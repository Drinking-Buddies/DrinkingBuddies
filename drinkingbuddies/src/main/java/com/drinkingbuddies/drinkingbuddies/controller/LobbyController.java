package com.drinkingbuddies.drinkingbuddies.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LobbyController {
	private ArrayList<Player> allPlayers = new ArrayList<>();
	
    @RequestMapping(value = "/lobby", method = RequestMethod.GET)
    public String lobbyPage(){
        return "lobby";
    }
    
    @RequestMapping(value = "/lobby", method = RequestMethod.POST)
    public void addDrink(@RequestParam String userID) {
    	for (Player p : allPlayers) {
    		if (p.getName().equals(userID)) {
    			p.addShot();
    		}
    	}
    }
	
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
}
