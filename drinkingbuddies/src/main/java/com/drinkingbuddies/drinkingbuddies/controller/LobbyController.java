package com.drinkingbuddies.drinkingbuddies.controller;

import java.text.DecimalFormat;
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
	private dbUtility util = new dbUtility();
	private LinkedList<UserThread> allPlayers = new LinkedList<>();
	
	@RequestMapping(value = "/leave")
	public String leaveToHome() {
		allPlayers.clear();
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/addShot")
	public String addShot(ModelMap model, HttpServletRequest request) {
		boolean canAddShot = true;
		allPlayers.clear();
		
		updateValue(model, request, canAddShot);
		return "redirect:/lobby";
	}
	
    @RequestMapping(value = "/lobby")
    public String lobbyPage(ModelMap model, HttpServletRequest request){
    	allPlayers.clear();
    	boolean canAddShot = false;
    	updateValue(model, request, canAddShot);
        return "lobby";
    }
    
    private void updateValue(ModelMap model, HttpServletRequest request, boolean canAddShot) {
    	String lobbyName = readCookie("lobbyName", request).get();
    	LinkedList<String> allPlayerEmails = util.getParticipants(lobbyName);
    	
    	// Need to check if the user is actually me
    	String myEmail = "Guest";
    	String myUsername = "Guest";
    	String myGender = "";
    	int myWeight = 0;
    	
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
    			myGender = u.getGender();
    			myWeight = u.getWeight();
    		}else {
    			UserThread otherPlayer = new UserThread(username, email, lobbyName, 0, u.getGender(), u.getWeight());
        		allPlayers.add(otherPlayer);
        		seat++;
    		}
    	}
    	if (!myUsername.equals("Guest") && !myEmail.equals("Guest")) {
    		UserThread mePlayer = new UserThread(myUsername, myEmail, lobbyName, 0 , myGender, myWeight);
        	allPlayers.add(0, mePlayer);
    	}
    	executeThreads();
    	if (canAddShot) {
    		allPlayers.get(0).addShot();
    		canAddShot = false;
    		
    	}
    	// Get amount of drinks
    	passValue(model, myEmail, myGender, myWeight);
    }
    
    private void passValue(ModelMap model, String email, String gender, int weight) {
    	String place = "seat";
    	String drink = "drink";
    	int num = 1;
    	for (UserThread u : allPlayers) {
    		model.put(place.concat(Integer.toString(num)), u.getUsername());
    		model.put(drink.concat(Integer.toString(num)), u.getAmountDrinked());
    		num++;
    		
    		// For BAC
    		int amountDrinked = u.getAmountDrinked();
    		if (u.getEmail().equals(email)) {
    			double BAC = 0.0; 
    			DecimalFormat df = new DecimalFormat("0.00");
    			double alcohol = (double) (amountDrinked * 28.3495);
    			if (gender.equals("Female"))
    			{
    				BAC = (alcohol / (weight * 453.592 * 0.55)) * 100.0; 
    			}
    			else 
    			{
    				BAC = (alcohol / (weight * 453.592 * 0.68)) * 100.0; 
    				
    			}
    			String drunkMsg = setDrunkMsg(BAC);
    			model.put("BAC", df.format(BAC).toString());
    			model.put("drunkMsg", drunkMsg);
    		}
    	}
    }
    
    private String setDrunkMsg(double BAC) {
    	String msg = "";
    	if (BAC <= 0.04) {
    		msg = "You aren't even drunk yet. Keep up the good work!";
    	}
    	else if (BAC > 0.04 && BAC <= 0.06) {
    		msg = "I bet you are feeling pretty good about yourself now.";
    	}
    	else if (BAC > 0.06 && BAC <= 0.10) {
    		msg = "Can you try walking straight? You will probably fail.";
    	}
    	else if (BAC > 0.06 && BAC <= 0.10) {
    		msg = "Can you try walking straight? You will probably fail.";
    	}
    	else if (BAC > 0.10 && BAC <= 0.13) {
    		msg = "Can you spell out pneumonia? You are pretty drunk.";
    	}
    	else if (BAC > 0.13 && BAC <= 0.16) {
    		msg = "Go find a toilet. Don't throw up on me.";
    	}
    	else if (BAC > 0.16 && BAC <= 0.20) {
    		msg = "You should stop drinking by now. Let's get real...";
    	}
    	else if (BAC > 0.20 && BAC <= 0.25) {
    		msg = "Yo someone needs to carry this one home.";
    	}
    	else if (BAC > 0.25 && BAC <= 0.4) {
    		msg = "Alright, we might need an ambulance";
    	}
    	else{
    		msg = "If you are not just clicking the button for fun, you are probably in the ICU.";
    	}
    	return msg;
    }
    
    private void executeThreads() {
    	ExecutorService ex = Executors.newCachedThreadPool();
    	for (UserThread u : allPlayers) {
    		ex.execute(u);
    	}
    	ex.shutdown();
    	while (!ex.isTerminated()) {}
    }
    
	// Read them cookies
	public Optional<String> readCookie(String key, HttpServletRequest request) {
	    return Arrays.stream(request.getCookies())
	      .filter(c -> key.equals(c.getName()))
	      .map(Cookie::getValue)
	      .findAny();
	}
}
