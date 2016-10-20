package com.capone.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Model class for Account
 * @author Prathyusha
 * @since 10-18-2016
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LMAccount {

	@JsonProperty("is-autosave-source")
	private Boolean isAutoSaveSource;
	
	@JsonProperty("can-be-autosave-target")
	private Boolean autoSaveTarget;
	
	@JsonProperty("institution-id")
	private Integer institutionId;
	
	@JsonProperty("asset-account-type")
	private String assetAccountType;
	
	@JsonProperty("autosave-account-priority")
	private String autoSaveAcctPrty;
	
	@JsonProperty("account-name")
	private String acctName;
	
	@JsonProperty("institution-login-id")
	private String institLoginId;
	
	@JsonProperty("institution-name")
	private String institutionName;
	
	@JsonProperty("account-id")
	private String accountId;
	
	@JsonProperty("balance")
	private Integer balance;
	
	@JsonProperty("last-digits")
	private String lastDigits;
	
	@JsonProperty("active")
	private Boolean active;
	
	@JsonProperty("account-type")
	private String accountType;

	public Boolean getIsAutoSaveSource() {
		return isAutoSaveSource;
	}

	public void setIsAutoSaveSource(Boolean isAutoSaveSource) {
		this.isAutoSaveSource = isAutoSaveSource;
	}

	public Boolean getAutoSaveTarget() {
		return autoSaveTarget;
	}

	public void setAutoSaveTarget(Boolean autoSaveTarget) {
		this.autoSaveTarget = autoSaveTarget;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getAssetAccountType() {
		return assetAccountType;
	}

	public void setAssetAccountType(String assetAccountType) {
		this.assetAccountType = assetAccountType;
	}

	public String getAutoSaveAcctPrty() {
		return autoSaveAcctPrty;
	}

	public void setAutoSaveAcctPrty(String autoSaveAcctPrty) {
		this.autoSaveAcctPrty = autoSaveAcctPrty;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getInstitLoginId() {
		return institLoginId;
	}

	public void setInstitLoginId(String institLoginId) {
		this.institLoginId = institLoginId;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getLastDigits() {
		return lastDigits;
	}

	public void setLastDigits(String lastDigits) {
		this.lastDigits = lastDigits;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
}
