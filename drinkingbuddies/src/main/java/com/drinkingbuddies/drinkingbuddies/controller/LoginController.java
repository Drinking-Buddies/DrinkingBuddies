package com.drinkingbuddies.drinkingbuddies.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session;
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
	public String homePage(ModelMap model, @RequestParam String userID, @RequestParam String password, HttpServletResponse response) {
		// Check if password and userID matches
		// This is a template that could be followed
		if (userID.equals("admin") && password.equals("root")) {
			Cookie usrName = new Cookie("username", "Welcome="+userID+"!");
			usrName.setMaxAge(3600);
			response.addCookie(usrName);
			return "home";
		}else {
			model.put("errorMsg", "Invalid user ID or password");
			return "login";
		}
	}
}
