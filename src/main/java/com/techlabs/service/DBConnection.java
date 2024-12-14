package com.techlabs.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private Connection connection = null;
	private Statement statement;
	
	
	public Connection connect() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_application","root","root");
			System.out.println("connection establish");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		
		
	}

}
