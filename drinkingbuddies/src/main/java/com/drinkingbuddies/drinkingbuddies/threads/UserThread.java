package com.drinkingbuddies.drinkingbuddies.threads;

import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;

public class UserThread implements Runnable{
	private String username;
	private String email;
	private String lobbyName;
	private int amountDrinked;
	private int seatNum;
	private boolean isFinished = false;
	private dbUtility util;
	public UserThread(String username, String email, String lobbyName, int amountDrinked, int seatNum) {
		this.username = username;
		this.email = email;
		this.lobbyName = lobbyName;
		this.amountDrinked = amountDrinked;
		this.seatNum = seatNum;
		util = new dbUtility();
	}
	
	public void run() {
		amountDrinked = util.getAmountDrinked(email, lobbyName);
		System.out.println(username+": "+amountDrinked);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getAmountDrinked() {
		return amountDrinked;
	}

	public int getSeatNum() {
		return seatNum;
	}
	
	// we can change this to more than 1 later
	public void addShot() {
		util.addDrink(email, lobbyName, 1);
	}

	public String getUsername() {
		return username;
	}
	
	public void finishSession() {
		isFinished = true;
	}
}
