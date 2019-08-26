<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	
	<%String fname = request.getParameter("fname"); %>
	<title> Welcome <%=fname%>!</title>
	</head>

	<body>
		
		<h2>WELCOME <%=fname%>!</h2>
		<br>
		<br>
		<a href="login.jsp">logout</a>
				
	</body>
	
</html>