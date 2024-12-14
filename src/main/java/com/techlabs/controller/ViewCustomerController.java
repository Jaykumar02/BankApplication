package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techlabs.entity.Customer;
import com.techlabs.service.CustomerService;

/**
 * Servlet implementation class ViewCustomerController
 */
@WebServlet("/ViewCustomerController")
public class ViewCustomerController extends HttpServlet {
	private CustomerService customerService;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 customerService = new CustomerService();
		 List<Customer> customers = customerService.getAllCustomers();
		 
		 request.setAttribute("customers", customers);
		 
		 
		  request.getRequestDispatcher("AdminHome.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
