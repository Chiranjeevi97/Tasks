<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<head>
	
	<meta charset="ISO-8859-1"> 
	<title>LOGIN</title>
	 
	 <link rel = "shortcut icon" href = "apple-logo.png" type = "image/x-icon">  <!-- It WORKS !! but in chrome. Not in Internal Web Browser -->
		
	</head>
	
	<body>
	
		<h4>Login Form</h4>
		
		<%String error = request.getParameter("error"); %>
		<%String error1 = request.getParameter("error1"); %>
		<%String error2 = request.getParameter("error2"); %>
		<%String error3 = request.getParameter("error3"); %>
		<%String erroremailnull = request.getParameter("erroremailnull"); %>
		
		<%if (error != null) {%>
			<h4 style="color: red"> <%= error %> </h4>
		<%} %>
		
		<%if (error1 != null) {%>
			<h4 style="color: red"> <%= error1 %> </h4>
		<%} %>
		
		<%if (error2 != null) {%>
			<h4 style="color: red"> <%= error2 %> </h4>
		<%} %>

		<%if (error3 != null) {%>
			<h4 style="color: red"> <%= error3 %> </h4>
		<%} %>
		
		<%if (erroremailnull != null) {%>
			<h4 style="color: red"> <%= erroremailnull %> </h4>
		<%} %>
				
		<form action="loginsv" method="post">
			User Name / Email = <input type="text" name="uname" placeholder="User Name or Email">
			<br>
			<br>
			Password = <input type="password" name="passwd" placeholder="Password">  
			<br>
			<br>
			<input type="submit" value="Login">  
			<a href="signup.jsp">New User? Register Now</a>
			<br>
			<br>
			<a href="forgot.jsp">Forgot Password?</a>		
		</form>
					
	</body>
	
</html>

