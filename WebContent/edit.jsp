<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.test.User"%>
<%@ page import="com.test.DatabaseConnector"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h3>Edit User</h3>
		<h2>Sign Up</h2>
		<form action="edit" method="get">
			<%
				String uname = request.getParameter("uname");
			%>
			<%
				Connection conn = DatabaseConnector.getConnection();
			%>
			<%
				String query = "SELECT * FROM emp WHERE uname = ?";
			%>
			<%
				PreparedStatement ps = conn.prepareStatement(query);
			%>
			<%
				ps.setString(1, uname);
			%>
			<%
				ResultSet rs = ps.executeQuery();
			%>
			<%
				rs.next();
			%>
			Full name <input type="text" name="fname"
				value="<%=rs.getString(1)%>"><br /> <br /> Access Level <select
				name="al" value="<%=rs.getString(2)%>">
				<option value="admin">Admin</option>
				<option value="user">User</option>
			</select><br /> <br /> Username <input type="text" name="uname"
				value="<%=uname%>" readonly><br /> <br /> Password <input
				type="password" name="pwd" value="<%=rs.getString(4)%>"><br />
			<br /> <input type="submit" value="Confirm"> <input
				type="button" value="Cancel" onclick="javascript:history.go(-1)">
		</form>
	</div>
</body>
</html>