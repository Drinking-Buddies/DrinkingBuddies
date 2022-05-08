package com.drinkingbuddies.drinkingbuddies.threads;

import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;

public class UserThread extends Thread{
	private String username;
	private String lobbyName;
	private int amountDrinked;
	private int seatNum;
	private dbUtility util = new dbUtility();
	public UserThread(String username, String lobbyName, int amountDrinked, int seatNum) {
		this.username = username;
		this.lobbyName = lobbyName;
		this.amountDrinked = amountDrinked;
		this.seatNum = seatNum;
	}
	
	public void run() {
		amountDrinked = util.getAmountDrinked(username, lobbyName);
		try {
			Thread.sleep(500);
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
		util.addDrink(username, lobbyName, 1);
	}
}
