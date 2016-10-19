package com.capone.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LMTransaction {

	String amount;
	String isPending;
	String accountId;
	String aggregrationTime;
	String transanctionId;
	String catg;
	String merchant;
	String transactionTime;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIsPending() {
		return isPending;
	}
	public void setIsPending(String isPending) {
		this.isPending = isPending;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAggregrationTime() {
		return aggregrationTime;
	}
	public void setAggregrationTime(String aggregrationTime) {
		this.aggregrationTime = aggregrationTime;
	}
	public String getTransanctionId() {
		return transanctionId;
	}
	public void setTransanctionId(String transanctionId) {
		this.transanctionId = transanctionId;
	}
	public String getCatg() {
		return catg;
	}
	public void setCatg(String catg) {
		this.catg = catg;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	
}
