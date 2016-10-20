package com.capone.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capone.web.jsonview.Views;
import com.capone.web.model.LMResponseBody;
import com.capone.web.service.LoginService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class LoginController {
	
	//
	@Autowired
	private LoginService loginService;
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/validateUser" )
	public LMResponseBody validateUser( HttpServletRequest request,
			@RequestBody String email, 
			@RequestBody String password) {
		LMResponseBody result = new LMResponseBody();
		try {
			boolean isValid = loginService.validateUser(email, password);
			if( isValid ) {
				result.setCode("200");
				result.setMsg("SUCCESS");
				result.setResult(isValid);
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
