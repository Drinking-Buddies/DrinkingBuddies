package com.drinkingbuddies.drinkingbuddies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EditProfileController {
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String profilePage() {
		return "editProfile";
	}
}
