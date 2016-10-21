package com.capone.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capone.web.jsonview.Views;
import com.capone.web.model.LMResponseBody;
import com.capone.web.model.User;
import com.capone.web.service.LoginService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/validateUser" )
	public LMResponseBody validateUser( HttpServletRequest request,
			@RequestBody User user) {
		LMResponseBody result = new LMResponseBody();
		try {
			user = loginService.validateUser(user);
			if( user.getToken() != null ) {
				result.setCode("200");
				result.setMsg("SUCCESS");
				result.setResult(true);
			} else {
				result.setCode("204");
				result.setMsg("ERROR");
				result.setResult(false);
			}
		} catch (Exception exception) {
			result.setCode("500");
			result.setMsg("Exception occured while searching Transactions");
		}
		return result;
	}
}
