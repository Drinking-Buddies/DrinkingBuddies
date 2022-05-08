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
 
	public User getUser (String email) {
		
		User user = new User();
		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "SELECT * FROM Users WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setString(1, email);
								
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					user.setEmail(rs.getString("email")); 
					user.setUsername(rs.getString("username")); 
					user.setGender(rs.getString("sex"));
					user.setPass(rs.getString("pass")); 
					user.setBirthday(rs.getString("birthday"));
					user.setPhone(rs.getString("phone"));
					user.setEmergency_phone(rs.getString("emergency_phone"));
					user.setWeight(rs.getInt("weight"));
					user.setBio(rs.getString("bio")); 
				}			
				
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
				return null;
			}
		return user;
	}
	
	public void newUser (String email, String username, String pass, String birthday, String phone, String emergency_phone, int weight, String bio, String gender) {
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL NewUser(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, email);
			stmt.setString(2, username);
			stmt.setString(3, pass);
			stmt.setString(4, gender);
			stmt.setString(5, birthday);
			stmt.setString(6, phone);
			stmt.setString(7, emergency_phone);
			stmt.setInt(8, weight);
			stmt.setString(9, bio);
			
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
	}
	
	public boolean userExists(String email, String pass) {
		boolean result = false;
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL UserExists(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, email);
			stmt.setString(2, pass);
			
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
	
	public void updateUsername(String email, String changeTo) {		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "UPDATE Users SET username = ? WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setString(1, changeTo);
				stmt.setString(2, email);
				
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
	}
	
	public void updatePass(String email, String changeTo) {		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "UPDATE Users SET pass = ? WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setString(1, changeTo);
				stmt.setString(2, email);
				
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
	}
	
	public void updateGender(String email, String changeTo) {		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "UPDATE Users SET sex = ? WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setString(1, changeTo);
				stmt.setString(2, email);
				
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
	}
	
	public void updateBirthday(String email, String changeTo) {		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "UPDATE Users SET birthday = ? WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setString(1, changeTo);
				stmt.setString(2, email);
				
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
	}
	
	public void updatePhone(String email, String changeTo) {		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "UPDATE Users SET phone = ? WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setString(1, changeTo);
				stmt.setString(2, email);
				
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
	}
	
	public void updateEmergencyPhone(String email, String changeTo) {		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "UPDATE Users SET emergency_phone = ? WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setString(1, changeTo);
				stmt.setString(2, email);
				
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
	}
	
	public void updateWeight(String email, int changeTo) {		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "UPDATE Users SET weight = ? WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setInt(1, changeTo);
				stmt.setString(2, email);
				
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
	}
	
	public void updateBio(String email, String changeTo) {		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "UPDATE Users SET bio = ? WHERE email = ?";
		
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				 PreparedStatement stmt = conn.prepareStatement(sql);) {
				
				stmt.setString(1, changeTo);
				stmt.setString(2, email);
				
				stmt.executeUpdate();
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
	}
	
	public void newEvent (String start_time, String event_name) {
		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL NewEvent(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, start_time);
			stmt.setString(2, event_name);
			
			stmt.execute();
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
	} 
	
	public void joinEvent (String email, String event_name, int amount)
	{
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL JoinEvent(?, ?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

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
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL EventExists(?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

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
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL AddDrink(?, ?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

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
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL GetAmountDrinked(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, email);
			stmt.setString(2, event_name);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		return amount;
	}

	public LinkedList<String> getParticipants (String event_name)
	{
		LinkedList<String> result = new LinkedList<String>();
		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL GetParticipants(?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, event_name);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
       			result.add(rs.getString(1));
       		}

		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public String friendRequest (String requester, String receiver)
	{
		try {
            Class.forName("com.mysql.jdbc.Driver");
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
			System.out.println ("SQLException:" + sqle.getMessage());
			return "Invalid email...";
		}
		return "Request sent successfully.";
	} 
	
	public void acceptFriend (String requester, String receiver)
	{
		try {
            Class.forName("com.mysql.jdbc.Driver");

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
	
	public void removeFriend (String requester, String receiver)
	{ 
		boolean result = false;
		
		if (areFriends(requester, receiver))
		{
			try {
	            Class.forName("com.mysql.jdbc.Driver");
	
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
			
			String sql = "DELETE FROM Friendships WHERE requester = ? AND receiver = ?";
	        
			try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
				PreparedStatement p = conn.prepareStatement(sql);) {
	
				p.setString(1, requester);
				p.setString(2, receiver);
				p.executeUpdate();
//				if (!rs.next())
//				{
//					sql = "DELETE FROM Friendships WHERE requester = ? AND receiver = ?";
//					try (Connection conn1 = DriverManager.getConnection(DB, DBUserName, DBPassword);
//							PreparedStatement p1 = conn1.prepareStatement(sql);) {
//
//						p1.setString(1, receiver);
//						p1.setString(2, requester);
//
//						ResultSet rs1 = p1.executeQuery();
//						if (rs1.next())
//						{
//							return;
//						}
//					}
//
//				}
			} 
			catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
		}
		
	} 

	public LinkedList<String> getPendingRequests(String email){
		LinkedList<String> result = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "{Call GetPendingFriend(?)}";
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword)){
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		}catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
			return null;
		}
		return result;
	}

	public LinkedList<String> getFriends (String email)
	{
		LinkedList<String> result = new LinkedList<String>();
		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();	
        }
		
		String sql = "{ CALL GetFriends(?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
       			result.add(rs.getString(1));
       		}

		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return result;
	}
	
	public static boolean areFriends(String email1, String email2) {
		boolean result = false;
		
		try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String sql = "{ CALL AreFriends(?, ?) }";
        
		try (Connection conn = DriverManager.getConnection(DB, DBUserName, DBPassword);
			 CallableStatement stmt = conn.prepareCall(sql);) {

			stmt.setString(1, email1);
			stmt.setString(2, email2);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getBoolean(1);
			}
			
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		return result;
	} 
	
	
}
