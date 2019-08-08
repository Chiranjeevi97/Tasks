<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ashutosh</title>
</head>
<body>
	<% String username = request.getParameter("username"); %>
	<h2>Welcome <%= username %>!</h2>
	
	<div align = "right"><a href = "login.jsp">Log out</a></div>
</body>
</html>