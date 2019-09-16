package com.testwebproject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/loginsv")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname").trim();   // In future, make sure that you provide the flexibility to accept either user name or email from login page ----> DO IT NOW !!
		String passwd = request.getParameter("passwd");   // Do not trim password. That's the trick here. Do the same in Signup Servlet as well. Whatever the user types in password field just take it as such

		/*

		NEW FEATURE:
		The string uname could now either be user name or email. Act accordingly now ---> FEATURE IMPLEMENTED AND IT WORKS PERFECTLY !! 

		Update: 
		Try to give exact error if wrong email or user name. Make them separate  ---> Try regex for java. If string uname has @ then treat it as an email else user name ---> Perfect logic but I haven't used regex.
		
		*/
		
		if (uname.isEmpty() || passwd.isEmpty()) {
			response.sendRedirect("login.jsp?error=Please fill all the fields");
		}
		else {
			try {
				// load driver 
				Class.forName("com.mysql.jdbc.Driver");
				
				// establish connection
				String urldb = "jdbc:mysql://localhost:3306/empdb";
				String usernamedb = "root"; 
				String passwddb = "salman";
				
				Connection con = DriverManager.getConnection(urldb, usernamedb, passwddb);
				
				// statement
				String query = "SELECT * FROM EMPLOYEE WHERE EmpUserName = ?";
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1, uname);
				
				ResultSet rs = ps.executeQuery();

				// Query for checking if the uname is email 
				String queryemail = "SELECT * FROM EMPLOYEE WHERE EmpEmail = ?";
				PreparedStatement psemail = con.prepareStatement(queryemail);
				
				psemail.setString(1, uname);
				
				ResultSet rsemail = psemail.executeQuery();

				
				if (rs.next())  
				{
					String fname = rs.getString("EMPFIRSTNAME"); 
					String chkpasswd = rs.getString("EMPPASSWORD");
					String chkal = rs.getString("EMPACCESSLEVEL");
						
					if (!(chkpasswd.equals(passwd))) {
						response.sendRedirect("login.jsp?error1=Wrong password. Please try again");
						con.close();
					}
					
					else {    

						if (chkal.equals("admin")) {
							String querynew = "select * from employee where EMPUSERNAME = ?";
							PreparedStatement psnew = con.prepareStatement(querynew);
							
							psnew.setString(1,uname);
							ResultSet rsnew = psnew.executeQuery();
							rsnew.next();
						
							String adfname = rsnew.getString(2);
							String aduname = rsnew.getString(4);
							String adid = rsnew.getString(1);
							
							String querynew1 = "select * from employee";
							PreparedStatement psnew1 = con.prepareStatement(querynew1);
							ResultSet rsnew1 = psnew1.executeQuery();
											
							request.setAttribute("dbdetails", rsnew1);
							RequestDispatcher rd = request.getRequestDispatcher("view.jsp?adfname="+adfname+"&aduname="+aduname+"&adid="+adid);    // I CHANGED FROM HERE EVERYTHING // send object now through request dispatcher
							rd.forward(request, response); 
							con.close();	
					
						}
						else {
							response.sendRedirect("welcome.jsp?fname="+fname);	
							con.close();
						}
										
					}
					
				}
				
				else if (rsemail.next()){     // If uname is not a user name then it might be email. Implement else if loop checking if it is an email  
					String fname = rsemail.getString("EMPFIRSTNAME"); 
					String chkpasswd = rsemail.getString("EMPPASSWORD");
					String chkal = rsemail.getString("EMPACCESSLEVEL");
						
					if (!(chkpasswd.equals(passwd))) {
						response.sendRedirect("login.jsp?error1=Wrong password. Please try again");
						con.close();
					}
					
					else {     
						if (chkal.equals("admin")) {
							String querynew = "select * from employee where EMPEMAIL = ?";
							PreparedStatement psnew = con.prepareStatement(querynew);
							
							psnew.setString(1,uname);
							ResultSet rsnew = psnew.executeQuery();
							rsnew.next();
						
							String adfname = rsnew.getString(2);
							String aduname = rsnew.getString(4);
							String adid = rsnew.getString(1);
							
							String querynew1 = "select * from employee";
							PreparedStatement psnew1 = con.prepareStatement(querynew1);
							ResultSet rsnew1 = psnew1.executeQuery();
											
							request.setAttribute("dbdetails", rsnew1);
							RequestDispatcher rd = request.getRequestDispatcher("view.jsp?adfname="+adfname+"&aduname="+aduname+"&adid="+adid); 
							rd.forward(request, response); 
							con.close();	
					
						}
						else {
							response.sendRedirect("welcome.jsp?fname="+fname);	
							con.close();
						}
					}
				}

				else 
				{
					// If the string uname has @ in it then it is an email.

					if (uname.indexOf("@") == -1)   // It means that "@" not found in the string uname, implies that it is an user name 
					{ 
					response.sendRedirect("login.jsp?error2=user name doesn't exist. Please try again");    // Try to give error for email and user name separately
					con.close();				
				}
					else {
						response.sendRedirect("login.jsp?error3=email doesn't exist. Please try again");
						con.close();
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}			