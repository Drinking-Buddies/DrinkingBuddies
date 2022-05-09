package com.drinkingbuddies.drinkingbuddies.threads;

import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;

public class UserThread implements Runnable{
	private String username;
	private String email;
	private String lobbyName;
	private int amountDrinked;
	private boolean isFinished = false;
	private int weight;
	private int BACValue = 0;
	private String gender;
	private dbUtility util;
	public UserThread(String username, String email, String lobbyName, int amountDrinked, String gender, int weight) {
		this.username = username;
		this.email = email;
		this.lobbyName = lobbyName;
		this.amountDrinked = amountDrinked;
		this.gender = gender;
		this.weight = weight;
		util = new dbUtility();
	}
	
	public void run() {
		// only do database stuff if it's not a guest
		if (!username.equals("Guest")){
			amountDrinked = util.getAmountDrinked(email, lobbyName);
		}
		
	}

	public synchronized int getAmountDrinked() {
		//System.out.println(username+": "+amountDrinked);
		return amountDrinked;
	}
	
	public String getEmail() {
		return email;
	}
	
	// we can change this to more than 1 later
	public synchronized void addShot() {
		if (!username.equals("Guest")){
			util.addDrink(email, lobbyName, 1);
		}else {
			amountDrinked += 1;
		}
	}

	public synchronized String getUsername() {
		return username;
	}
	
	public void finishSession() {
		isFinished = true;
	}

}
