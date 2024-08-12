<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Our Task Manager</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />
<link rel="stylesheet" href="css/styles.css">
</head>
<body id="page-top">
<input type="hidden" id="content" value="<%= request.getAttribute("content") %>">

<%@ page import="com.becca.registration.Task" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>



	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top">To-Do List</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">Logout</a></li>
					<li class="nav-item mx-0 mx-lg-1 bg-danger"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout"><%=session.getAttribute("name")%></a></li>
					
				</ul>
			</div>
		</div>
	</nav>
	
	<header class="masthead bg-primary text-white text-center">
		<div class="container d-flex align-items-center flex-column">
			<img class="masthead-avatar mb-5" src="assets/img/portfolio/notepad.png"
				alt="..." />
		
			<div class="divider-custom divider-light">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fas fa-star"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
		</div>
	</header>
	
	<form action ="tasks" method="post" style="background:#FF7F50" class="masthead bg-primary text-white text-center">
		<input type="hidden" name="id" id="id">
		<label>Task name:</label>
		<br><input type="text" name="name" id="task-name" placeholder="task name"><br>
		<label>Task due:</label>
		<br><input type="text" name="date" id="task-date" placeholder="due date"><br>
		<label>Task content:</label>
		<br><input type="text" name="content" id="task-content" placeholder="content"><br>
		<div class="form-group form-button">
			<br><input type="submit" name="signup" id="signup" class="form-submit" value="Create" />
		</div>
	</form>
	
	<section class="page-section bg-primary text-white mb-0" id="tasks">
		<div class="container" id="list-container">
		
			<h2 class="page-section-heading text-center text-uppercase text-white">Tasks</h2>
			
			<h1><%= request.getAttribute("display") %></h1>
		</div>
	</section>

	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>