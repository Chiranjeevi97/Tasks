<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ashutosh</title>
</head>
<body>
	<h2>Sign In</h2>
	<% String error = request.getParameter("error"); %>
	<% if(error != null) { %>
	<h5><%= "Incorrect Username or Password. Try again." %></h5>
	<% } %>
	<form action = "login" method = "post">
		Username <input type = "text" name = "username"><br/><br/>
		Password <input type = "password" name = "password"><br/><br/>
		<input type = "submit" value = "Login">
	</form>
	<br/>
	<a href = "signup.jsp">Sign Up</a> for an account.
</body>
</html>