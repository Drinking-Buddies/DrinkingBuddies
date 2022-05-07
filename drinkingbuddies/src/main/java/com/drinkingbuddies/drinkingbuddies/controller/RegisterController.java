package com.drinkingbuddies.drinkingbuddies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage() {
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String homePage(ModelMap model, @RequestParam String email, 
			@RequestParam String password, @RequestParam String userID,
			@RequestParam String legalName, @RequestParam String birthDate,
			@RequestParam String gender, @RequestParam String weight) {
		// Maybe do database stuff here
		
		model.put("userID", userID);
		return "home";
	}
}
