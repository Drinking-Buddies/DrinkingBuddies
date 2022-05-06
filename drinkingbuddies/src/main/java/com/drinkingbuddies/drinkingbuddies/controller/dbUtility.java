package com.drinkingbuddies.drinkingbuddies.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class dbUtility {
	static public String DBUsername = "root"; 
	static public String DBPassword = "root"; 
	static public String url = "jdbc:mysql://localhost;3306/drinkingbuddies"; // temporary url 
	
	static public Pattern namePattern = Pattern.compile("^[ A-Za-z]+$"); 
	static public Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$"); 
	
	static public void sqlconnect () //basic SQL connection 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword); 
		
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
