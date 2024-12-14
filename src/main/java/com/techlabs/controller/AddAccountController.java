package com.techlabs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techlabs.entity.Account;
import com.techlabs.service.AccountService;
import com.techlabs.service.CustomerService;
import com.techlabs.service.DBConnection;

/**
 * Servlet implementation class AddAccountController
 */
@WebServlet("/AddAccountController")
public class AddAccountController extends HttpServlet {
	private Account account;
	private DBConnection dbconnection;
	private AccountService service;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerIDStr = request.getParameter("customerId");
		String accountNumberStr = request.getParameter("accountNumber");
		String accounttype = request.getParameter("accountType");
		String amountStr = request.getParameter("amount");
		
		int customerID = Integer.parseInt(customerIDStr);
		 long accountNumber = Long.parseLong(accountNumberStr);
		double amount = Double.parseDouble(amountStr);
		
		account = new Account(customerID, accountNumber, accounttype, amount);
		service = new AccountService();
		
		try {
			
			boolean isAccountAdded = service.addBankAccount(account);
			 if (isAccountAdded) {
				 request.setAttribute("message", "Bank Account Added Successfully!");
	            } else {
	            	 request.setAttribute("message", "Failed to add bank account.");
	            	 
	            }
		}catch (NumberFormatException e) {
            
            response.getWriter().println("Error: Invalid input for customer ID, account number, or amount.");
        } catch (Exception e) {
            
            response.getWriter().println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
		
		request.getRequestDispatcher("/AdminHome.jsp").forward(request, response);
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
