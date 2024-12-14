package com.techlabs.entity;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int customerId;
    private long accountNumber;
    private long accountTransferNo;
    private String transactionType;
    private double amount;
    private Timestamp transactionDate; // Add the transactionDate field

    // Getters and setters for the fields

   


    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public long getAccountTransferNo() {
        return accountTransferNo;
    }
    public void setAccountTransferNo(long accountTransferNo) {
        this.accountTransferNo = accountTransferNo;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
