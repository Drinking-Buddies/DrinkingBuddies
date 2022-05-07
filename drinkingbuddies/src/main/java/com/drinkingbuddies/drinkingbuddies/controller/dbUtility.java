package com.drinkingbuddies.drinkingbuddies.controller;

import java.util.Map;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.catalina.User;
import org.graalvm.compiler.nodes.ReturnNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.baeldung.jdbc")

public class dbUtility {
	@Bean
	public DataSource mysqlDatasource()
	{
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver"); 
		datasource.setUrl("jdbc:mysql://localhost;3306/drinkingbuddies"); // temporary url 
		datasource.setUsername("root"); 
		datasource.setPassword("root"); 
		
		return datasource; 
		
	}
//	static public String DBUsername = "root"; 
//	static public String DBPassword = "root"; 
//	static public String url = "jdbc:mysql://localhost;3306/drinkingbuddies"; 
	
	static public Pattern namePattern = Pattern.compile("^[ A-Za-z]+$"); 
	static public Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$"); 
	
	public void setdatasource (DataSource datasource) // put this into each Class (User, friends, ...) 
	{
		 this.simpleJdbcTemplate = new SimpleJdbcTemplate(datasource);
	     this.simpleJdbcCall = new SimpleJdbcCall(datasource).withProcedureName(Procedure_name); //put stored procedures instead of Procedure_name 
	}
	 
	public void insertUser (String email, String username, String pass, String birthday, String phone, String emergency_phone, int weight) 
	//reference: 12.5.5 in https://docs.spring.io/spring-framework/docs/3.0.0.M4/reference/html/ch12s05.html 
	{
		MapSqlParameterSource in = new MapSqlParameterSource(); 
		
		in.addValue("email", email); 
		in.addValue("username", username); 
		in.addValue("pass", pass);
		in.addValue("birthday", birthday);
		in.addValue("phone", phone); 
		in.addValue("emergency_phone", emergency_phone);
		in.addValue("weight", weight);
		
		simpleJdbcCall.execute(in); //simpleJdbcCall is from the setdatasource function above 
		
		User user = new User(email, username, pass, birthday, phone, emergency_phone, weight); 
//		user.setemail(email); 
//		user.setusername(username); 
//		user.setpassword(pass); 
//		user.setbirthday(birthday);
//		user.setphone(phone); 
//		user.setemergencyphone(emergency_phone); 
//		user.setweight(weight); 
	}
	
	public boolean userlogin (String email, String pass)
	{
		MapSqlParameterSource in = new MapSqlParameterSource(); 
		
		in.addValue("email", email); 
		in.addValue("pass", pass);
		
		Map out = simpleJdbcCall.execute(in); 
		
		return (boolean) out.get("valid");
	} 
	
	public void friendrequest (String requester, String receiver)
	{
		MapSqlParameterSource in = new MapSqlParameterSource(); 
		
		in.addValue("requester", requester); 
		in.addValue("receiver", receiver);
		
		simpleJdbcCall.execute(in); 
		
	} 
	
	public void acceptfriend (String requester, String receiver)
	{
		MapSqlParameterSource in = new MapSqlParameterSource(); 
		
		in.addValue("requester", requester); 
		in.addValue("receiver", receiver);
		
		simpleJdbcCall.execute(in); 
		
	} 

	public int newevent (String starttime)
	{
		MapSqlParameterSource in = new MapSqlParameterSource(); 
		
		in.addValue("start_time", starttime); 
		
		Map out = simpleJdbcCall.execute(in); 
		
		return (int) out.get("id"); 
	} 
	
	public void joinevent (String email, String eventid, int amount)
	{
		MapSqlParameterSource in = new MapSqlParameterSource(); 
		s
		in.addValue("email", email); 
		in.addValue("event_id", eventid); 
		in.addValue("amount", amount); 
		
		simpleJdbcCall.execute(in); 
	
	}
}
