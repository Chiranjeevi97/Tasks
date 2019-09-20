<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<head>
	<meta charset="ISO-8859-1">
	
	<% String error = request.getParameter("error"); %>
	<% String error1 = request.getParameter("error1"); %>
		
	<% String adfname = request.getParameter("adfname"); %>
	<% String aduname = request.getParameter("aduname"); %>
	<% String adid = request.getParameter("adid"); %>
	
	<% String fnameuser = request.getParameter("fnameuser"); %>
	<% String lnameuser = request.getParameter("lnameuser"); %>
	<% String exuname = request.getParameter("exuname"); %>	
	<% String id = request.getParameter("id"); %>	
	<% String al = request.getParameter("al"); %>	
	<% String email = request.getParameter("email"); %>	

	<title> EDIT FORM </title>
	<link rel = "shortcut icon" href = "apple-logo.png" type = "image/x-icon">  <!-- It WORKS !! but in chrome. Not in Internal Web Browser -->
	
	</head>
			
	<body>

		<h2> welcome admin <%= adfname %> ! </h2>
		
		<h4> Edit details of <%= exuname %> </h4>
	
		<%if(error != null){ %>
			<h3 style="color: red"> <%= error %> </h3>
		<%} %>

		<%if(error1 != null){ %>
			<h3 style="color: red"> <%= error1 %> </h3>
		<%} %>
		
		<% if (fnameuser == null || lnameuser == null || exuname == null || al == null) { %>
			<form action="newviewsv" method="post">
	
				<input type="hidden" name="adid" value="<%= adid %>">
				<input type="hidden" name="aduname" value="<%= aduname %>">
				<input type="hidden" name="adfname" value="<%= adfname %>">
				
				<input type="hidden" name="id" value="<%= id %>">
				<input type="hidden" name="email" value="<%= email %>">
				<input type="hidden" name="exuname" value="<%= exuname %>">
				
				First Name <input type="text" name="fname" placeholder="First Name">
				<br>
				<br>
				Last Name <input type="text" name="lname" placeholder="Last Name">	
				<br>
				<br>
				User Name <input type="text" name="uname" placeholder="User Name">
				<br>
				<br>
				Access Level <select name="al">
					<option>--select--</option>
					<option>user</option>
					<option>admin</option>
				</select>
				<br>
				<br>
				<input type="submit" value="Done">    &nbsp;&nbsp;&nbsp;   <input type="button" value="Cancel" onclick="javascript:history.goback(-1)">	
				<br>
				<br>
					        &nbsp;          <input type="reset" value="Reset">
			</form>
			
			
			<% } else {%>
			
			<form action="newviewsv" method="post">
	
				<input type="hidden" name="adid" value="<%= adid %>">
				<input type="hidden" name="aduname" value="<%= aduname %>">
				<input type="hidden" name="adfname" value="<%= adfname %>">
				
				<input type="hidden" name="id" value="<%= id %>">
				<input type="hidden" name="email" value="<%= email %>">
				<input type="hidden" name="exuname" value="<%= exuname %>">
				
				First Name <input type="text" name="fname" value = "<%= fnameuser %>" placeholder="First Name">
				<br>
				<br>
				Last Name <input type="text" name="lname" value = "<%= lnameuser %>" placeholder="Last Name">	
				<br>
				<br>
				User Name <input type="text" name="uname" value = "<%= exuname %>"  placeholder="User Name">
				<br>
				<br>
				Access Level <select name="al" value="<%= al %>">
					<option>--select--</option>
					<option>user</option>
					<option>admin</option>
				</select>
				<br>
				<br>
				<input type="submit" value="Done">   &nbsp;&nbsp;&nbsp;   <input type="button" value="Cancel" onclick="javascript:history.go(-1)">
				<br>
				<br>
					 &nbsp;      <input type="reset" value="Reset">    		
				
			</form>
				
		<% }%>
		
	</body>
	
</html>

