<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ashutosh</title>
</head>
<body>
	<div align="center">
		<h2>Sign Up</h2>
		<form action="signup" method="get">
			Full name <input type="text" name="fname"><br />
			<br /> Access Level <select name="al">
				<option value="admin">Admin</option>
				<option value="user">User</option>
			</select><br />
			<br /> Username <input type="text" name="uname"><br />
			<br /> Password <input type="password" name="pwd"><br />
			<br /> <input type="submit" value="Sign Up">
		</form>
		<br /> Already a User? Sign In <a href="login.jsp">here</a>.
	</div>
</body>
</html>