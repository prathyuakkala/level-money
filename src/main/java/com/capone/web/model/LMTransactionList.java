package com.capone.web.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Model class for transaction list
 * @author Prathyusha
 * @since 10-18-2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LMTransactionList {

	@JsonProperty("transactions")
	private List<LMTransaction> transactions;
	
	private String error;

	public List<LMTransaction> getTransactions() {
		if( this.transactions == null ) {
			this.transactions = new ArrayList<LMTransaction>();
		}
		return transactions;
	}

	public void setTransactions(List<LMTransaction> transactions) {
		this.transactions = transactions;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
