package com.proj.asqa.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login**", method = RequestMethod.GET)
	public ModelAndView dologin() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
		
		
	}
}
