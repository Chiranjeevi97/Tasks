<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ashutosh</title>
<script>
	function validate() {
		var x = document.forms["myForm"]["username"].value;
		var y = document.forms["myForm"]["password"].value;
		if(x == "") {
			alert("Please enter username");
		}
		if(y == "") {
			alert("Please enter password");
		}
	}
</script>
</head>
<body>
	<h2>Sign In</h2>
	<% String error = request.getParameter("error"); %>
	<% if(error != null) { %>
	<h5><%= error %>. Try Again.</h5>
	<% } %>
	<form name="myForm" action = "login" onsubmit="return validate()" method = "post">
		Username <input type = "text" name = "username"><br/><br/>
		Password <input type = "password" name = "password"><br/><br/>
		<input type = "submit" value = "Login">
	</form>
	<br/>
	<a href = "signup.jsp">Sign Up</a> for an account.
</body>
</html>