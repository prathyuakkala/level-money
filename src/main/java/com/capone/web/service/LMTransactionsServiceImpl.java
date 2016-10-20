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
import com.capone.web.model.LMAccount;
import com.capone.web.model.LMAccountList;
import com.capone.web.model.LMTransaction;
import com.capone.web.model.LMTransactionList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Implementation class for service layer
 * @author Prathyusha
 * @since 10-18-2016
 */
@Service
@SuppressWarnings("unchecked")
public class LMTransactionsServiceImpl implements LMTransactionsService {

	@Autowired
	private HttpHelper httpHelper;
	
	private static final String CONST_DONUT_1 = "DUNKIN #";
	private static final String CONST_DONUT_2 = "Krispy Kreme Donuts";
    /**
     * This method returns all transactions of a user 
     */
	@Override
	public Map getTransactions() throws JsonParseException, JsonMappingException, IOException {
		return populateTransactionToMap(getAllTransactionsFromLM());
	}
	/**
     * This method returns all transactions of a user 
     * excluding the donut transactions
     */
	@Override
	public Map getTransactionsWithoutDonuts()
			throws JsonParseException, JsonMappingException, IOException {
		LMTransactionList lmTransactionList = getAllTransactionsFromLM();
		LMTransactionList lmTransactionListWoDougnuts = new LMTransactionList();
		for( LMTransaction lmTransaction : lmTransactionList.getTransactions()) {
			if( lmTransaction.getMerchant() != null && !lmTransaction.getMerchant().contains(CONST_DONUT_1) 
					&& !lmTransaction.getMerchant().contains(CONST_DONUT_2) ) {
				lmTransactionListWoDougnuts.getTransactions().add(lmTransaction);
			} 
		}
		return populateTransactionToMap(lmTransactionListWoDougnuts);
	}
	/**
     * This method returns all transactions of a user 
     * excluding the credit card payments
     */
	@Override
	public Map<String, Object> getTransactionsWithoutCCPayments()
			throws JsonParseException, JsonMappingException, IOException {
		LMTransactionList lmTransactionList = getAllTransactionsFromLM();
		ObjectMapper mapper = new ObjectMapper();
		/*
		 * We are making backend call to double check the credit card account 
		 */
		String ccAccountId = "";
		String chkAccountId = "";
		String jsonAsString = httpHelper.httpClientPost("get-accounts", null);
		LMAccountList lmAccounts =  mapper.readValue(jsonAsString,LMAccountList.class);
		if( lmAccounts.getLmAccount().size() > 0 ) {
			for( LMAccount lmAccount : lmAccounts.getLmAccount() ) {
				if( lmAccount.getAcctName().contains("Credit Card") ) {
					ccAccountId = lmAccount.getAccountId();
				} else if(  lmAccount.getAcctName().contains("Checking")  ) {
					chkAccountId = lmAccount.getAccountId();
				}
			}
		}
		LMTransaction cclmTransaction = null;
		List<LMTransaction> ccPaymentTransactions = new ArrayList<LMTransaction>();
		for( LMTransaction lmTransaction : lmTransactionList.getTransactions()) {
			if( lmTransaction.getAccountId().equals(ccAccountId) && !lmTransaction.getAmount().startsWith("-")) {
				cclmTransaction = lmTransaction;
			} else if( cclmTransaction != null && lmTransaction.getAccountId().equals(chkAccountId) 
					&& !lmTransaction.getAmount().startsWith("-") && 
					( cclmTransaction.getTransactionTime().getTime() - lmTransaction.getTransactionTime().getTime() ) <= 86400000) {
				ccPaymentTransactions.add(cclmTransaction);
			}
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for( LMTransaction ccPaymentTransaction : ccPaymentTransactions ) {
			Map<String, Object> resultChildMap = new HashMap<String, Object>();
			resultChildMap.put("account-id",ccPaymentTransaction.getAccountId());
			resultChildMap.put("merchant",ccPaymentTransaction.getMerchant());
			resultChildMap.put("amount",ccPaymentTransaction.getAmount());
			resultChildMap.put("transaction-time",ccPaymentTransaction.getTransactionTime().toString());
			resultList.add(resultChildMap);
		}
		resultMap.put("transactions", resultList);
		return resultMap;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	private LMTransactionList getAllTransactionsFromLM() throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonAsString = httpHelper.httpClientPost("get-all-transactions", null);
		return mapper.readValue(jsonAsString,LMTransactionList.class);
	}
	
	

	private Map<String, Object> populateTransactionToMap(LMTransactionList lmTransactionList) {
		
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
