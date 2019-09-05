<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	
	<head>
	<meta charset="ISO-8859-1">
	<title> Forgot Password? </title>
	</head>
	
	<body>
	
		<h1> Hey there! Forgot password? We got you. </h1>
		<br>
		<br>
		
		<% String error = request.getParameter("error"); %>
		<% String error1 = request.getParameter("error1"); %>
		
		<% if (error != null) {%>
			<h2 style="color: red"> <%= error %> </h2>
		<%} %>
		
		<% if (error1 != null) {%>
			<h2 style="color: red"> <%= error1 %> </h2>
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
		<br>
		
		<a href="login.jsp"> Login </a>
		
	</body>
	
</html>