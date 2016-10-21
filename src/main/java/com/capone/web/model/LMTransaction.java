package com.capone.web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Model class for transaction
 * @author Prathyusha
 * @since 10-18-2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LMTransaction {
  
	@JsonProperty("amount")
	String amount;
	@JsonProperty("is-pending")
	String isPending;
	@JsonProperty("account-id")
	String accountId;
	@JsonProperty("aggregration-time")
	String aggregrationTime;
	@JsonProperty("transaction-id")
	String transanctionId;
	@JsonProperty("raw-merchant")
	String rawMerchant;
	@JsonProperty("categorization")
	String catg;
	@JsonProperty("merchant")
	String merchant;
	@JsonProperty("transaction-time")
	Date transactionTime;
	
	public Date getTransactionTime() {
		return transactionTime;
	}
	public String getRawMerchant() {
		return rawMerchant;
	}
	public void setRawMerchant(String rawMerchant) {
		this.rawMerchant = rawMerchant;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
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
}
