package com.techlabs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techlabs.entity.Customer;
import com.techlabs.service.CustomerService;
import com.techlabs.service.DBConnection;

/**
 * Servlet implementation class AddCustomerController
 */
@WebServlet("/AddCustomerController")
public class AddCustomerController extends HttpServlet {
	private Customer customer;
	private CustomerService customerService;
	private DBConnection connection = new DBConnection();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerFName = request.getParameter("customerFirstName");
		String customerLName = request.getParameter("customerlastName");
		String customerEmailId = request.getParameter("customerEmailID");
		String customerPassword = request.getParameter("customerPassword");
		
		
		
		customer = new Customer(0, customerFName, customerLName, customerEmailId, customerPassword);
		customerService = new CustomerService();
		
		try {
			customerService.addCustomer(customer, connection);
			response.getWriter().write("Customer Added Successfully");
			 response.sendRedirect("AdminHome.jsp?status=success");
			 request.setAttribute("message", "Customer Added Successfully");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("adminHome.jsp");
			 dispatcher.forward(request, response);
		}catch(Exception e) {
			response.getWriter().write(e.getMessage());
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
