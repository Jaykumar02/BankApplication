<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Transaction</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
        }
        .navbar {
            background-color: #007bff;
        }
        .navbar-brand {
            font-size: 1.8rem;
            font-weight: bold;
            color: white;
        }
        .nav-link {
            color: white;
        }
        .tab-container {
            margin-top: 30px;
            display: flex;
            justify-content: space-around;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
        }
        .tab {
            font-size: 1.2rem;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .tab.active {
            background-color: #007bff;
            color: white;
            border: 1px solid #007bff;
        }
        .tab:hover {
            background-color: #0056b3;
            color: white;
        }
        .content-container {
            margin-top: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .btn-back {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <%
    String customerName = (String) session.getAttribute("username");
    if (customerName == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
    %>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="CustomerHome.jsp">Customer Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="customerDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <%= customerName %>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="customerDropdown">
                            <li><a class="dropdown-item" href="LogoutController">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="tab-container">
            <div class="tab active" data-content="transactionContent">New Transaction</div>
        </div>

        <div id="content" class="content-container">
            <div id="transactionContent" class="content-section">
                <h3>New Transaction</h3>

                <!-- New Transaction Form -->
                <form action="TransactionController" method="post" id="transactionForm">
                    <div class="mb-3">
                        <label for="transactionType" class="form-label">Select Transaction Type</label>
                        <select class="form-select" name="transactionType" id="transactionType" required>
                        	<option value="Select">Select</option>
						    <option value="Credit">Credit</option>
						    <option value="Debit">Debit</option>
						    <option value="Transfer">Transfer</option>
						</select>

                    </div>
                    <div class="mb-3">
                        <label for="accountNumber" class="form-label">From Account</label>
                        <select class="form-select" name="accountNumber" id="accountNumber" required>
                            <c:forEach var="account" items="${accountDetails}">
                                <option value="${account.accountNumber}">${account.accountNumber} - ${account.accountType}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3" id="toAccountDiv" style="display: none;">
                        <label for="accountNumberToTransfer" class="form-label">To Account</label>
                        <input type="text" class="form-control" name="accountNumberToTransfer" id="accountNumberToTransfer" placeholder="Enter Account Number" required>
                    </div>
                    <div class="mb-3">
                        <label for="amount" class="form-label">Amount</label>
                        <input type="number" class="form-control" name="amount" id="amount" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Complete Transaction</button>
                </form>

                <div class="mt-4">
                    <%
                    if (request.getAttribute("transactionResult") != null) {
                    %>
                    <div class="alert alert-info">
                        <%= request.getAttribute("transactionResult") %>
                    </div>
                    <%
                    }
                    %>
                </div>
            </div>
        </div>
    </div>

    <script>
    document.getElementById("transactionType").addEventListener("change", function () {
        const transactionType = this.value;
        const toAccountDiv = document.getElementById("toAccountDiv");
        const toAccountInput = document.getElementById("accountNumberToTransfer");

        if (transactionType === "Transfer") {
            toAccountDiv.style.display = "block";
            toAccountInput.setAttribute("required", "true");
        } else {
            toAccountDiv.style.display = "none";
            toAccountInput.removeAttribute("required");
            toAccountInput.value = ""; // Clear the value
        }
    });

    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
