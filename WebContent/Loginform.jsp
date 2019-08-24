<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<script>
	function validate() {
		//document.getElementById("password_error").innerHTML = "**Please enter password";
		var x = document.forms["myForm"]["uname"].value;
		if(x == "") {
			document.getElementById("username_error").innerHTML = "Please enter username";
			var y = document.forms["myForm"]["password"].value;
			if(y == "") {
				document.getElementById("password_error").innerHTML = "Please enter password";
				return false;
			}
			return false;
		}
		
	}
</script>
</head>
<body>
	<div>

		<h2>Login Page</h2>
		
		<form name = "myForm" action="Login" onsubmit = "return validate()" method="post">
			
			<span><b> Username: <input type="text" name="uname"></b></span><span id="username_error"></span><br><br>
			<span><b> Password: <input type="password" name="password"></b></span><span id="password_error"></span><br>
			<br> <input type="submit" value="Submit">
		</form>
		<br> <a href="SignUp.jsp">SIGN UP </a>
	</div>
</body>
</html>