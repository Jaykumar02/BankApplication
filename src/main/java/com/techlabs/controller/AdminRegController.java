package com.techlabs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techlabs.entity.Admin;
import com.techlabs.service.AdminService;
import com.techlabs.service.DBConnection;


@WebServlet("/AdminRegController")
public class AdminRegController extends HttpServlet {
	
	private Admin admin;
	private DBConnection connection = new DBConnection();
	private AdminService service;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username = request.getParameter("adminName");
		    String password = request.getParameter("adminPassword");
		    String confirmPassword = request.getParameter("confirmPassword");
		    
		    System.out.println("abc");
		    if (!password.equals(confirmPassword)) {
		        response.getWriter().write("Passwords do not match!");
		        return;
		    }
		   
		    Admin admin = new Admin(username, password);
		    service = new AdminService(admin, connection);
		
		    try {
		        service.addAdmin();
		        response.sendRedirect("Login.jsp");
//		        response.getWriter().write("Admin registered successfully!");
		    } catch (Exception e) {
		        response.getWriter().write("Error: " + e.getMessage());
		    }
		
	}

}
