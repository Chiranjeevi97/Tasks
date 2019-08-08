<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	function confirmDelete(url) {
		var del = confirm("Do you want to delete the User?");
		if (del) {
			var http = new XMLHttpRequest();
			http.open("GET", url);
			http.send();
			http.onreadystatechange = function() {
				location.reload();
				document.getElementById("hd").innerHTML = "User deleted";
			}
		} else {
		}
	}
</script>
</head>
<body>

	<div align="right">
		<a href="login.jsp">Log out</a>
	</div>
	<div align="center">
		<h3 id="hd" align="center">Employee Information</h3>

		<%
			ResultSet rs = (ResultSet) request.getAttribute("users");
		%>
		<table border="1">
			<tr>
				<th>Fullname</th>
				<th>Access Level</th>
				<th>Username</th>
				<th>Options</th>
			</tr>
			<%
				while (rs.next()) {
			%>
			<tr>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(3)%></td>
				<%
					String url = "delete?uname=" + rs.getString(3);
				%>
				<td><a href="edit.jsp?uname=<%=rs.getString(3)%>">Edit</a> <!-- button type = "button" onclick = "confirmEdit()">Edit</button> -->
					<button type="button" onclick="confirmDelete('<%=url%>')">Delete</button>
				</td>

			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>