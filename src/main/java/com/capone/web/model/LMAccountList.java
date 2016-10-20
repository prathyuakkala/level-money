package com.capone.web.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LMAccountList {

	@JsonProperty("accounts")
	private List<LMAccount> lmAccount;

	public List<LMAccount> getLmAccount() {
		if( this.lmAccount == null ) {
			this.lmAccount = new ArrayList<LMAccount>();
		}
		return lmAccount;
	}

	public void setLmAccount(List<LMAccount> lmAccount) {
		this.lmAccount = lmAccount;
	}
}
