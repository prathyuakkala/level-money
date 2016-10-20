package com.capone.web.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LoginService {

	boolean validateUser(String email, String password) throws JsonParseException, JsonMappingException, IOException;

}
