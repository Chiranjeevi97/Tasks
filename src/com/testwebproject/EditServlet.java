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


@WebServlet("/editsv")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String exuname = request.getParameter("exuname");
		String id = request.getParameter("id");
		String al = request.getParameter("al");
		
		String email = request.getParameter("email");                         // In no case you shall edit email though. 
		String adfname = request.getParameter("adfname"); 
		String aduname = request.getParameter("aduname");
		String adid = request.getParameter("adid");
		
		
		try {	
			// load driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// establish connection
			String urldb = "jdbc:mysql://localhost:3306/empdb";
			String unamedb = "root";
			String passwddb = "salman";
			
			Connection con = DriverManager.getConnection(urldb, unamedb, passwddb);
			
			if (al.equals("user")) {                                          // if user then edit his/her details
				
				String queryup = "SELECT * FROM EMPLOYEE WHERE EMPID = ?";
				PreparedStatement psup = con.prepareStatement(queryup);
				psup.setString(1, id);
				
				psup.executeQuery();
							
				response.sendRedirect("new_view.jsp?al="+al+"&id="+id+"&email="+email+"&aduname="+aduname+"&adid="+adid+"&adfname="+adfname+"&exuname="+exuname); 
				
				con.close();
				
			}
			
			else if(al.equals("admin")){                                      // if admin then redirect to view.jsp saying error that you cannot details of admin 
				
				if (!(adid.equals(id))){

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
					RequestDispatcher rdedit = request.getRequestDispatcher("view.jsp?erroredit=You cannot edit details of admin "+fnameuser+"&aduname="+aduname+"&adfname="+adfname+"&adid="+adid);
					rdedit.forward(request, response);
					
					con.close();
				}
				
				else{ 
						
						String query3 = "SELECT * FROM EMPLOYEE WHERE EMPID = ?";
						PreparedStatement ps3 = con.prepareStatement(query3);
						ps3.setString(1, adid);
						
						ps3.executeQuery();
									
						response.sendRedirect("new_view.jsp?al="+al+"&id="+id+"&email="+email+"&aduname="+aduname+"&adid="+adid+"&adfname="+adfname+"&exuname="+exuname); 
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}