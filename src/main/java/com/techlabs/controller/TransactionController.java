package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.entity.Account;
import com.techlabs.entity.Transaction;
import com.techlabs.service.TransactionService;

@WebServlet("/TransactionController")
public class TransactionController extends HttpServlet {
    private TransactionService transactionService = new TransactionService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerEmail = (String) session.getAttribute("username");

        if (customerEmail == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        List<Account> accountDetails = transactionService.getCustomerAccounts(customerEmail);
        request.setAttribute("accountDetails", accountDetails);

        String transactionType = request.getParameter("transactionType");
        if (transactionType != null) {
            long fromAccount = Long.parseLong(request.getParameter("accountNumber"));
            long toAccount = 0; // Initialize with a default value

            if (request.getParameter("accountNumberToTransfer") != null && !request.getParameter("accountNumberToTransfer").isEmpty()) {
                toAccount = Long.parseLong(request.getParameter("accountNumberToTransfer"));
            }

            int amount = Integer.parseInt(request.getParameter("amount"));
            boolean isSuccessful = false;
            Transaction transaction = new Transaction();
            transaction.setCustomerId(transactionService.getCustomerId(customerEmail)); // Fetch customer ID based on email
            transaction.setAccountNumber(fromAccount);
            transaction.setAccountTransferNo(toAccount);
            transaction.setAmount(amount);

            switch (transactionType.toLowerCase()) {
                case "credit":
                    isSuccessful = transactionService.credit(fromAccount, amount);
                    transaction.setTransactionType("credit");
                    break;
                case "debit":
                    isSuccessful = transactionService.debit(fromAccount, amount);
                    transaction.setTransactionType("debit");
                    break;
                case "transfer":
                    if (toAccount == 0) {
                        request.setAttribute("transactionResult", "Invalid 'To Account' number for transfer.");
                        request.getRequestDispatcher("NewTransaction.jsp").forward(request, response);
                        return;
                    }
                    isSuccessful = transactionService.transfer(fromAccount, toAccount, amount);
                    transaction.setTransactionType("transfer");
                    break;
                default:
                    isSuccessful = false;
                    break;
            }

            // Save the transaction in the database
            if (isSuccessful) {
                boolean transactionSaved = transactionService.saveTransaction(transaction);
                if (!transactionSaved) {
                    request.setAttribute("transactionResult", "Transaction successful, but failed to record the transaction.");
                } else {
                    request.setAttribute("transactionResult", "Transaction successful and recorded.");
                }
            } else {
                request.setAttribute("transactionResult", "Transaction failed. Please try again.");
            }
        }

        request.getRequestDispatcher("NewTransaction.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    // Method to get customer ID based on email
    private int getCustomerId(String email) {
        // This can be a simple query that fetches the customer ID based on the email
        // Example: SELECT customer_id FROM customer WHERE email_id = ?
        // You'll need to implement the logic here
        return 0; // Placeholder return value, you should fetch the actual customer ID
    }
}
