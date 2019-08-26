<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
	<head>
	<meta charset="ISO-8859-1">

	<% String erroredit = request.getParameter("erroredit"); %>
	<% String errordelete = request.getParameter("errordelete"); %>
	
	<% String adfname = request.getParameter("adfname"); %>
	<% String aduname = request.getParameter("aduname"); %>
	<% String adid = request.getParameter("adid"); %>

	
	<title> WELCOME ADMIN <%= adfname %> ! </title>
	
	</head>
	
	
	<body>	
		<%if (erroredit != null) {%>
			<h3 style="color: red"> <%= erroredit %> </h3>
		<%} %>
			
		<%if (errordelete != null) {%>
			<h3 style="color: red"> <%= errordelete %> </h3>	
		<%} %>


		<h1> WELCOME ADMIN <%= adfname %> ! </h1>
			
		<% ResultSet rs = (ResultSet) request.getAttribute("dbdetails"); %>	
			
			<table border="1">
			
				<% while(rs.next()) {%>
					<tr>
						<td> <%= rs.getString(1) %> </td>
						<td> <%= rs.getString(2) %> </td>
						<td> <%= rs.getString(3) %> </td>
						<td> <%= rs.getString(4) %> </td>
						<td> <%= rs.getString(5) %> </td>
						<td> <%= rs.getString(6) %> </td>	
						<td> <a href="deletesv?uname=<%= rs.getString(4) %>&al=<%= rs.getString(6) %>&aduname=<%=aduname %>&adfname=<%=adfname %>&adid=<%=adid %>&id=<%=rs.getString(1) %>"> delete </a> </td> 
						<td> <a href="editsv?uname=<%= rs.getString(4) %>&al=<%= rs.getString(6) %>&aduname=<%=aduname %>&adfname=<%=adfname %>&adid=<%=adid %>&id=<%=rs.getString(1) %>"> edit </a> </td>				
					</tr>		
		<%} %>
			
			</table>
		<a href="login.jsp">logout</a>
	</body>

</html>