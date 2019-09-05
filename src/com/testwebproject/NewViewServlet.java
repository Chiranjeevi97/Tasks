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


@WebServlet("/newviewsv")
public class NewViewServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fname = request.getParameter("fname"); 
		String lname = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String al = request.getParameter("al");
		String id = request.getParameter("id");
		
		String adid = request.getParameter("adid");
		String aduname = request.getParameter("aduname");
		String adfname = request.getParameter("adfname");
	

				
		if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || al.equals("--select--")) {
			response.sendRedirect("new_view.jsp?error=Please fill all the details&id="+id+"&adid="+adid+"&adfname="+adfname+"&aduname="+aduname+"&al="+al);
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
				
				// check if the user name exists in the database (all users except the current user) then say that user name is in use. 
					
				if (adid.equals(id)){
					
					String chkunamequery = "SELECT * FROM EMPLOYEE WHERE EMPUSERNAME NOT IN (?)"; // ROLL BACK CHANGES FROM HERE to just one condition where empusername = uname.
					PreparedStatement pschkuname = con.prepareStatement(chkunamequery);
					pschkuname.setString(1,aduname);
					ResultSet rschkuname = pschkuname.executeQuery();
					
					while (rschkuname.next()) {
						
						String tempuname = rschkuname.getString(4);
						
						if (uname.equals(tempuname)) {
							response.sendRedirect("new_view.jsp?error1=User name already in use. Please try another user name.&id="+id+"&adid="+adid+"&adfname="+adfname+"&aduname="+aduname+"&al="+al);
							break;
						}
						
						else {
							continue;
						}
					
					}
					
					String pquery = "SELECT * FROM EMPLOYEE WHERE EMPID="+adid;
					PreparedStatement ps4 = con.prepareStatement(pquery);
					ResultSet rs4 = ps4.executeQuery();
					rs4.next();
					
					String passwd = rs4.getString(5);
		
										
					// prepare statement out of a query to update that record in db
					String query = "UPDATE EMPLOYEE SET EMPID = ?, EMPFIRSTNAME = ?, EMPLASTNAME = ?, EMPUSERNAME = ?, EMPPASSWORD = ?, EMPACCESSLEVEL = ? WHERE EMPID = " + id; 
					PreparedStatement ps = con.prepareStatement(query);
					
					ps.setString(1, adid);
					ps.setString(2, fname);
					ps.setString(3, lname);
					ps.setString(4, uname);
					ps.setString(5, passwd);
					ps.setString(6, al);
				
					// execute the prepared statement
					ps.executeUpdate();
										
					response.sendRedirect("login.jsp");
					con.close();

				}
				
				else {
					
					String chkunamequery = "SELECT * FROM EMPLOYEE WHERE EMPUSERNAME NOT IN (?)"; // ROLL BACK CHANGES FROM HERE to just one condition where empusername = uname.
					PreparedStatement pschkuname = con.prepareStatement(chkunamequery);
					pschkuname.setString(1,uname);
					ResultSet rschkuname = pschkuname.executeQuery();
					
					while (rschkuname.next()) {
						
						String tempuname = rschkuname.getString(4);
						
						if (uname.equals(tempuname)) {
							response.sendRedirect("new_view.jsp?error1=User name already in use. Please try another user name.&id="+id+"&adid="+adid+"&adfname="+adfname+"&aduname="+aduname+"&al="+al);
							break;
						}
						
						else {
							continue;
						}
					
					}
							
					String pquery1 = "SELECT * FROM EMPLOYEE WHERE EMPID = " + id;
					PreparedStatement ps41 = con.prepareStatement(pquery1);
					ResultSet rs41 = ps41.executeQuery();
					rs41.next();
					
					String passwd = rs41.getString(5);
		
										
					// prepare statement out of a query to update that record in db
					String query41 = "UPDATE EMPLOYEE SET EMPID = ?, EMPFIRSTNAME = ?, EMPLASTNAME = ?, EMPUSERNAME = ?, EMPPASSWORD = ?, EMPACCESSLEVEL = ? WHERE EMPID = " + id; 
					PreparedStatement ps411 = con.prepareStatement(query41);
					
					ps411.setString(1, id);
					ps411.setString(2, fname);
					ps411.setString(3, lname);
					ps411.setString(4, uname);
					ps411.setString(5, passwd);
					ps411.setString(6, al);
				
					// execute the prepared statement
					ps411.executeUpdate();
					
					String queryfinal11 = "SELECT * FROM EMPLOYEE";
					PreparedStatement psfinal11 = con.prepareStatement(queryfinal11);
					
					ResultSet rsfinal11 = psfinal11.executeQuery();
					
					request.setAttribute("dbdetails", rsfinal11);
					RequestDispatcher rdfinal11 = request.getRequestDispatcher("view.jsp?&aduname="+aduname+"&adfname="+adfname+"&adid="+adid); //destination is view.jsp
					rdfinal11.forward(request, response);
					con.close();
						
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}