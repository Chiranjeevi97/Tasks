package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.DatabaseConnector;
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
    	String pwd = request.getParameter("password");
    	
    	try {
    		Connection conn = DatabaseConnector.getConnection();
    		
    		Properties prop = QueryProperties.getProp(); 
    		
       		String query = prop.getProperty("db.selectUser");
    		PreparedStatement ps = conn.prepareStatement(query);
    		ps.setString(1, uname);
    		ps.setString(2, pwd);
    		
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			if(rs.getString(2).equalsIgnoreCase("admin")) {
    				query = "SELECT * FROM emp";
    	    		ps = conn.prepareStatement(query);
    	    		ResultSet rs1 = ps.executeQuery();
    	    		
					/*
					 * ArrayList<User> users = new ArrayList<>(); while(rs1.next()) { User user =
					 * new User(); user.setFullname(rs1.getString(1));
					 * user.setAccessLevel(rs1.getString(2)); user.setUsername(rs1.getString(3));
					 * user.setPassword(rs1.getString(4)); users.add(user); }
					 */
    	    		
    	    		request.setAttribute("users", rs1);
    	    		RequestDispatcher rd = request.getRequestDispatcher("view.jsp");
    	    		rd.forward(request, response);
    				//response.sendRedirect("view.jsp");
    			} else {
    				response.sendRedirect("Home.jsp?username=" + uname);	
    			}
    			
    		}
    		else
    			response.sendRedirect("login.jsp?error=Incorrect Username or Password.");
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		
    		//System.err.println("Error message: " + e.getMessage());
    		
    		response.sendRedirect("login.jsp?error=DB Error.");
    		
    	}
	}

}
