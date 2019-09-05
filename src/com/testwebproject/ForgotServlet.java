package com.testwebproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testwebproject.SendMail;


@WebServlet("/forgotsv")
public class ForgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email"); // once you email from forgot.jsp, make sure that the email exists in the db 
		
		if (email.isEmpty()) {
			
			response.sendRedirect("forgot.jsp?error=Please fill all the fields");
		}
		
		else {
			try {
		// db connection essentials
				String urldb = "jdbc:mysql://localhost:3306/empdb";
				String unamedb = "root";
				String passwddb = "salman";
	
				// loading driver to connect to db
				String dbDriver = "com.mysql.jdbc.Driver"; 
				Class.forName(dbDriver);
				
				// establish connection
				Connection con = DriverManager.getConnection(urldb, unamedb, passwddb);
				
				// statement to check if username exists in db
				
				String query = "SELECT * FROM EMPLOYEE WHERE EMPEMAIL = ?";
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) { // email exists in the database. Now send the link of reset_password.jsp through email (JavaMail API comes into picture here) 
					
					String fnameemail = rs.getString(2);
					SendMail.send(email, fnameemail);
					response.sendRedirect("confirmation.jsp");
				}
				
				else {
					response.sendRedirect("forgot.jsp?error1=The entered Email Address does not exist.");
			}
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}		
		
	}

}
