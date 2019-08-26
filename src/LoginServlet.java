
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

@WebServlet("/loginsv")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");

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
				
				if (rs.next()) {
					String fname = rs.getString("EMPFIRSTNAME"); 
					String chkpasswd = rs.getString("EMPPASSWORD");
					String chkal = rs.getString("EMPACCESSLEVEL");
						
					if (!(chkpasswd.equals(passwd))) {
						response.sendRedirect("login.jsp?error1=Wrong password. Please try again");
						con.close();
					}
					
					else {     // change to 'if' in case of any error with same condition as above except ! 
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
				
				else {
					response.sendRedirect("login.jsp?error2=user name doesn't exist");
					con.close();				
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}			