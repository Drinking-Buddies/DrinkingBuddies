package com.drinkingbuddies.drinkingbuddies.controller.Util;

import java.util.ArrayList;

public class User {
	private String email;
	private String username;
	private String pass;
    private String birthday;
    private String phone;
    private String emergency_phone;
    private int weight;
    private String bio;
	private String gender;
    private ArrayList<User> friends;

    public User() {}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User(String email, String username, String pass, String birthday, String phone, String emergency_phone,
				int weight, String bio, String gender) {
		this.email = email;
		this.username = username;
		this.pass = pass;
		this.birthday = birthday;
		this.phone = phone;
		this.emergency_phone = emergency_phone;
		this.weight = weight;
		this.bio = bio;
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmergency_phone() {
		return emergency_phone;
	}
	
	public void setEmergency_phone(String emergency_phone) {
		this.emergency_phone = emergency_phone;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}
    
	public int calcBAC(int alcohol)
	{
		int BAC = 0; 
		
		if (this.getBio() == "female")
		{
			BAC = (int) (alcohol / (this.getWeight() * 0.55)) * 100; 
		}
		else 
		{
			BAC = (int) (alcohol / (this.getWeight() * 0.68)) * 100; 
		}
		
		return BAC;
	}
	
}



