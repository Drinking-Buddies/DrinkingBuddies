package com.drinkingbuddies.drinkingbuddies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DrinkController {
	@RequestMapping(value = "/drink", method = RequestMethod.GET)
	public String drinkPage() {
		return "drink";
	}
	
	@RequestMapping(value = "/drink", method = RequestMethod.POST)
	public String homePage(ModelMap model, @RequestParam String hostName, @RequestParam String joinName) {
		// This is a template that could be followed
		if (joinName.equals("lobby")) {
			model.put("lobbyName", joinName);
			return "lobby";
		}else {
			model.put("errorMsg", "Room not found bruv");
			return "drink";
		}
		
	}
}
