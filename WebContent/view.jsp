<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ServletsPackage.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <% 
 List<User> users= (ArrayList<User>)request.getAttribute("us"); 
 %>
 
<%String username=(String)session.getAttribute("uname"); %>
<h2 >Welcome <%=username %></h2>
<table border="1">
<tr>
<td>Firstname</td>
<td>Username</td>
<td>Password</td>
</tr>
<% for(User u : users ) { %>
<tr>
<td> <%=u.getFirstname() %> </td>
<td> <%=u.getUsername() %> </td>
<td> <%=u.getPassword() %> </td>
<td><a href="Delete?uname=<%=u.getUsername() %>">Delete</a></td>
</tr>
<% } %>


</table>
</body>
</html>