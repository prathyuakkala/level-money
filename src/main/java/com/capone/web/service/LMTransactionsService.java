package com.capone.web.service;

import java.io.IOException;
import java.util.List;

import com.capone.web.model.LMTransaction;
import com.capone.web.model.LMTransactionList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LMTransactionsService {
	
	
	public LMTransactionList getAllTransactions() throws JsonParseException, JsonMappingException, IOException;

}
