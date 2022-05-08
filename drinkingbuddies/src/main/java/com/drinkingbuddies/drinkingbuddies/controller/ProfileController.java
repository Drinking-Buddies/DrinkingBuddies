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
			if(email.equals("")) return "profileRemind";
			// When we know user's logged in, return to normal profile page.
			User curUser = util.getUser(email);
			model.put("userName",curUser.getUsername());
			model.put("bio",curUser.getBio());
			model.put("email",curUser.getEmail());
			model.put("phone", curUser.getPhone());
			model.put("DOB",curUser.getBirthday());
			LinkedList<String> friends = util.getFriends(curUser.getEmail());
			request.setAttribute("friends",friends);
			return "profile";
		}else{
			return "profileRemind";
		}
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profilePage(ModelMap map, @RequestParam(defaultValue = "") String receiverEmail,HttpServletRequest request) {
		String msg = "";
		String usrEmail = "";
		// Use try clause to safely get the current user's email
		// Use util function to get receiver's email
		if (request.getCookies() != null) {
			String userEmail = "";
			try {
				userEmail = readCookie("userEmail", request).get();
			} catch (Exception e) {
				return "profileRemind";
			}
			if (userEmail.equals("")) return "profileRemind";
			// When we know user's logged in, return to normal profile page.
			User curUser = util.getUser(userEmail);
			map.put("userName", curUser.getUsername());
			map.put("bio", curUser.getBio());
			map.put("email", curUser.getEmail());
			map.put("phone", curUser.getPhone());
			map.put("DOB", curUser.getBirthday());
			msg = util.friendRequest(usrEmail, receiverEmail);
			map.put("msg", msg);
			return "profile";
		}
		return "home";
	}

	public Optional<String> readCookie(String key, HttpServletRequest request) {
		return Arrays.stream(request.getCookies())
				.filter(c -> key.equals(c.getName()))
				.map(Cookie::getValue)
				.findAny();
	}
}
