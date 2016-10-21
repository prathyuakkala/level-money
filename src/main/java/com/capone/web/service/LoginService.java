package com.capone.web.service;

import java.io.IOException;

import com.capone.web.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Interface for the login service layer
 * @author Prathyusha
 * @since 10-18-2016
 */
public interface LoginService {

	public User validateUser(User user) throws JsonParseException, JsonMappingException, IOException;

}
