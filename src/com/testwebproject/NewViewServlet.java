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


@WebServlet("/newviewsv")                                                         // get rid of annotation. web.xml file must have servlet class and url mapping defined in it.
public class NewViewServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fname = request.getParameter("fname").trim(); 
		String lname = request.getParameter("lname").trim();
		String uname = request.getParameter("uname").trim();                      // can change user name but not email address under any circumstances. The variable uname here will be the one which you enter in new_view.jsp but not the one in database.
		String al = request.getParameter("al");
		String id = request.getParameter("id");
		
		String exuname = request.getParameter("exuname");
		String email = request.getParameter("email");
		String adid = request.getParameter("adid");
		String aduname = request.getParameter("aduname");
		String adfname = request.getParameter("adfname");
	
		
		if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || al.equals("--select--"))   // Check if any one of the fields is empty. If so then redirect back to the same page
		{
			response.sendRedirect("new_view.jsp?error=Please fill all the details&id="+id+"&adid="+adid+"&adfname="+adfname+"&aduname="+aduname+"&al="+al+"&email="+email+"&exuname="+exuname);
		}
		
		else 
			{
				try {
				// load driver 
				Class.forName("com.mysql.jdbc.Driver");
				
				// establish connection
				String urldb = "jdbc:mysql://localhost:3306/empdb";
				String usernamedb = "root"; 
				String passwddb = "salman";
				
				Connection con = DriverManager.getConnection(urldb, usernamedb, passwddb); 
				
				// check if the user name exists in the database (all users except the current user) then say that user name is in use  

				// before proceeding any further, like updating the details of the user, first try to get that user's existing details from database

				String normalquery = "SELECT * FROM EMPLOYEE WHERE EMPID = "+id;
				PreparedStatement psnormal = con.prepareStatement(normalquery);
				ResultSet rsnormal = psnormal.executeQuery();
				
				rsnormal.next();
				
				
				//  IMPORTANT POINT :::   Reason to keep both separate are because if you change your access level, then it makes sense to not see view.jsp as only admin can view 	
				
				if (adid.equals(id)){                                                                            // you are editing details of yourself (admin) 
					
					String chkunamequery = "SELECT * FROM EMPLOYEE WHERE EMPUSERNAME NOT IN (?)"; 
					PreparedStatement pschkuname = con.prepareStatement(chkunamequery);
					pschkuname.setString(1,exuname);
					ResultSet rschkuname = pschkuname.executeQuery();
					
					while (rschkuname.next()) {
						
						String tempuname = rschkuname.getString(4);                                 
						
						if (uname.equals(tempuname)) {
							response.sendRedirect("new_view.jsp?error1=User name already in use. Please try another user name&id="+id+"&adid="+adid+"&adfname="+adfname+"&aduname="+aduname+"&al="+al+"&email="+email+"&uname="+uname+"&exuname="+exuname);
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
					String query = "UPDATE EMPLOYEE SET EMPID = ?, EMPFIRSTNAME = ?, EMPLASTNAME = ?, EMPUSERNAME = ?, EMPPASSWORD = ?, EMPACCESSLEVEL = ?, EMPEMAIL = ? WHERE EMPID = ?"; 
					PreparedStatement ps = con.prepareStatement(query);
					
					ps.setString(1, adid);
					ps.setString(2, fname);
					ps.setString(3, lname);
					ps.setString(4, uname);                                  // update user name with the one you entered in new_view.jsp, which is uname
					ps.setString(5, passwd);
					ps.setString(6, al);
					ps.setString(7, email);
					ps.setString(8, id);
				
					// execute the prepared statement
					ps.executeUpdate();
										
					response.sendRedirect("login.jsp");
					con.close();
					
				}
				
				else {                                                       // you are editing details of a regular user, not admin 
					
					// if uname entered exists in database then redirect back to new_view.jsp stating that the user name already exists and also try to use the same error variable which you used for admin editing (editing yourself)
					
					String chkunamequery1 = "SELECT * FROM EMPLOYEE WHERE EMPUSERNAME NOT IN (?)"; // ROLL BACK CHANGES FROM HERE to just one condition where empusername = uname.
					PreparedStatement pschkuname1 = con.prepareStatement(chkunamequery1);
					pschkuname1.setString(1,exuname); // this is the one which user enters in new_view.jsp. This won't work, try other way
					ResultSet rschkuname1 = pschkuname1.executeQuery();
					
					while (rschkuname1.next()) {
						
						String tempuname1 = rschkuname1.getString(4);
						
						if (uname.equals(tempuname1)) 
						{
							response.sendRedirect("new_view.jsp?error1=User name already in use. Please try another user name&id="+id+"&adid="+adid+"&adfname="+adfname+"&aduname="+aduname+"&al="+al+"&email="+email+"&exuname="+exuname);
							break;
						}
						
						else 
						{
							continue;
						}
						
					}
							
					String pquery12 = "SELECT * FROM EMPLOYEE WHERE EMPID = " + id;
					PreparedStatement ps412 = con.prepareStatement(pquery12);
					ResultSet rs412 = ps412.executeQuery();
					rs412.next();
					
					String passwdr = rs412.getString(5);
							
					// prepare statement out of a query to update that record in db
					
					String query413 = "UPDATE EMPLOYEE SET EMPID = ?, EMPFIRSTNAME = ?, EMPLASTNAME = ?, EMPUSERNAME = ?, EMPPASSWORD = ?, EMPACCESSLEVEL = ?, EMPEMAIL = ? WHERE EMPID = " + id; 
					PreparedStatement ps4113 = con.prepareStatement(query413);
					
					ps4113.setString(1, id);
					ps4113.setString(2, fname);
					ps4113.setString(3, lname);
					ps4113.setString(4, uname);
					ps4113.setString(5, passwdr);
					ps4113.setString(6, al);
					ps4113.setString(7, email);
				
					// execute the prepared statement
					ps4113.executeUpdate();
					
					String queryfinal114 = "SELECT * FROM EMPLOYEE";
					PreparedStatement psfinal114 = con.prepareStatement(queryfinal114);
					
					ResultSet rsfinal114 = psfinal114.executeQuery();
					
					request.setAttribute("dbdetails", rsfinal114);
					RequestDispatcher rdfinal114 = request.getRequestDispatcher("view.jsp?&aduname="+aduname+"&adfname="+adfname+"&adid="+adid);     
					rdfinal114.forward(request, response);
					con.close();
						
					}
				
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}