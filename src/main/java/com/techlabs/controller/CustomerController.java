package com.techlabs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.entity.Customer;
import com.techlabs.service.CustomerService;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	  String fieldToUpdate = request.getParameter("fieldToUpdate");
    	    String newValue = request.getParameter("newValue");

    	    HttpSession session = request.getSession();
    	    String sessionEmail = (String) session.getAttribute("username");

    	    if (sessionEmail == null) {
    	        response.sendRedirect("Login.jsp");
    	        return;
    	    }

    	    Integer customerId = customerService.getCustomerIdByEmail(sessionEmail);

    	    if (customerId == null) {
    	        response.sendRedirect("Login.jsp");
    	        return;
    	    }

    	    boolean isUpdated = customerService.updateCustomerField(customerId, fieldToUpdate, newValue);

    	    if (isUpdated) {
    	        response.sendRedirect("CustomerHome.jsp?success=true");
    	    } else {
    	        response.sendRedirect("EditProfile.jsp?error=true");
    	    }
    	}
    
}