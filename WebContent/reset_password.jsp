<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title> Reset Password </title>
	</head>

	<body>
	
	<% String error = request.getParameter("error"); %>
	<% String error1 = request.getParameter("error1"); %>
	<% String error2 = request.getParameter("error2"); %>
	<% String email = request.getParameter("email"); %>
	
	<% if (error != null){%>
		<h2 style="color: red"> <%= error %> </h2>
	<% }%>
	
	<% if (error1 != null) {%>
		<h2 style="color: red"> <%= error1 %></h2>
	
	<%} %>
	
	<% if (error2 != null) {%>
		<h2 style="color: red"> <%= error2 %></h2>
	
	<%} %>
	
	
	
	<form action="resetsv" method="post">
		
		<input type="hidden" name="email" value="<%= email %>">
		
		Password <input type="password" name="password">
		<br>
		<br>
		Re enter Password <input type="password" name="rpassword">
		<br>
		<br>
		<input type="submit" value="Reset Password">

	</form>
		
	</body>

</html>