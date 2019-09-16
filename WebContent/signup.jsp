<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	
	<head>
	<meta charset="ISO-8859-1">	<!-- I also tried "utf-8"/ but no use, i am not able to add favicon (.ico file) to my tab -->
	<title>SIGN UP</title>
		<link rel="shortcut icon" href="download_ac7_icon.ico?" type="image/x-icon" /> <!-- Try adding Geeks for geeks website example --> 
	</head>
	
	<body>
	
		<h4>Sign Up Form</h4>
		
		<%String error = request.getParameter("error"); %>
		<%String error1 = request.getParameter("error1"); %>
		<%String error2 = request.getParameter("error2"); %>
		<%String error3 = request.getParameter("error3"); %>
		<%String error4 = request.getParameter("error4"); %>
		<%String error5 = request.getParameter("error5"); %>
		
		<%if (error1 != null) {%>
			<h4 style="color: red"> <%= error1 %></h4>
		<%} %>
		
		<%if (error2 != null) {%>
			<h4 style="color: red"> <%= error2 %></h4>
		<%} %>
		
		<%if (error != null) {%>
			<h4 style="color: red"> <%= error %></h4>
		<%} %>
		
		<%if (error3 != null) {%>
			<h4 style="color: red"> <%= error3 %></h4>
		<%} %>
		
		<%if (error4 != null) {%>
		<h4 style="color: red"> <%= error4 %></h4>
		<%} %>
		
		<%if (error5 != null) {%>
		<h4 style="color: red"> <%= error5 %></h4>
		<%} %>
		
		
		<form action="signupsv" method="post">
			First Name <input type="text" name="fname" placeholder="First Name"> 
			<br>
			<br>
			Last Name <input type="text" name="lname" placeholder="Last Name">
			<br>
			<br>
			User Name <input type="text" name="uname" placeholder="User Name"> 
			<br>
			<br>
			Email <input type="text" name="email" placeholder="Email"> 
			<br>
			<br>
			Password <input type="password" name="passwd" placeholder="Password"> 
			<br>
			<br>
			Re Enter Password <input type="password" name="rpasswd" placeholder="Re Enter Password"> 
			<br>
			<br>
			Access Level <select name="accss">
				<option>--select--</option>
				<option>user</option>
				<option>admin</option>
			</select>
			<br>
			<br>
			<input type="submit" value="Sign Up">
			 
			<a href="login.jsp">Already a User?</a>
		
		</form>
					
	</body>
	
</html>

