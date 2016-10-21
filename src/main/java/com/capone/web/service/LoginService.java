package com.capone.web.service;

import java.io.IOException;

import com.capone.web.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LoginService {

	public User validateUser(User user) throws JsonParseException, JsonMappingException, IOException;

}
