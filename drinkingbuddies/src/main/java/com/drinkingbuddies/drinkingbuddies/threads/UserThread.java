package com.drinkingbuddies.drinkingbuddies.threads;

import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;

public class UserThread extends Thread{
	private String username;
	private String email;
	private String lobbyName;
	private int amountDrinked;
	private int seatNum;
	private boolean isFinished = false;
	private dbUtility util = new dbUtility();
	public UserThread(String username, String email, String lobbyName, int amountDrinked, int seatNum) {
		this.username = username;
		this.lobbyName = lobbyName;
		this.amountDrinked = amountDrinked;
		this.seatNum = seatNum;
	}
	
	public void run() {
		try {
			while (!isFinished) {
				amountDrinked = util.getAmountDrinked(email, lobbyName);
				System.out.println(amountDrinked);
				Thread.sleep(500);
			}
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
		System.out.println("Add shot");
		util.addDrink(email, lobbyName, 1);
	}

	public String getUsername() {
		return username;
	}
	
	public void finishSession() {
		System.out.println("leave");
		isFinished = true;
	}
}
