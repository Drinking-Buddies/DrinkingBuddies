package com.drinkingbuddies.drinkingbuddies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profilePage() {
		return "profile";
	}
}
