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


@WebServlet("/signupsv") // Aim now is to take values from signup.jsp and store in database.
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");
		String rpasswd = request.getParameter("rpasswd"); // got all values from signup.jsp
		String accss = request.getParameter("accss");
		String email = request.getParameter("email");
				 	
		String urldb = "jdbc:mysql://localhost:3306/empdb";
		String unamedb = "root";
		String passwddb = "salman";
		
		if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || email.isEmpty() || passwd.isEmpty() || rpasswd.isEmpty() || accss.equals("--select--"))
		{	
			response.sendRedirect("signup.jsp?error1=Please fill all the provided fields.");
						
		}
		else {
			
			try {
				// load driver
				String dbDriver = "com.mysql.jdbc.Driver"; 
				Class.forName(dbDriver);
				
				// establish connection
				Connection con = DriverManager.getConnection(urldb, unamedb, passwddb);
				
				// statement to check if username exists in db
				
				String query1 = "SELECT * FROM EMPLOYEE WHERE EMPUSERNAME = ? ";
				PreparedStatement ps1 = con.prepareStatement(query1);
				
				ps1.setString(1, uname);
				ResultSet rs1 = ps1.executeQuery();
				
				if (rs1.next()) {
					response.sendRedirect("signup.jsp?error2=User name used. Please try other user name.");   // do the same for email as well
					con.close();
				}
				
				String query2 = "SELECT * FROM EMPLOYEE WHERE EMPEMAIL = ?";    
				PreparedStatement ps2 = con.prepareStatement(query2);
				
				ps2.setString(1, email);
				ResultSet rs2 = ps2.executeQuery();
				
				if (rs2.next()) {
					response.sendRedirect("signup.jsp?error3=Email already used. Please try another email.");   
					con.close();
				}
				
				
				if (passwd.equals(rpasswd)) {
					
					// prepare statement
					String query = "INSERT INTO EMPLOYEE VALUES (?,?,?,?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(query);
					
					ps.setString(1, null);
					ps.setString(2, fname);
					ps.setString(3, lname);
					ps.setString(4, uname);
					ps.setString(5, passwd);
					ps.setString(6, accss);
					ps.setString(7, email);

					
					ps.executeUpdate();

					con.close();
										
					response.sendRedirect("login.jsp");
					
				}
				
				if (!(passwd.equals(rpasswd))){
					response.sendRedirect("signup.jsp?error=Passwords do not match. Please try again");
					con.close();
				}
							
				}catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
}