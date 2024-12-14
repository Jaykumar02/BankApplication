package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techlabs.entity.Transaction;
import com.techlabs.service.AdminService;
import com.techlabs.service.DBConnection;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	AdminService adminService;
	 public void init() throws ServletException {
	        super.init();
	        DBConnection dbConnection = new DBConnection(); 
	        this.adminService = new AdminService(null, dbConnection);
	    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Transaction> transactions = adminService.getTransactions();
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("AdminHome.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
