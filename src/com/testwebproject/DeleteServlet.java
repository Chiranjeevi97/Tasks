package com.testwebproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletesv") // instead of annotations being explicitly exposed here write a properties file? is it correct. in xml u use servlet mapping tag and servlet tags i guess. 
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");  // need not use trim() method here as the values are being accessed from url that in turn are from the database. The values in the db are proper (no leading/trailing spaces).
		String id = request.getParameter("id");
		String al = request.getParameter("al");
		
		String adfname = request.getParameter("adfname"); 
		String aduname = request.getParameter("aduname");
		String adid = request.getParameter("adid");
		
		/*  Provide a prompt before deleting a user or yourself (admin). 
		 * Try without JavaScript 
		 */	
		
		try {	
			// load driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// establish connection
			String urldb = "jdbc:mysql://localhost:3306/empdb";
			String unamedb = "root";
			String passwddb = "salman";
			
			Connection con = DriverManager.getConnection(urldb, unamedb, passwddb);
			
			if (al.equals("user")) { // if access level is "user" then delete that user 
										
				String query = "DELETE FROM EMPLOYEE WHERE EMPUSERNAME = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, uname);
				
				ps.executeUpdate();
									
				String query1 = "select * from employee";
				PreparedStatement ps1 = con.prepareStatement(query1);
				
				ResultSet rs1 = ps1.executeQuery();
				
				request.setAttribute("dbdetails", rs1);// create a object map? not sure what to call it
				RequestDispatcher rd1 = request.getRequestDispatcher("view.jsp?adfname="+adfname+"&aduname="+aduname+"&adid="+adid);
				rd1.forward(request, response);
				
				con.close();
	
			}
			
			else if(al.equals("admin")){ // if admin then redirect to view.jsp saying error that you cannot details of admin 
				
				if (!(adid.equals(id))) // some other admin then you cannot delete him/her !!
				
				{

					String queryfname = "SELECT * FROM EMPLOYEE WHERE EMPID = ?";
					PreparedStatement psfname = con.prepareStatement(queryfname);
					psfname.setString(1, id);
					ResultSet rsfname = psfname.executeQuery();
					rsfname.next();
					
					String fnameuser = rsfname.getString(2);
					
					String queryedit = "SELECT * FROM EMPLOYEE";
					PreparedStatement psedit = con.prepareStatement(queryedit);
					
					ResultSet rsedit = psedit.executeQuery();
					
					
					request.setAttribute("dbdetails", rsedit);
					RequestDispatcher rdedit = request.getRequestDispatcher("view.jsp?erroredit=You cannot delete admin "+fnameuser+"&aduname="+aduname+"&adfname="+adfname+"&adid="+adid);
					rdedit.forward(request, response);
					
					con.close();
				}
				
				else
				{  // deleting yourself (remember you are the admin !!)
					
					String query = "DELETE FROM EMPLOYEE WHERE EMPID = ?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, adid);
					
					ps.executeUpdate();


					// if you delete yourself then just redirect the control back to login.jsp 

					response.sendRedirect("login.jsp");
								
					String query1 = "select * from employee";
					PreparedStatement ps1 = con.prepareStatement(query1);
					
					ResultSet rs1 = ps1.executeQuery();
					
					request.setAttribute("dbdetails", rs1);// create a object map? not sure what to call it
					RequestDispatcher rd1 = request.getRequestDispatcher("view.jsp?adfname="+adfname+"&aduname="+aduname+"&adid="+adid);
					rd1.forward(request, response);
					
					con.close();
					
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}