package com.drinkingbuddies.drinkingbuddies.controller;

import com.drinkingbuddies.drinkingbuddies.controller.Util.User;
import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Controller
public class HomeController {

	private dbUtility util = new dbUtility();

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String homePage(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		// check if user's logged in
		String email = "";
		if (request.getCookies() != null) {
			try {
				email = readCookie("userEmail", request).get();
			} catch (Exception e) {
				return "home";
			}
			// if logged in, get drinking history
			Map<String, String> drinkHistory = util.getDrinkingHistory(email);
			LinkedList<String> history = new LinkedList<>();
			for (Entry<String, String> e : drinkHistory.entrySet()) {
				String details = e.getValue();
				int amount = Integer.parseInt(details.split(",")[0]);
				String date = details.split(",")[1];
				
				if (amount <= 10) {
					history.add("Date: "+date+" &emsp; Session: "+e.getKey() + " - You drinked "+amount+" shots. That's not too much.");
				}else if (amount > 10 && amount <= 20) {
					history.add("Date: "+date+" &emsp; Session: "+e.getKey() + " - You drinked "+amount+" shots. You can certainly drink...");
				}else {
					history.add("Date: "+date+" &emsp; Session: "+e.getKey() + " - You drinked "+amount+" shots. Did you wake up in ICU?");
				}
				
			}
			if (!history.isEmpty()) {
				request.setAttribute("history", history);
			}
			
			
			// if logged in, pull out the possible friend request.
			LinkedList<String> allRequests = util.getPendingRequests(email);
			if(!allRequests.isEmpty() && request != null){
				request.setAttribute("curRequest", allRequests);
			}
		}
		return "home";
	}
	
	
	@RequestMapping(value= {"/acceptFriend"}, method = RequestMethod.POST)
	public String acceptFriend(@RequestParam(defaultValue = "") String reqEmail, HttpServletRequest request) {		
		// check if user's logged in
		String email = "";
		if (request.getCookies() != null) {
			try {
				email = readCookie("userEmail", request).get();
			} catch (Exception e) {
				return "home";
			}
		}
		
		if (!reqEmail.isBlank()) {
			util.acceptFriend(reqEmail,email);
		}
		return "redirect:/home";
	}

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.POST)
	public String homePage(HttpServletRequest request) {
		return "home";
	}

	public Optional<String> readCookie(String key, HttpServletRequest request) {
		return Arrays.stream(request.getCookies())
				.filter(c -> key.equals(c.getName()))
				.map(Cookie::getValue)
				.findAny();
	}
}