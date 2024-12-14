<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.techlabs.entity.Customer" %>
<%@ page import="com.techlabs.entity.Transaction" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .navbar-brand {
            font-size: 1.8rem;
            font-weight: bold;
        }
        .navbar {
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .content-section {
            margin-top: 30px;
        }
        .content-section h2 {
            margin-bottom: 20px;
            font-weight: bold;
            color: #333;
        }
        .btn-custom {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .form-label {
            font-weight: bold;
        }
        .table {
            margin-top: 20px;
            border: 1px solid #ddd;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .table th {
            background-color: #f8f9fa;
            text-align: center;
        }
        .table td {
            text-align: center;
        }
        .scrollable-content {
            max-height: 500px;
            overflow-y: auto;
        }
        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
	
    if (username == null) {
    	System.out.println("abc null");
       // response.sendRedirect("Login.jsp");
    }else{
    	System.out.println("abc1233456");
    }
%>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Admin Dashboard</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" id="addCustomerTab" href="#">Add New Customer</a></li>
        <li class="nav-item"><a class="nav-link" id="addBankAccountTab" href="#">Add Bank Account</a></li>
        <li class="nav-item"><a class="nav-link" id="viewCustomerTab" href="#">View Customer</a></li>
        <li class="nav-item"><a class="nav-link" id="viewTransactionTab" href="#">View Transaction</a></li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <%= username %>
          </a>
          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="LogoutController">Logout</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- Main Content -->
<div class="container">
    <!-- Add New Customer Form -->
    <div id="addCustomerContent" class="content-section">
        <h2 class="text-center">Add New Customer</h2>
        <form action="AddCustomerController" method="post">
            <div class="mb-3">
                <label for="customerName" class="form-label">Customer First Name</label>
                <input type="text" class="form-control" id="customerName" name="customerFirstName" placeholder="Enter first name">
            </div>
            <div class="mb-3">
                <label for="customerLastName" class="form-label">Customer Last Name</label>
                <input type="text" class="form-control" id="customerLastName" name="customerlastName" placeholder="Enter last name">
            </div>
            <div class="mb-3">
                <label for="customerEmail" class="form-label">Customer Email</label>
                <input type="email" class="form-control" id="customerEmail" name="customerEmailID" placeholder="Enter email">
            </div>
            <div class="mb-3">
                <label for="customerPassword" class="form-label">Customer Password</label>
                <input type="password" class="form-control" id="customerPassword" name="customerPassword" placeholder="Enter password">
            </div>
            <button type="submit" class="btn btn-custom btn-lg w-100">Add Customer</button>
        </form>
    </div>

    <!-- Add Bank Account Form -->
    <div id="addBankAccountContent" class="content-section" style="display: none;">
        <h2 class="text-center">Add Bank Account</h2>
        <form action="viewCustomerController" method="post" class="mb-3">
            <div class="mb-3">
                <label for="searchCustomerId" class="form-label">Search Customer by ID</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="searchCustomerId" name="customerId" placeholder="Enter Customer ID">
                    <button type="submit" class="btn btn-secondary">Search</button>
                </div>
            </div>
        </form>
        <%
            String customerId = (String) request.getParameter("customerId");
            String customerFirstName = (String) request.getAttribute("customerFirstName");
            String customerLastName = (String) request.getAttribute("customerLastName");
            String customerEmail = (String) request.getAttribute("customerEmail");

            if (customerFirstName != null && customerLastName != null && customerEmail != null) {
        %>
        <div class="alert alert-info">
            <h5>Customer Details:</h5>
            <p><strong>Name:</strong> <%= customerFirstName %> <%= customerLastName %></p>
            <p><strong>Email:</strong> <%= customerEmail %></p>
        </div>
        <% } %>
        <form action="AddAccountController" method="post">
            <div class="mb-3">
                <label for="accountNumber" class="form-label">Account Number</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="accountNumber" name="accountNumber" placeholder="Generated account number" readonly>
                    <button type="button" class="btn btn-secondary" id="generateAccountNumberBtn">Generate</button>
                </div>
            </div>
            <div class="mb-3">
                <label for="accountType" class="form-label">Account Type</label>
                <select class="form-select" id="accountType" name="accountType">
                    <option value="Saving">Saving</option>
                    <option value="Current">Current</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="initialAmount" class="form-label">Enter Amount</label>
                <input type="number" class="form-control" id="initialAmount" name="amount" placeholder="Enter initial amount">
            </div>
            <input type="hidden" name="customerId" value="<%= customerId %>">
            <button type="submit" class="btn btn-custom w-100">Add Account</button>
        </form>
    </div>

   
    <!-- View Customers Section -->
<div id="viewCustomerContent" class="content-section scrollable-content" style="display: none;">
    <h2 class="text-center">Customer Details</h2>

    <!-- Filter Input -->
    <div class="mb-3">
        <label for="customerFilter" class="form-label">Search Customers</label>
        <input type="text" id="customerFilter" class="form-control" placeholder="Search by username, account number">
    </div>

    <form action="ViewCustomerController" method="GET" class="text-center mb-3">
        <button type="submit" class="btn btn-primary">View Customers</button>
    </form>

    <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        if (customers != null && !customers.isEmpty()) {
    %>
    <table class="table table-striped table-bordered" id="customerTable">
        <thead>
            <tr>
                <th>Customer ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Account Number</th>
                <th>Account Type</th>
                <th>Balance</th>
            </tr>
        </thead>
        <tbody>
            <% for (Customer customer : customers) { %>
            <tr>
                <td><%= customer.getCustomerId() %></td>
                <td><%= customer.getCustomerFirstName() %></td>
                <td><%= customer.getCustomerlastName() %></td>
                <td><%= customer.getAccountNumber() %></td>
                <td><%= customer.getAccountType() %></td>
                <td><%= customer.getBalance() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p class="text-center">No customer records found.</p>
    <% } %>
</div>




<div id="viewTransactionContent" class="content-section scrollable-content" style="display: none;">
    <h2 class="text-center">Transaction Details</h2>
    <form action="AdminController" method="GET" class="text-center mb-3">
        <button type="submit" class="btn btn-primary">View Transactions</button>
    </form>
    <%
        List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
        if (transactions != null && !transactions.isEmpty()) {
    %>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Transaction ID</th>
                <th>Customer ID</th>
                <th>Account Number</th>
                <th>Transfer Account No</th>
                <th>Transaction Type</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <% for (Transaction transaction : transactions) { %>
            <tr>
                <td><%= transaction.getTransactionId() %></td>
                <td><%= transaction.getCustomerId() %></td>
                <td><%= transaction.getAccountNumber() %></td>
                <td><%= transaction.getAccountTransferNo() %></td>
                <td><%= transaction.getTransactionType() %></td>
                <td><%= transaction.getAmount() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p class="text-center">No transaction records found.</p>
    <% } %>
</div>





<%
    String message = (String) request.getAttribute("message");
%>

<script>
  
</script>

<script>
    document.querySelector('form[action="AddCustomerController"]').addEventListener('submit', function (event) {
        const firstName = document.getElementById('customerName').value.trim();
        const lastName = document.getElementById('customerLastName').value.trim();
        const email = document.getElementById('customerEmail').value.trim();
        const password = document.getElementById('customerPassword').value.trim();

        let errorMessage = '';

        if (firstName === '') {
            errorMessage += 'First Name is required.\n';
        }
        if (lastName === '') {
            errorMessage += 'Last Name is required.\n';
        }
        if (!/^\S+@\S+\.\S+$/.test(email)) {
            errorMessage += 'Invalid Email format.\n';
        }
        if (password.length < 6) {
            errorMessage += 'Password must be at least 6 characters long.\n';
        }

        if (errorMessage) {
            alert(errorMessage);
            event.preventDefault(); // Prevent form submission
        }
    });
</script>


<script>
    document.querySelector('form[action="AddAccountController"]').addEventListener('submit', function (event) {
        const accountNumber = document.getElementById('accountNumber').value.trim();
        const accountType = document.getElementById('accountType').value;
        const initialAmount = document.getElementById('initialAmount').value.trim();

        let errorMessage = '';

        if (accountNumber === '') {
            errorMessage += 'Account number must be generated.\n';
        }
        if (accountType === '') {
            errorMessage += 'Please select an account type.\n';
        }
        if (!/^\d+$/.test(initialAmount) || parseFloat(initialAmount) <= 0) {
            errorMessage += 'Amount must be a positive number.\n';
        }

        if (errorMessage) {
            alert(errorMessage);
            event.preventDefault(); // Prevent form submission
        }
    });

    // Ensure "Generate Account Number" button populates the field
    document.getElementById('generateAccountNumberBtn').addEventListener('click', () => {
        const accountNumberField = document.getElementById('accountNumber');
        const generatedNumber = Math.floor(100000000 + Math.random() * 900000000);
        accountNumberField.value = generatedNumber;
    });
</script>



<script>
	
    document.getElementById('addCustomerTab').addEventListener('click', () => showSection('addCustomerContent'));
    document.getElementById('addBankAccountTab').addEventListener('click', () => showSection('addBankAccountContent'));
    document.getElementById('viewCustomerTab').addEventListener('click', () => showSection('viewCustomerContent'));
    document.getElementById('viewTransactionTab').addEventListener('click', () => showSection('viewTransactionContent'));

    function showSection(sectionId) {
        document.querySelectorAll('.content-section').forEach(section => section.style.display = 'none');
        document.getElementById(sectionId).style.display = 'block';
    }

    document.getElementById('generateAccountNumberBtn').addEventListener('click', () => {
        const accountNumberField = document.getElementById('accountNumber');
        const generatedNumber = Math.floor(100000000 + Math.random() * 900000000);
        accountNumberField.value = generatedNumber;
    });
    
    document.getElementById('customerFilter').addEventListener('input', function () {
        const filterValue = this.value.toLowerCase();
        const rows = document.querySelectorAll('#customerTable tbody tr');
        
        rows.forEach(row => {
            const customerId = row.cells[0].textContent.toLowerCase();
            const firstName = row.cells[1].textContent.toLowerCase();
            const lastName = row.cells[2].textContent.toLowerCase();
            const accountNumber = row.cells[3].textContent.toLowerCase();

           
            if (customerId.includes(filterValue) || firstName.includes(filterValue) || lastName.includes(filterValue) || accountNumber.includes(filterValue)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
