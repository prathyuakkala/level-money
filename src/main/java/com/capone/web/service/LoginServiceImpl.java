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
/**
 * Login service implementation class
 * @author Prathyusha
 * @since 10-18-2016
 */
@Service
public class LoginServiceImpl implements LoginService {

	
	@Autowired
	private HttpHelper httpHelper;
	/**
     * This method authenticates the user 
     * @param user
	 * @return User
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException 
     */
	@Override
	public User validateUser(User user) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> parmsMap = new HashMap<String, Object>();
		parmsMap.put("email", user.getEmail());
		parmsMap.put("password", user.getPassword());
		
		Map<String, Object> parmsMap2 = new HashMap<String, Object>();
		parmsMap2.put("api-token", user.getApiToken());
		parmsMap2.put("json-verbose-response", false);
		parmsMap2.put("json-strict-mode", false);
		parmsMap.put("args", parmsMap2);
		String parmsMapAsJson = new ObjectMapper().writeValueAsString(parmsMap);
		String jsonAsString = httpHelper.httpClientPost("login", parmsMapAsJson);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonAsString,User.class);
	}

}
