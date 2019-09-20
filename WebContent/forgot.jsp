<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	
	<head>
	<meta charset="ISO-8859-1">
	<title> Forgot Password? </title>
	<link rel = "shortcut icon" href = "apple-logo.png" type = "image/x-icon">  <!-- It WORKS !! but in chrome. Not in Internal Web Browser -->
	</head>
	
	<body>
	
		<h1> Hey there! Forgot password? We got you. </h1>
		<% String error = request.getParameter("error"); %>
		<% String error1 = request.getParameter("error1"); %>
		<% String error2 = request.getParameter("error2"); %>
		
		<% String erroremailnull = request.getParameter("erroremailnull"); %>
		
		<% if (error != null) {%>
			<h2 style="color: red"> <%= error %> </h2>
		<%} %>
		
		<% if (error1 != null) {%>
			<h2 style="color: red"> <%= error1 %> </h2>
		<%} %>
		
		<% if (error2 != null) {%>
			<h2 style="color: red"> <%= error2 %> </h2>
		<%} %>
		
		<% if (erroremailnull != null) {%>
			<h2 style="color: red"> <%= erroremailnull %> </h2>
		<%} %>
				
		<form action="forgotsv" method="post">

			Enter your email address <input type="text" name="email">
			<br>
			<br>
			<input type="submit" value="Reset Password">
			<br>
			<br>
		</form>
		<br>		
		<a href="login.jsp"> Login </a>
		
	</body>
	
</html>