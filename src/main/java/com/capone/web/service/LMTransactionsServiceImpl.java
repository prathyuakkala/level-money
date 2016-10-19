package com.capone.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capone.web.helper.HttpHelper;
import com.capone.web.model.LMTransaction;
import com.capone.web.model.LMTransactionList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LMTransactionsServiceImpl implements LMTransactionsService {

	@Autowired
	private HttpHelper httpHelper;

	public Map getAllTransactions() throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonAsString = httpHelper.httpClientPost("", "");
		LMTransactionList lmTransactionList = mapper.readValue(jsonAsString,LMTransactionList.class);
		Map<String, List<LMTransaction>> monthlyExpdtrMap = new HashMap<String, List<LMTransaction>>();
		for( LMTransaction lmTransaction : lmTransactionList.getTransactions()) {
			Calendar cal = Calendar.getInstance();
		    cal.setTime( lmTransaction.getTransactionTime() );
			String key = cal.get(1) + "-" + (cal.get(2)+1);
			if (!monthlyExpdtrMap.containsKey(key)) {
			    List<LMTransaction> list = new ArrayList<LMTransaction>();
			    list.add(lmTransaction);
			    monthlyExpdtrMap.put(key, list);
			} else {
				monthlyExpdtrMap.get(key).add(lmTransaction);
			}
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer totalSpent = 0;
	    Integer totalEarn = 0;
		for (Entry<String, List<LMTransaction>> entry : monthlyExpdtrMap.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    Integer spent = 0;
		    Integer earn = 0;
		    for( LMTransaction lmTransaction : entry.getValue()) {
		    	
		    	if( lmTransaction.getAmount() != null && lmTransaction.getAmount().trim().startsWith("-")) {
		    		spent = spent + Integer.parseInt(lmTransaction.getAmount().replace("-", ""));
		    	} else if( lmTransaction.getAmount() != null){
		    		earn = earn + Integer.parseInt(lmTransaction.getAmount());
		    	}
		    }
		    Map<String, Object> resultChildMap = new HashMap<String, Object>();
		    totalSpent = totalSpent + spent;
		    totalEarn = totalEarn + earn;
		    resultChildMap.put("spent", spent);
		    resultChildMap.put("earn", earn);
		    resultMap.put(entry.getKey(),resultChildMap );
		}
		Map<String, Object> resultAvgMap = new HashMap<String, Object>();
		resultAvgMap.put("spent", totalSpent/monthlyExpdtrMap.size());
		resultAvgMap.put("earn", totalEarn/monthlyExpdtrMap.size());
		resultMap.put("average",resultAvgMap );
		TreeMap<String, Object> sortedResultMap = new TreeMap<String, Object>(resultMap);
		return sortedResultMap.descendingMap();
	}

}
