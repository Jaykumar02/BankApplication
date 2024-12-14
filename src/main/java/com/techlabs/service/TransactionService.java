package com.techlabs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techlabs.entity.Account;
import com.techlabs.entity.Transaction;

public class TransactionService {
    private DBConnection dbConnection = new DBConnection();

    
    public List<Account> getCustomerAccounts(String email) {
       

        List<Account> accounts = new ArrayList<>();
        String query = "SELECT account_number, account_type FROM accounts WHERE customer_id = " +
                       "(SELECT customer_id FROM customer WHERE email_id = ?)";
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long accountNumber = rs.getLong("account_number");
                String accountType = rs.getString("account_type");
                System.out.println(accountNumber);
                System.out.println(accountType);

                
                Account account = new Account(accountNumber, accountType);
                accounts.add(account);
                System.out.println(accounts);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }
    
    
  

   
    public boolean credit(long accountNumber, int amount) {
        try (Connection conn = dbConnection.connect()) {
            conn.setAutoCommit(false);

            System.out.println("inside credit");
            String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            try (PreparedStatement stmt = conn.prepareStatement(creditQuery)) {
                stmt.setInt(1, amount);
                stmt.setLong(2, accountNumber);
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                	
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public boolean debit(long accountNumber, int amount) {
        try (Connection conn = dbConnection.connect()) {
            conn.setAutoCommit(false);
            System.out.println("inside debit");
            
            String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? AND balance >= ?";
            try (PreparedStatement stmt = conn.prepareStatement(debitQuery)) {
                stmt.setInt(1, amount);
                stmt.setLong(2, accountNumber);
                stmt.setInt(3, amount);
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                	
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    
    public boolean transfer(long fromAccount, long toAccount, int amount) {
        try (Connection conn = dbConnection.connect()) {
            conn.setAutoCommit(false);

            
            boolean debited = debit(fromAccount, amount);

           
            boolean credited = credit(toAccount, amount);

            if (debited && credited) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public int getCustomerId(String email) {
        String query = "SELECT customer_id FROM customer WHERE email_id = ?";
        int customerId = 0;

        try (Connection conn = dbConnection.connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customerId = rs.getInt("customer_id"); // Fetch the customer_id
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerId;
    }
    
    
    public boolean saveTransaction(Transaction transaction) {
        // Query to check if the customer_id exists in the customer table
        String checkCustomerQuery = "SELECT COUNT(*) FROM customer WHERE customer_id = ?";
        
        // Query to insert the transaction
        String insertTransactionQuery = "INSERT INTO transaction (customer_id, account_number, account_transfer_no, transaction_type, amount) "
                                       + "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkCustomerQuery)) {
            
            
            checkStmt.setInt(1, transaction.getCustomerId());
            ResultSet rs = checkStmt.executeQuery();
            
           
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Invalid customer ID: " + transaction.getCustomerId());
                return false; 
            }

            
            try (PreparedStatement stmt = conn.prepareStatement(insertTransactionQuery)) {
                stmt.setInt(1, transaction.getCustomerId());
                stmt.setLong(2, transaction.getAccountNumber());
                stmt.setLong(3, transaction.getAccountTransferNo());
                stmt.setString(4, transaction.getTransactionType());
                stmt.setDouble(5, transaction.getAmount());
                
                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0; 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
    
    public List<Transaction> getTransactionHistory(int customerId) {
        List<Transaction> transactionHistory = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE customer_id = ? ORDER BY transaction_date DESC"; // Example query, adjust it to your schema

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setAccountNumber(rs.getLong("account_number"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date")); 
                transactionHistory.add(transaction);
                System.out.println(transactionHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionHistory;
    }
    
    
    
    

    
    
}