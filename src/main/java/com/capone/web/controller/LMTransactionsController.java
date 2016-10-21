package com.capone.web.controller;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capone.web.jsonview.Views;
import com.capone.web.model.LMResponseBody;
import com.capone.web.model.User;
import com.capone.web.service.LMTransactionsService;
import com.capone.web.service.LoginService;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Controller class for Level Money transactions processing
 * @author Prathyusha
 * @since 10-18-2016
 */
@RestController
public class LMTransactionsController {
    
	
	@Autowired
	private LMTransactionsService lmTransactionsService;

	@Autowired
	private LoginService loginService;
	
	/**
	 * This method calls the corresponding service 
	 * layers' method depending on the request uri
	 * to get the transactions.
	 * @param request
	 * @param user
	 * @return LMResponseBody
	 */
	@JsonView(Views.Public.class)
	@RequestMapping(value = { "/search/api/getSearchResult", "/search/api/getSearchResultWithoutDougnuts",
			"/search/api/getSearchResultWithoutCC" })
	public LMResponseBody getAllTransactions( HttpServletRequest request, @RequestBody User user ) {
		LMResponseBody result = new LMResponseBody();
		Map<String, Object> lmTransactionsJson = null;
		try {
			//Security is not important for now.
			user = loginService.validateUser(user);
			String uri = request.getRequestURI();
			if( uri.endsWith("getSearchResult")) {
				lmTransactionsJson = lmTransactionsService.getTransactions(user);
			} else if( uri.endsWith("getSearchResultWithoutDougnuts") ){
				lmTransactionsJson = lmTransactionsService.getTransactionsWithoutDonuts(user);
			} else {
				lmTransactionsJson = lmTransactionsService.getTransactionsWithoutCCPayments(user);
			}
			if (!lmTransactionsJson.isEmpty()) {
				result.setCode("200");
				result.setMsg("SUCCESS");
				result.setResult(lmTransactionsJson);
			} else {
				result.setCode("204");
				result.setMsg("No Transaction Found !!!! ");
			}
		} catch (Exception exception) {
			result.setCode("500");
			result.setMsg("Exception occured while searching Transactions");
		}
		return result;
	}
}
