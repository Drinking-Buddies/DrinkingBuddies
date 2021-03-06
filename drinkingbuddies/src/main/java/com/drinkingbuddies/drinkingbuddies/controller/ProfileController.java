package com.drinkingbuddies.drinkingbuddies.controller;

import com.drinkingbuddies.drinkingbuddies.classes.RegisterUser;
import com.drinkingbuddies.drinkingbuddies.controller.Util.User;
import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;

@Controller
public class ProfileController {
	private dbUtility util = new dbUtility();

	@RequestMapping(value = "/removeFriend")
	public String removeFriend(@RequestParam(defaultValue = "") String removeFriendEmail,
			HttpServletRequest request, HttpServletResponse response) {
		String userEmail = "";
		try {
			userEmail = readCookie("userEmail", request).get();
		} catch (Exception e) {
			return "profileRemind";
		}
		if(!removeFriendEmail.isBlank()){
			util.removeFriend(userEmail, removeFriendEmail);
		}
		return "redirect:/profile";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profilePage(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
		// Check whether the user is guest or not.
		// If guest, redirect to an new simple asfuck reminding jsp page.
		if(request.getCookies()!=null){
			String email = "";
			try{
				email = readCookie("userEmail", request).get();
			}catch(Exception e){
				return "profileRemind";
			}
			if(email.isBlank()) return "profileRemind";
			// When we know user's logged in, return to normal profile page.
			User curUser = util.getUser(email);
			model.put("userName",curUser.getUsername());
			model.put("bio",curUser.getBio());
			model.put("email",curUser.getEmail());
			model.put("phone", curUser.getPhone());
			model.put("DOB",curUser.getBirthday());
			model.put("weight",curUser.getWeight());
			model.put("gender",curUser.getGender());
			model.put("emergencyNum",curUser.getEmergency_phone());
			LinkedList<String> friends = util.getFriends(curUser.getEmail());
			request.setAttribute("friends",friends);
			return "profile";
		}else{
			return "profileRemind";
		}
	}

	// Sending friend requests
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profilePage(ModelMap map, @RequestParam(defaultValue = "") String receiverEmail,
							  HttpServletRequest request) {
		String userEmail = "";
		try {
			userEmail = readCookie("userEmail", request).get();
		} catch (Exception e) {
			return "profileRemind";
		}
		User curUser = util.getUser(userEmail);
		map.put("userName", curUser.getUsername());
		map.put("bio", curUser.getBio());
		map.put("email", curUser.getEmail());
		map.put("phone", curUser.getPhone());
		map.put("DOB", curUser.getBirthday());
		map.put("weight",curUser.getWeight());
		map.put("gender",curUser.getGender());
		map.put("emergencyNum",curUser.getEmergency_phone());

		String msg = "";
		// Use try clause to safely get the current user's email
		// Use util function to get receiver's email
		LinkedList<String> currentFriends = util.getFriends(userEmail);
		boolean alreadyFriends = false;
		boolean sentRequestAlready = false;
		for (String f : currentFriends) {
			if (f.equals(receiverEmail)) {
				alreadyFriends = true;
				break;
			}
		}
		
		sentRequestAlready = util.friendRequestExists(userEmail, receiverEmail);
		
		if (!alreadyFriends && !sentRequestAlready) {
			msg = util.friendRequest(userEmail,receiverEmail);
			map.put("msg", msg);
		}else if (alreadyFriends){
			map.put("msg", "You guys are friends already...");
		}else if (sentRequestAlready) {
			map.put("msg", "You already sent the request");
		}
		
		LinkedList<String> friends = util.getFriends(curUser.getEmail());
		request.setAttribute("friends",friends);
		return "profile";
	}


	public Optional<String> readCookie(String key, HttpServletRequest request) {
		return Arrays.stream(request.getCookies())
				.filter(c -> key.equals(c.getName()))
				.map(Cookie::getValue)
				.findAny();
	}
}
