package com.drinkingbuddies.drinkingbuddies.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

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
		System.out.println(allPlayers.size());
		allPlayers.clear();
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/addShot")
	public String addShot() {
		allPlayers.get(0).addShot();
		return "redirect:/lobby";
	}
	
    @RequestMapping(value = "/lobby", method = RequestMethod.GET)
    public String lobbyPage(HttpServletRequest request){
    	
    	if (allPlayers.isEmpty()) {
    		// Get participants!!!!!!!!!!!!!
        	String lobbyName = readCookie("lobbyName", request).get();
        	LinkedList<String> allPlayerEmails = util.getParticipants(lobbyName);
        	
        	// Need to check if the user is actually me
        	String myEmail = "Guest";
        	String myUsername = "Guest";
        	Optional<String> tmpUserEmail = readCookie("userEmail",request);
        	if (tmpUserEmail.isPresent()) {
        		myEmail = tmpUserEmail.get();
        	}
        	
        	
        	// Need to construct thread objects and get them into the list
        	User me = null;
        	int myShots = 0;
        	int seat = 2;
        	
        	for (String e : allPlayerEmails) {
        		User u = util.getUser(e);
        		String email = u.getEmail();
        		String username = u.getUsername();
        		int amount = util.getAmountDrinked(u.getEmail(), lobbyName);
        		
        		if (email.equals(myEmail)) {
        			myUsername = u.getUsername();
        			myShots = amount;
        		}else {
        			UserThread otherPlayer = new UserThread(username, email, lobbyName, amount, seat);
            		allPlayers.add(otherPlayer);
            		seat++;
        		}
        	}
        	UserThread mePlayer = new UserThread(myUsername, myEmail, lobbyName, myShots, 1);
        	allPlayers.add(0, mePlayer);        	
        	executeThreads();
    	}
        return "lobby";
    }
    
    private void executeThreads() {
    	ExecutorService ex = Executors.newCachedThreadPool();
    	for (UserThread u : allPlayers) {
    		try {
				ex.execute(u);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
