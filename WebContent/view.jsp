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
	<link rel = "shortcut icon" href = "apple-logo.png" type = "image/x-icon">  <!-- It WORKS !! but in chrome. Not in Internal Web Browser -->
	
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
						<td> <%= rs.getString(7) %> </td>	
						<td> <a href="deletesv?exuname=<%= rs.getString(4) %>&email=<%= rs.getString(7) %>&al=<%= rs.getString(6) %>&aduname=<%=aduname %>&adfname=<%=adfname %>&adid=<%=adid %>&id=<%=rs.getString(1) %>" onclick="return confirm('Delete <%= rs.getString(4) %> ?')"> delete </a> </td> 
						<td> <a href="editsv?exuname=<%= rs.getString(4) %>&email=<%= rs.getString(7) %>&al=<%= rs.getString(6) %>&aduname=<%=aduname %>&adfname=<%=adfname %>&adid=<%=adid %>&id=<%=rs.getString(1) %>" onclick="return confirm('Edit <%= rs.getString(4) %> ?')"> edit </a> </td>				
					</tr>		
		<%} %>
			
			</table>
		<a href="login.jsp">logout</a>
	</body>

</html>

