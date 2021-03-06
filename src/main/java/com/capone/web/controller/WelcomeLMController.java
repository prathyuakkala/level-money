package com.capone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Welcome Controller class
 * @author Prathyusha
 * @since 10-18-2016
 */
@Controller
public class WelcomeLMController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "welcome";
	}

}
