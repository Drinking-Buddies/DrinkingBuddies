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

import com.drinkingbuddies.drinkingbuddies.controller.Util.User;
import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;

@Controller
public class LoginController {
	private dbUtility util = new dbUtility();
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLogin(ModelMap model, @RequestParam String email, @RequestParam String password, HttpServletResponse response) {
		// Check if password and userID matches
		// This is a template that could be followed
		
		// Check if the user exists
		if (util.userExists(email, password)) {
			User user = util.getUser(email);
			String userID = user.getUsername();
			Cookie usrName = new Cookie("userName", "Welcome="+userID+"!");
			Cookie usrEmail = new Cookie("userEmail", email);
			usrName.setMaxAge(3600);
			usrEmail.setMaxAge(3600);
			
			usrName.setPath("/");
			usrEmail.setPath("/");
			
			response.addCookie(usrName);
			response.addCookie(usrEmail);
			return "redirect:/home";
		}else {
			model.put("errorMsg", "Invalid user ID or password");
			return "login";
		}
	}
}
