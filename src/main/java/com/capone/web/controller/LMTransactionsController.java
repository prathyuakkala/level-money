package com.capone.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capone.web.service.LMTransactionsService;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.capone.web.jsonview.Views;
import com.capone.web.model.LMResponseBody;
import com.capone.web.model.LMTransactionList;
import com.capone.web.model.SearchCriteria;

@RestController
public class LMTransactionsController {

	@Autowired
	private LMTransactionsService lmTransactionsService;
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getSearchResult")
	public LMResponseBody getAllTransactions(@RequestBody SearchCriteria search) throws JsonParseException, JsonMappingException, IOException {

		LMResponseBody result = new LMResponseBody();

		if (isValidSearchCriteria(search)) {
			LMTransactionList lmTransaction = lmTransactionsService.getAllTransactions();
			if (lmTransaction.getTransactions().size() > 0) {
				result.setCode("200");
				result.setMsg("");
				result.setResult(lmTransaction.getTransactions());
			} else {
				result.setCode("204");
				result.setMsg("No user!");
			}

		} else {
			result.setCode("400");
			result.setMsg("Search criteria is empty!");
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}

	private boolean isValidSearchCriteria(SearchCriteria search) {

		boolean valid = true;

		if (search == null) {
			valid = false;
		}

		if ((StringUtils.isEmpty(search.getUsername())) && (StringUtils.isEmpty(search.getEmail()))) {
			valid = false;
		}

		return valid;
	}
}
