<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
			<a class="navbar-brand" href="#">Customer Dashboard</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="customerDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<%=customerName%>
					</a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="customerDropdown">
							<li><a class="dropdown-item" href="LogoutController">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>	

	<div class="container mt-4">
		<div class="tab-container">
			<a href="Passbook.jsp" class="tab">Passbook</a>
			<!-- Update: Link to the new transaction page -->
			<a  href ="TransactionController" class="tab">New Transaction</a>
			<a href="EditProfile.jsp" class="tab">Edit Profile</a>
			
		</div>

		<div id="content" class="content-container">
			<div id="passbookContent" class="content-section">
				<h3>Passbook</h3>
				<p>Your passbook details will be displayed here.</p>
			</div>

			<div id="editProfileContent" class="content-section" style="display: none;">
				<h3>Edit Profile</h3>
				<p>Profile editing form will be displayed here.</p>
			</div>
		</div>
	</div>

	<script>
        const tabs = document.querySelectorAll('.tab');
        const contentSections = document.querySelectorAll('.content-section');

        tabs.forEach(tab => {
            tab.addEventListener('click', () => {
                tabs.forEach(t => t.classList.remove('active'));
                contentSections.forEach(section => section.style.display = 'none');

                tab.classList.add('active');
                const contentId = tab.getAttribute('data-content');
                document.getElementById(contentId).style.display = 'block';
            });
        });
    </script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
