package com.techlabs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService {
	private String userName;
	private String password;
	private String role;
	
	private DBConnection dbconnection;
	
	
	public LoginService(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	
	 public boolean validateCredentials(Connection connection) throws Exception {
	        String query = null;

	       
	        if ("Admin".equalsIgnoreCase(role)) {
	            query = "SELECT * FROM admindetails WHERE admin_name = ? AND admin_password = ?";
	        } else if ("Customer".equalsIgnoreCase(role)) {
	            query = "SELECT * FROM customer WHERE email_id = ? AND password = ?";
	        } else {
	        	System.out.println("Invalid Credentials");
	            throw new IllegalArgumentException("Invalid role: " + role);
	        }
	        
	        
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, userName); 
	            statement.setString(2, password);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                return resultSet.next();
	            }
	        }
	

	
	 }

}
