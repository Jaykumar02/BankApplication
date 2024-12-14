package com.techlabs.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.service.DBConnection;
import com.techlabs.service.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private LoginService service;
    private DBConnection dbconnection = new DBConnection();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        RequestDispatcher dispatcher;

        try (Connection connection = dbconnection.connect()) {
            service = new LoginService(username, password, role);

           
            if (service.validateCredentials(connection)) {
                
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                
                if ("Admin".equalsIgnoreCase(role)) {
                    dispatcher = request.getRequestDispatcher("/AdminHome.jsp");
                    dispatcher.forward(request, response);
                    System.out.println("admin");
                } else if ("Customer".equalsIgnoreCase(role)) {
                    dispatcher = request.getRequestDispatcher("/CustomerHome.jsp");
                    dispatcher.forward(request, response);
                    
                    System.out.println("customer");
                } else {
                   
                    request.setAttribute("errorMessage", "Unexpected role. Please try again.");
                    dispatcher = request.getRequestDispatcher("/Login.jsp");
                    dispatcher.forward(request, response);
                }

                
                dispatcher.forward(request, response);
            } else {
                
                request.setAttribute("errorMessage", "Invalid credentials, please try again.");
                dispatcher = request.getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
           
            request.setAttribute("errorMessage", "An error occurred. Please try again later.");
           
            
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the login page for GET requests
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
        dispatcher.forward(request, response);
    }
}