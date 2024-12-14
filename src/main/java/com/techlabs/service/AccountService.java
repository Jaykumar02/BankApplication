package com.techlabs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.techlabs.entity.Account;
import com.techlabs.entity.Customer;

public class AccountService {
	private Customer customer;
	private DBConnection dbconnection;
	private Account account;
	
	public boolean addBankAccount(Account account) {
        dbconnection = new DBConnection(); 
        Connection connection = dbconnection.connect();

        String sql = "INSERT INTO accounts (customer_id, account_number, account_type, balance) VALUES (?, ?, ?, ?)";
        boolean isAdded = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	
            preparedStatement.setInt(1, account.getCustomerID());
            preparedStatement.setLong(2, account.getAccountNumber());
            preparedStatement.setString(3, account.getAccountType());
            preparedStatement.setDouble(4, account.getAmount());
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bank account added successfully.");
                isAdded = true;
            } else {
                System.out.println("Failed to add bank account.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAdded;
    }

}
