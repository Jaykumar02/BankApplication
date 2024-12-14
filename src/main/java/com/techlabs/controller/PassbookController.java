package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.entity.Transaction;
import com.techlabs.service.TransactionService;

@WebServlet("/PassbookController")
public class PassbookController extends HttpServlet {
    private TransactionService transactionService;

    @Override
    public void init() throws ServletException {
        transactionService = new TransactionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String customerEmail = (String) session.getAttribute("username");

        if (customerEmail == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        
        int customerId = transactionService.getCustomerId(customerEmail);

        if (customerId == 0) {
            response.sendRedirect("Login.jsp");
            return;
        }

        
        List<Transaction> transactionHistory = transactionService.getTransactionHistory(customerId);

       
        request.setAttribute("transactionHistory", transactionHistory);

       
        request.getRequestDispatcher("Passbook.jsp").forward(request, response);
    }
}
