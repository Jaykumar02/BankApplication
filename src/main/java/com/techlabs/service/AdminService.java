package com.techlabs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techlabs.entity.Admin;
import com.techlabs.entity.Transaction;

public class AdminService {
	private Admin admin;
	private DBConnection dbconnection;
	public AdminService(Admin admin, DBConnection dbconnection) {
		super();
		this.admin = admin;
		this.dbconnection = dbconnection;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public DBConnection getConnection() {
		return dbconnection;
	}
	public void setConnection(DBConnection connection) {
		this.dbconnection = connection;
	}
	
	public void addAdmin() {
	    Connection connection = dbconnection.connect();
         
        String sql = "INSERT INTO admindetails (admin_name, admin_password) VALUES (?, ?)"; 
 
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) { 
            
        	preparedStatement.setString(1, admin.getAdminName()); 
            preparedStatement.setString(2, admin.getAdminPassword()); 
 
            int rowsAffected = preparedStatement.executeUpdate(); 
            if (rowsAffected > 0) { 
                System.out.println("Admin added successfully!"); 
            } else { 
                System.out.println("Failed to add Admin."); 
            } 
        } catch (SQLException e) { 
            e.printStackTrace();  
        } 
    }
	
	 public List<Transaction> getTransactions() {
	        List<Transaction> transactions = new ArrayList<>();
	        String sql = "SELECT transaction_id, customer_id, account_number, account_transfer_no, transaction_type, amount FROM transaction";
	        dbconnection = new DBConnection();
	        try (Connection connection = dbconnection.connect();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                Transaction transaction = new Transaction();
	                transaction.setTransactionId(resultSet.getInt("transaction_id"));
	                transaction.setCustomerId(resultSet.getInt("customer_id"));
	                transaction.setAccountNumber(resultSet.getLong("account_number"));
	                transaction.setAccountTransferNo(resultSet.getLong("account_transfer_no"));
	                transaction.setTransactionType(resultSet.getString("transaction_type"));
	                transaction.setAmount(resultSet.getDouble("amount"));

	                transactions.add(transaction);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return transactions;
	    }
	
	
	

}
