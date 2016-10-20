package com.capone.web.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.capone.web.model.LMTransaction;
import com.capone.web.model.LMTransactionList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Interface for the service layer
 * @author Prathyusha
 * @since 10-18-2016
 */
public interface LMTransactionsService {
	
	
	public Map<String, Object>  getTransactions() throws JsonParseException, JsonMappingException, IOException;
	
	public Map<String, Object>  getTransactionsWithoutDonuts() throws JsonParseException, JsonMappingException, IOException;
	
	public Map<String, Object>  getTransactionsWithoutCCPayments() throws JsonParseException, JsonMappingException, IOException;

}
