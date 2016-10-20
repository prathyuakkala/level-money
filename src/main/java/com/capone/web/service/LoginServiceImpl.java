package com.capone.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.web.helper.HttpHelper;
import com.capone.web.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginServiceImpl implements LoginService {

	
	@Autowired
	private HttpHelper httpHelper;
	
	@Override
	public boolean validateUser(String email, String password) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> parmsMap = new HashMap();
		parmsMap.put("email", email);
		parmsMap.put("password", password);
		String jsonAsString = httpHelper.httpClientPost("login", parmsMap);
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(jsonAsString,User.class);
		if( user != null && user.getToken() != null ) {
			return true;
		} 
		return false;
	}

}
