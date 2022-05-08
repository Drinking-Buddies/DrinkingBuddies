package com.drinkingbuddies.drinkingbuddies.controller.Util;

import java.util.LinkedList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbUtility {
	private static final String DB = "jdbc:mysql://localhost:3306/buddy";
	private static final String DBUserName = "root";
	private static final String DBPassword = "root";
	private Connection conn;
	
	public dbUtility(){
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		try {
			conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
		}catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
	}
	
 
	public User getUser (String email) {
		
		User user = new User();
		
		
		String sql = "SELECT * FROM Users WHERE email = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql); 
			
			//Set IN parameters
			stmt.setString(1, email);
			
			//Execute stored procedure
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user.setEmail(rs.getString("email")); 
				user.setUsername(rs.getString("username")); 
				user.setPass(rs.getString("pass")); 
				user.setBirthday(rs.getString("birthday"));
				user.setPhone(rs.getString("phone"));
				user.setEmergency_phone(rs.getString("emergency_phone"));
				user.setWeight(rs.getInt("weight"));
				user.setBio(rs.getString("bio")); 
			}	
			rs.close();
			stmt.close();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		return user;
	}
	
	public void newUser (String email, String username, String pass, String birthday, String phone, String emergency_phone, int weight, String bio) {
		
		String sql = "{ CALL NewUser(?, ?, ?, ?, ?, ?, ?, ?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, email);
			stmt.setString(2, username);
			stmt.setString(3, pass);
			stmt.setString(4, birthday);
			stmt.setString(5, phone);
			stmt.setString(6, emergency_phone);
			stmt.setInt(7, weight);
			stmt.setString(8, bio);
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
	}
	
	public boolean userExists(String email, String pass) {
		boolean result = false;
		
		String sql = "{ CALL UserExists(?, ?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, email);
			stmt.setString(2, pass);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getInt(1) != 0) {
					result = true;
				}
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public void newEvent (String start_time, String event_name) {
		
		String sql = "{ CALL NewEvent(?, ?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, start_time);
			stmt.setString(2, event_name);
			
			stmt.execute();
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
	} 
	
	public void joinEvent (String email, String event_name, int amount)
	{
		
		String sql = "{ CALL JoinEvent(?, ?, ?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, email);
			stmt.setString(2, event_name);
			stmt.setInt(3, amount);
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
	}
	
	public boolean eventExists (String event_name)
	{
		boolean result = false;
		
		String sql = "{ CALL EventExists(?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, event_name);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0) {
					result = true;
				}
			}
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public void addDrink (String email, String event_name, int amount)
	{
		
		System.out.println("Adding drink");
		
		String sql = "{ CALL AddDrink(?, ?, ?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setString(1, email);
			stmt.setString(2, event_name);
			stmt.setInt(3, amount);
			stmt.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
	}	
	
	public int getAmountDrinked (String email, String event_name)
	{
		int amount = 0;
		try {
			String sql = "{ CALL GetAmountDrinked(?, ?) }";

			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, email);
			stmt.setString(2, event_name);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				amount = rs.getInt(1);
			}
			rs.close();
			stmt.close();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		return amount;
	}

	public LinkedList<String> getParticipants (String event_name)
	{
		LinkedList<String> result = new LinkedList<String>();
		
		String sql = "{ CALL GetParticipants(?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, event_name);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
       			result.add(rs.getString(1));
       		}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public void friendRequest (String requester, String receiver)
	{
		
		String sql = "{ CALL FriendRequest(?, ?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, requester);
			stmt.setString(2, receiver);
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
	} 
	
	public void acceptFriend (String requester, String receiver)
	{
		
		String sql = "{ CALL AcceptFriend(?, ?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, requester);
			stmt.setString(2, receiver);
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
	} 
	
	public LinkedList<String> getFriends (String email)
	{
		LinkedList<String> result = new LinkedList<String>();
		
		String sql = "{ CALL GetFriends(?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
       			result.add(rs.getString(1));
       		}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public boolean areFriends(String email1, String email2) {
		boolean result = false;
		
		String sql = "{ CALL AreFriends(?, ?) }";
        
		try{
			CallableStatement stmt = conn.prepareCall(sql);

			stmt.setString(1, email1);
			stmt.setString(2, email2);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getBoolean(1);
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	} 
	
	
}
