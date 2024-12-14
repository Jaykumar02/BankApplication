<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Bank Application</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">Bank Application</a>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card" style="width: 400px;">
            <div class="card-body">
                <h3 class="card-title text-center">Login</h3>
                <form action="LoginController" method="post">
                    <!-- User Role Selection -->
                    <div class="form-group">
                        <label for="role">Login as:</label>
                        <select class="form-control" id="role" name="role" required>
                            <option value="Admin">Admin</option>
                            <option value="Customer">Customer</option>
                        </select>
                    </div>
                    <!-- Username Field -->
                    <div class="form-group mt-3">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
                    </div>
                    <!-- Password Field -->
                    <div class="form-group mt-3">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
                    </div>
                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-primary btn-block mt-4">Login</button>
                </form>
                <!-- Register Admin Button -->
                <div class="text-center mt-3">
                    <a href="AdminRegistration.jsp" class="btn btn-link">Register as Admin</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
