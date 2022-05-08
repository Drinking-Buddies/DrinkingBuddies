package com.drinkingbuddies.drinkingbuddies.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.drinkingbuddies.drinkingbuddies.controller.Util.User;
import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;
import com.drinkingbuddies.drinkingbuddies.threads.UserThread;

@Controller
public class LobbyController {
	private static LinkedList<UserThread> allPlayers = new LinkedList<>();
	private dbUtility util = new dbUtility();
	
	@RequestMapping(value = "/leave")
	public String leaveToHome() {
		for (UserThread u : allPlayers) {
			u.finishSession();
		}
		allPlayers.clear();
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/addShot")
	public String addShot() {
		allPlayers.get(0).addShot();
		return "redirect:/lobby";
	}
	
    @RequestMapping(value = "/lobby", method = RequestMethod.GET)
    public String lobbyPage(ModelMap model, HttpServletRequest request){
    	allPlayers.clear();

    	String lobbyName = readCookie("lobbyName", request).get();
    	LinkedList<String> allPlayerEmails = util.getParticipants(lobbyName);
    	
    	// Need to check if the user is actually me
    	String myEmail = "Guest";
    	String myUsername = "Guest";
    	
    	Optional<String> tmpUserEmail = readCookie("userEmail",request);
    	if (tmpUserEmail.isPresent()) {
    		myEmail = tmpUserEmail.get();
    	}
    	
    	// Need to construct threads and get them into the list
    	int seat = 2;
    	for (String e : allPlayerEmails) {
    		User u = util.getUser(e);
    		String email = u.getEmail();
    		String username = u.getUsername();
    		int amount = util.getAmountDrinked(u.getEmail(), lobbyName);
    		// if it is me
    		if (email.equals(myEmail)) {
    			myUsername = u.getUsername();
    		}else {
    			UserThread otherPlayer = new UserThread(username, email, lobbyName, 0, seat);
        		allPlayers.add(otherPlayer);
        		seat++;
    		}
    	}
    	if (!myUsername.equals("Guest") && !myEmail.equals("Guest")) {
    		UserThread mePlayer = new UserThread(myUsername, myEmail, lobbyName, 0, 1);
        	allPlayers.add(0, mePlayer);
    	}
    	executeThreads();
    	
    	// Get amount of drinks
    	passValue(model);
        return "lobby";
    }
    
    private void passValue(ModelMap model) {
    	String place = "seat";
    	int num = 1;
    	for (UserThread u : allPlayers) {
    		model.put(place.concat(Integer.toString(num)), u.getAmountDrinked()+" "+u.getUsername());
    		num++;
    	}
    }
    
    private static void executeThreads() {
    	ExecutorService ex = Executors.newCachedThreadPool();
    	for (UserThread u : allPlayers) {
    		ex.execute(u);
    	}
    	ex.shutdown();
    	while(!ex.isTerminated()) {}
    }
    
    @RequestMapping(value = "/lobby", method = RequestMethod.POST)
    public void addDrink(@RequestParam String userID) {
    	for (UserThread u : allPlayers) {
    		if (u.getUsername().equals(userID)) {
    			u.addShot();
    		}
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
