package com.drinkingbuddies.drinkingbuddies.controller.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class dbUtility {
	private static final String DB = "jdbc:mysql://localhost:3306/buddy";
	private static final String DBUserName = "root";
	private static final String DBPassword = "root";
 
	public void newUser (String email, String username, String pass, String birthday, String phone, String emergency_phone, int weight, String bio) {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL NewUser(?, ?, ?, ?, ?, ?, ?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

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
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL UserExists(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, email);
			stmt.setString(2, pass);
			
			ResultSet rs = stmt.executeQuery();
			
			result = rs.getBoolean(1);
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public int newEvent (String start_time, String event_name) {
		int result = -1;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL NewEvent(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, start_time);
			stmt.setString(2, event_name);
			
			ResultSet rs = stmt.executeQuery();
			
			result = rs.getInt(1);
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	} 
	
	public void joinEvent (String email, int event_id, int amount)
	{
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL JoinEvent(?, ?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, email);
			stmt.setInt(2, event_id);
			stmt.setInt(3, amount);
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
	
	}
	
	public LinkedList<String> getParticipants (int event_id)
	{
		LinkedList<String> result = new LinkedList<String>();
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL GetParticipants(?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setInt(1, event_id);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
       			result.add(rs.getString(1));
       		}

		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public void friendRequest (String requester, String receiver)
	{
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL FriendRequest(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, requester);
			stmt.setString(2, receiver);
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
	} 
	
	public void acceptFriend (String requester, String receiver)
	{
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL AcceptFriend(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, requester);
			stmt.setString(2, receiver);
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
	} 
	
	public LinkedList<String> getFriends (int email)
	{
		LinkedList<String> result = new LinkedList<String>();
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL GetFriends(?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setInt(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
       			result.add(rs.getString(1));
       		}

		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public boolean areFriends(String email1, String email2) {
		boolean result = false;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL AreFriends(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, email1);
			stmt.setString(2, email2);
			
			ResultSet rs = stmt.executeQuery();
			
			result = rs.getBoolean(1);
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
}
