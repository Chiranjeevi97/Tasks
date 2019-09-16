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

@WebServlet("/resetsv")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get password and re enter password fields and compare if they are same or are all the fields filled out 
		
		String password = request.getParameter("password");   
		String rpassword = request.getParameter("rpassword");
		String email = request.getParameter("email");
		
		
		if (password.isEmpty() || rpassword.isEmpty()) {
			response.sendRedirect("reset_password.jsp?error=Please fill all the fields&email="+email);
		}
				
		else {
			
			if (!(password.equals(rpassword))) {
				response.sendRedirect("reset_password.jsp?error1=Passwords do not match please try again&email="+email);
			}
			
			else if (password.length() < 8) {
				response.sendRedirect("reset_password.jsp?error2=Password must be atleast 8 characters long&email="+email);
			}
			
			else {  // now if everything goes fine then you should change password in db (update) for that particular user, identified by his/her email which shall never change in any case   
				
				try {
				// load driver 
				String driver = "com.mysql.jdbc.Driver";
				Class.forName(driver);
				
				// establish connection to db
				
				String urldb = "jdbc:mysql://localhost:3306/empdb";
				String usernamedb = "root"; 
				String passwddb = "salman";				
				
				Connection con = DriverManager.getConnection(urldb, usernamedb, passwddb);
				
				// prepare statement
				
				// first get that user's all the details using his email, as we know that email is unique and cannot be changed at any cost and then later update his password only
				
				String query = "SELECT * FROM EMPLOYEE WHERE EMPEMAIL = ?";
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				
				rs.next();
				
				String id = rs.getString(1);
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				String uname = rs.getString(4);
				String al = rs.getString(6);
				String emaildb = rs.getString(7);
				
				
				String query1 = "UPDATE EMPLOYEE SET EMPID = ?, EMPFIRSTNAME = ?, EMPLASTNAME = ?, EMPUSERNAME = ?, EMPPASSWORD = ?, EMPACCESSLEVEL = ?, EMPEMAIL = ? WHERE EMPEMAIL = ?"; 
				PreparedStatement ps1 = con.prepareStatement(query1);
				
				ps1.setString(1, id);
				ps1.setString(2, fname);
				ps1.setString(3, lname);
				ps1.setString(4, uname);
				ps1.setString(5, password);
				ps1.setString(6, al);
				ps1.setString(7, emaildb);
				ps1.setString(8, email);
				
				ps1.executeUpdate();
				
				response.sendRedirect("login.jsp");
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}

	}

}