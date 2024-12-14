<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Registration</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
    function handleRegistration(event) {
      //  event.preventDefault(); 
        alert("Registered Successfully!");
        window.location.href = "Login.jsp";
    }
</script>
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card" style="width: 400px;">
            <div class="card-body">
                <h3 class="card-title text-center">Admin Registration</h3>
                <form action="AdminRegController" method="post" onsubmit="handleRegistration(event)">
                    <!-- Admin Name -->
                    <div class="form-group">
                        <label for="adminName">Admin Name</label>
                        <input type="text" class="form-control" id="adminName" name="adminName" placeholder="Enter admin name" required>
                    </div>
                    <!-- Admin Password -->
                    <div class="form-group mt-3">
                        <label for="adminPassword">Password</label>
                        <input type="password" class="form-control" id="adminPassword" name="adminPassword" placeholder="Enter password" required>
                    </div>
                    <!-- Confirm Password -->
                    <div class="form-group mt-3">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm password" required>
                    </div>
                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-primary btn-block mt-4">Register</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
