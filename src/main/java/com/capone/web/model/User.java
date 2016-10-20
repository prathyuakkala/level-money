package com.capone.web.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class User {

	String email;
	String password;
	String token;
	Integer uid;
	String apiToken = "AppTokenForInterview";

	public User() {
	}

	public User(String username, String password, String email, Integer uid, String token) {
		super();
		this.email = email;
		this.password = password;
		this.token = token;
		this.uid = uid;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}


}
