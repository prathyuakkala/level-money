package com.capone.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LMTransactionList {

	@JsonProperty("transactions")
	private List<LMTransaction> transactions;
	
	private String error;

	public List<LMTransaction> getTransactions() {
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
