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

@WebServlet("/viewCustomerController")
public class SearchCustomerController extends HttpServlet {
	private CustomerService service;
   

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String customerIdStr = request.getParameter("customerId");
    	 service  = new CustomerService();
        
        try {
        	int customerId = Integer.parseInt(customerIdStr);
            Customer customer = service.getCustomerDetails(customerId);

            if (customer != null) {
               
                request.setAttribute("customerFirstName", customer.getCustomerFirstName());
                request.setAttribute("customerLastName", customer.getCustomerlastName());
                request.setAttribute("customerEmail", customer.getCustomerEmailID());
            } else {
                request.setAttribute("errorMessage", "Customer not found!");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Customer ID!");
        }

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
        dispatcher.forward(request, response);

        
       

       
    }
}
