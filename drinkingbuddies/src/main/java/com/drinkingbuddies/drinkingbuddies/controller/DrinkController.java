package com.drinkingbuddies.drinkingbuddies.controller;

import org.springframework.stereotype.Controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;

@Controller
public class DrinkController {
	private dbUtility util = new dbUtility();
	
	@RequestMapping(value = "/drink", method = RequestMethod.GET)
	public String drinkPage() {
		return "drink";
	}
	
	@RequestMapping(value = "/drink", method = RequestMethod.POST)
	public String homePage(ModelMap model, @RequestParam(defaultValue = "") String hostName, 
			@RequestParam String joinName, HttpServletResponse response, HttpServletRequest request) {
		
		// Need to fix the bug with guest

		Optional<String> read = readCookie("userEmail", request);
		String email = "Guest";
		if (read.isPresent()) {
			email = read.get();
		}
		
		if (hostName.isBlank() && joinName.isBlank()) {
			model.put("errorMsg", "Please enter a lobby name");
			return "drink";
		}
		else if (!hostName.isBlank() && joinName.isBlank()) {
			LocalDate dateObj = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String date = dateObj.format(formatter);
	        
	        // cannot host a lobby if it exists
	        if (util.eventExists(hostName)) {
	        	model.put("errorMsg", "This lobby already exists");
	        	return "drink";
	        }else {
	        	if (!email.isBlank()) {
	        		if (hostName.contains(" ")) {
						model.put("errorMsg", "You cannot put space in lobby name");
						return "drink";
					}
	        		
		        	util.newEvent(date, hostName);
		        	util.joinEvent(email, hostName, 0);
		        	
		        	Cookie lobbyName = new Cookie("lobbyName", hostName);
		        	lobbyName.setMaxAge(3600);
		        	response.addCookie(lobbyName);
		        	return "redirect:/lobby";
	        	}else {
	        		return "drink";
	        	}
	        }
		}
		else if (hostName.isBlank() && !joinName.isBlank()) {
			// very likely to not be blank lmao
			// check if the room exists
			if (!email.isBlank()) {
				if (util.eventExists(joinName)) {
					if (!email.equals("Guest")) {
						util.joinEvent(email, joinName, 0);
					}
					Cookie lobbyName = new Cookie("lobbyName", joinName);
		        	lobbyName.setMaxAge(3600);
		        	response.addCookie(lobbyName);
					return "redirect:/lobby";
				}
				else {
					model.put("errorMsg", "Room not found...");
					return "drink";
				}
			}
			return "drink";
		}
		else{
			model.put("errorMsg", "Please don't enter two fields at once");
			return "drink";
		}
		
	}
	
	// Read them cookies
	public Optional<String> readCookie(String key, HttpServletRequest request) {
	    return Arrays.stream(request.getCookies())
	      .filter(c -> key.equals(c.getName()))
	      .map(Cookie::getValue)
	      .findAny();
	}
}
