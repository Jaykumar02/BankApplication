package com.techlabs.entity;

public class Customer {

    private int customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmailID;
    private String customerPassword;
    private long accountNumber;
    private String accountType;
    private int balance;

 
    public Customer(int customerId, String customerFirstName, String customerLastName, String customerEmailID, String customerPassword) {
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmailID = customerEmailID;
        this.customerPassword = customerPassword;
    }
    
    

    public Customer(int customerId, String customerFirstName, String customerLastName, long accountNumber,
			String accountType, int balance) {
		super();
		this.customerId = customerId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}



	public long getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getAccountType() {
		return accountType;
	}



	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	public int getBalance() {
		return balance;
	}



	public void setBalance(int balance) {
		this.balance = balance;
	}



	public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerlastName() {
        return customerLastName;
    }

    public void setCustomerlastName(String customerlastName) {
        this.customerLastName = customerlastName;
    }

    public String getCustomerEmailID() {
        return customerEmailID;
    }

    public void setCustomerEmailID(String customerEmailID) {
        this.customerEmailID = customerEmailID;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }



	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + ", customerEmailID=" + customerEmailID + ", customerPassword=" + customerPassword
				+ "]";
	}
}
