package com.drinkingbuddies.drinkingbuddies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String homePage(ModelMap model, @RequestParam String userID, @RequestParam String password) {
		// Check if password and userID matches
		// This is a template that could be followed
		if (userID.equals("admin") && password.equals("root")) {
			model.put("userID", userID);
			return "home";
		}else {
			model.put("errorMsg", "Invalid user ID or password");
			return "login";
		}
		
	}
}
