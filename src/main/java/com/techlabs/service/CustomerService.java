package com.techlabs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techlabs.entity.Account;
import com.techlabs.entity.Customer;

public class CustomerService {
	private Customer customer;
	private DBConnection dbconnection;
	private Account account;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public DBConnection getConnection() {
		return dbconnection;
	}
	public void setConnection(DBConnection connection) {
		this.dbconnection = dbconnection;
	}
	
	public void addCustomer(Customer customer, DBConnection dbconnection) {
		Connection connection = dbconnection.connect();
		
		String sql = "INSERT INTO customer (first_name,last_name,email_id,password) VALUES(?,?,?,?)";
		
		try(PreparedStatement prepareStatement = connection.prepareStatement(sql)){
			prepareStatement.setString(1, customer.getCustomerFirstName());
			prepareStatement.setString(2, customer.getCustomerlastName());
			prepareStatement.setString(3, customer.getCustomerEmailID());
			prepareStatement.setString(4, customer.getCustomerPassword());
			int rowsAffected = prepareStatement.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Customer Added Successfully");
			}else {
				System.out.println("Customer Not Added");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public Customer getCustomerDetails(int customerID) {
		dbconnection = new DBConnection();
	    Connection connection = dbconnection.connect();
	    String sql = "SELECT * FROM customer WHERE customer_id = ?";
	    Customer customerDetails = null;

	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, customerID);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	        	
	        	customerDetails = new Customer(
	        			 resultSet.getInt("customer_id"),
	                    resultSet.getString("first_name"),
	                    resultSet.getString("last_name"),
	                    resultSet.getString("email_id"),
	                    resultSet.getString("password")
	                );
	           
	        } else {
	            System.out.println("No customer found with ID: " + customerID);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return customerDetails;
	}
	
	
	public List<Customer> getAllCustomers() {
		dbconnection = new DBConnection();
	    List<Customer> customers = new ArrayList<>();
	    String query = "SELECT c.customer_id, c.first_name, c.last_name, " +
	                   "a.account_number, a.account_type, a.balance " +
	                   "FROM customer c " +
	                   "LEFT JOIN accounts a ON c.customer_id = a.customer_id " +
	                   "ORDER BY c.customer_id";

	    try (Connection conn = dbconnection.connect();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            int customerId = rs.getInt("customer_id");
	            String firstName = rs.getString("first_name");
	            String lastName = rs.getString("last_name");

	            // Handle nullable fields for accounts
	            long accountNumber = rs.getLong("account_number");
	            if (rs.wasNull()) {
	                accountNumber = 0; 
	            }

	            String accountType = rs.getString("account_type");
	            if (accountType == null) {
	                accountType = "N/A";
	            }

	            int balance = rs.getInt("balance");
	            if (rs.wasNull()) {
	                balance = 0;
	            }

	            
	            Customer customer = new Customer(customerId, firstName, lastName, accountNumber, accountType, balance);

	            customers.add(customer);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 

	    return customers;
	}

	
	
	public boolean updateCustomerField(int customerId, String field, String value) {
	    String sql = "UPDATE customer SET " + field + " = ? WHERE customer_id = ?";
	    boolean isUpdated = false;

	    try (Connection connection = dbconnection.connect();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setString(1, value);
	        preparedStatement.setInt(2, customerId);
	        isUpdated = preparedStatement.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return isUpdated;
	}

	
	
	public Integer getCustomerIdByEmail(String email) {
        String sql = "SELECT customer_id FROM customer WHERE email_id = ?";
        Integer customerId = null;
        dbconnection = new DBConnection();
        try (Connection connection = dbconnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customerId = resultSet.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerId;
    }



	
	
	
	
	

	

	
	

}
