<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Profile</title>
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
.content-container {
	margin-top: 20px;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.error {
	color: red;
	font-size: 0.9rem;
	display: none;
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
			<a class="navbar-brand" href="CustomerHome.jsp">Customer Home</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="customerDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<%=customerName%>
					</a>
						<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="customerDropdown">
							<li><a class="dropdown-item" href="LogoutController">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>

<div class="container mt-4">
    <div class="content-container">
        <h3>Edit Profile</h3>
        <form action="CustomerController" method="post" id="editProfileForm">
            <div class="mb-3">
                <label for="fieldToUpdate" class="form-label">Select Field to Update</label>
                <select class="form-select" id="fieldToUpdate" name="fieldToUpdate" required>
                    <option value="first_name">First Name</option>
                    <option value="last_name">Last Name</option>
                    <option value="email_id">Email</option>
                    <option value="password">Password</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="newValue" class="form-label">Enter New Value</label>
                <input type="text" class="form-control" id="newValue" name="newValue" required>
                <div class="error" id="validationError"></div>
            </div>
            <div class="text-end">
                <button type="button" class="btn btn-primary" onclick="validateForm()">Save Changes</button>
            </div>
        </form>
    </div>
</div>
<c:if test="${param.success == 'true'}">
    <script>
        alert("Details Updated Successfully");
    </script>
</c:if>

<script>
function validateForm() {
    const fieldToUpdate = document.getElementById("fieldToUpdate").value;
    const newValue = document.getElementById("newValue").value;
    const errorDiv = document.getElementById("validationError");
    let errorMessage = "";

    if (fieldToUpdate === "email_id") {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(newValue)) {
            errorMessage = "Please enter a valid email address.";
        }
    } else if (fieldToUpdate === "first_name" || fieldToUpdate === "last_name") {
        const nameRegex = /^[A-Za-z\s]+$/;
        if (!nameRegex.test(newValue)) {
            errorMessage = "Name can only contain letters and spaces.";
        }
    } else if (fieldToUpdate === "password") {
        if (newValue.length < 8 || !/\d/.test(newValue) || !/[!@#$%^&*]/.test(newValue)) {
            errorMessage = "Password must be at least 8 characters long and include a number and a special character.";
        }
    }

    if (errorMessage) {
        errorDiv.style.display = "block";
        errorDiv.textContent = errorMessage;
        return false;
    } else {
        errorDiv.style.display = "none";
        if (confirm("Are you sure you want to save changes?")) {
            document.getElementById("editProfileForm").submit();
        }
    }
}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
