<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Passbook</title>
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

        .navbar-nav .nav-link {
            color: white;
        }

        .content-container {
            margin-top: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
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
                            ${sessionScope.username}
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="customerDropdown">
                            <li><a class="dropdown-item" href="LogoutController">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Form to send data to PassbookController -->
    <form action="PassbookController" method="get">
        <div class="container mt-4">
            <div class="content-container">
                <h3>Passbook</h3>
                <!-- You can pass customer email or ID as a hidden input or from the session -->
                <input type="hidden" name="customerEmail" value="${sessionScope.username}"/>

                <button type="submit" class="btn btn-primary">View Passbook</button>
            </div>
        </div>
    </form>

    <c:if test="${not empty transactionHistory}">
        <div class="container mt-4">
            <div class="content-container">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Transaction ID</th>
                            <th scope="col">Account Number</th>
                            <th scope="col">Transaction Type</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Transaction Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="transaction" items="${transactionHistory}">
                            <tr>
                                <td>${transaction.transactionId}</td>
                                <td>${transaction.accountNumber}</td>
                                <td>${transaction.transactionType}</td>
                                <td>${transaction.amount}</td>
                                <td>${transaction.transactionDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>

    <c:if test="${empty transactionHistory}">
        <div class="alert alert-warning" role="alert">
            No transactions found.
        </div>
    </c:if>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
