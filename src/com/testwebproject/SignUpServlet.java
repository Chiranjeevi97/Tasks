package com.testwebproject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// @WebServlet("/signupsv") // You have configured web.xml, where it handles servlet URL mapping with servlet class. It maps servlet name with servlet URL mapping so you need not use annotation @WebServlet here. // Aim now is to take values from signup.jsp and store in database.
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// More new features to be implemented in future: 1) Password should be atleast 8 chars long   ---> DONE
													
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String fname = request.getParameter("fname").trim();
		String lname = request.getParameter("lname").trim();
		String uname = request.getParameter("uname").trim();  
		String passwd = request.getParameter("passwd");                // Do not trim password at all. Whatever user enters just store it in Database
		String rpasswd = request.getParameter("rpasswd");              // got all values from signup.jsp
		String accss = request.getParameter("accss");
		String email = request.getParameter("email").trim().toLowerCase();     // ignore string case. Convert to lower case      // make sure "@" is present in the email address else redirect back to signup.jsp saying invalid email
				 	
		String urldb = "jdbc:mysql://localhost:3306/empdb";
		String unamedb = "root";
		String passwddb = "salman";

		//  New feature implemented: Trim the leading and trailing spaces in the strings that are retrieved from signup.jsp page's information fields. ----> DONE NOW !! It is working perfectly
		
		if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || email.isEmpty() || passwd.isEmpty() || rpasswd.isEmpty() || accss.equals("--select--") || fname == null || lname == null || uname == null || email == null || passwd == null || rpasswd == null || accss == null)
		{	
			response.sendRedirect("signup.jsp?error1=Please fill all the provided fields");			
		}
		
		else if (email.equals("null")) {
			response.sendRedirect("signup.jsp?erroremailnull=Email Address cannot be null !");
		}
		
		else if(passwd.equalsIgnoreCase("null")) {
			response.sendRedirect("signup.jsp?errorpassnull=Password cannot be set as null !");
		}
		
		// NEW FEATURE: If email doesn't has "@" then say invalid email. Do not store it in database. Check RFC (Request for Comments) 5321 (Simple Mail Transfer Protocol --- SMTP) for valid local body ASCII Characters
		else if (passwd.length() < 8)     // if password is shorter than 8 chars, do not accept it 
		{
			response.sendRedirect("signup.jsp?error5=Password must be atleast 8 characters long");
		}
		
		else if ((email.contains("@")) && (email.indexOf("@") >= 1))                     // the string method returns boolean. 
		{                                                                                // covered most of the rules not all as they are very complex. Most people use gmail and gmail has it's own RFC Standards, so we are good enough here as of now
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
					response.sendRedirect("signup.jsp?error2=User name in use. Please try other user name");                    // do the same for email as well
					con.close();
				}
				
				String query2 = "SELECT * FROM EMPLOYEE WHERE EMPEMAIL = ?";  
				PreparedStatement ps2 = con.prepareStatement(query2);
				
				ps2.setString(1, email);
				ResultSet rs2 = ps2.executeQuery();
				
				if (rs2.next()) {
					response.sendRedirect("signup.jsp?error3=Email in use. Please try another email");   
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
		else {
			response.sendRedirect("signup.jsp?error4=Invalid email address");
		}
	}
}

