package com.drinkingbuddies.drinkingbuddies.controller;

import com.drinkingbuddies.drinkingbuddies.controller.Util.User;
import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedList;
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
			// if logged in, pull out the possible friend request.
			LinkedList<String> allRequests = util.getPendingRequests(email);
			if(allRequests!=null){
				if(allRequests.size()!=0){
					User temp = util.getUser(allRequests.get(0));
					request.setAttribute("curRequest",temp.getUsername());
				}
			}
		}
		return "home";
	}
	
	
	@RequestMapping(value= {"/acceptFriend"}, method = RequestMethod.POST)
	public String acceptFriend(HttpServletRequest request) {
		// check if user's logged in
		String email = "";
		if (request.getCookies() != null) {
			try {
				email = readCookie("userEmail", request).get();
			} catch (Exception e) {
				return "home";
			}
		}
		
		// Direct here via post so either accept a request or dismiss
		LinkedList<String> allRequests = util.getPendingRequests(email);
		
		if (!allRequests.isEmpty()) {
			util.acceptFriend(allRequests.get(0),email);
			// After accpting, it is removed from sql.
			allRequests = util.getPendingRequests(email);
			if (!allRequests.isEmpty()) {
				User temp = util.getUser(allRequests.get(0));
				request.setAttribute("curRequest",temp.getUsername());
			}
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