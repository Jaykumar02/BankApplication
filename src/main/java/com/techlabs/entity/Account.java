package com.techlabs.entity;

public class Account {
	private int customerID;
	private long accountNumber;
	private String accountType;
	private double amount;
	
	public Account(int customerID, long accountNumber2, String accountType, double amount) {
		super();
		this.customerID = customerID;
		this.accountNumber = accountNumber2;
		this.accountType = accountType;
		this.amount = amount;
	}
	
	

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}



	public Account(long accountNumber, String accountType) {
	    super();
	    this.accountNumber = accountNumber;
	    this.accountType = accountType;
	}

	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	  public String toString() {
        return "Account Number: " + accountNumber + ", Account Type: " + accountType + ", Balance: " + amount;
    }
	
	
	
	

}
