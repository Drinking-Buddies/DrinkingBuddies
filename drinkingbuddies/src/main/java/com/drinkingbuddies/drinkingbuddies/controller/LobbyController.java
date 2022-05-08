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
	
    @RequestMapping(value = "/lobby", method = RequestMethod.GET)
    public String lobbyPage(HttpServletRequest request){
    	// Get participants bitch!!!!!!!!!!!!!
    	String lobbyName = readCookie("lobbyName", request).get();
    	LinkedList<String> allPlayerEmails = util.getParticipants(lobbyName);
    	
    	// Need to check if the user is actually me
    	String myName = readCookie("userName",request).get();
    	
    	
    	// Need to construct player objects and get them into the list
    	User me = null;
    	int myShots = 0;
    	int seat = 2;
    	for (String e : allPlayerEmails) {
    		User u = util.getUser(e);
    		String username = u.getUsername();
    		int amount = util.getAmountDrinked(u.getEmail(), lobbyName);
    		if (username.equals(myName)) {
    			myShots = amount;
    		}else {
    			UserThread otherPlayer = new UserThread(username, lobbyName, amount, seat);
        		allPlayers.add(otherPlayer);
        		seat++;
    		}
    	}
    	UserThread mePlayer = new UserThread(myName, lobbyName, myShots, 1);
    	allPlayers.add(0, mePlayer);
    	executeThreads();
        return "lobby";
    }
    
    private static void executeThreads() {
    	ExecutorService ex = Executors.newCachedThreadPool();
    	for (UserThread u : allPlayers) {
    		ex.execute(u);
    	}
    	ex.shutdown();
    }
    
    @RequestMapping(value = "/lobby", method = RequestMethod.POST)
    public void addDrink(@RequestParam String userID) {
    	for (UserThread u : allPlayers) {
    		if (u.getName().equals(userID)) {
    			u.addShot();
    		}
    	}
    }
	
    /*
	public class Player{
		private String name;
		private int shots;
		private int seatNum;
		
		Player(String name, int shots, int seatNum){
			this.name = name;
			this.shots = shots;
			this.seatNum = seatNum;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getShots() {
			return shots;
		}
		public void setShots(int shots) {
			this.shots = shots;
		}
		public void addShot() {
			this.shots += 1;
		}
		public void addShots(int shots) {
			this.shots += shots;
		}
	}
	*/
	
	// Read them cookies
	public Optional<String> readCookie(String key, HttpServletRequest request) {
	    return Arrays.stream(request.getCookies())
	      .filter(c -> key.equals(c.getName()))
	      .map(Cookie::getValue)
	      .findAny();
	}
}
