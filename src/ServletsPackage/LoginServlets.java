package ServletsPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/Login")
public class LoginServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String un= request.getParameter("uname");
		String pass= request.getParameter("password");
	/*
		String driver = "com.mysql.jdbc.Driver";
		Connection conn;
		try {
 		Class.forName(driver);
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
		String username = "root";
		String password = "root";
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			
			
*/          DbConnection b = new DbConnection();
            Connection conn = b.toConnect();
            try {
            Statement st1= conn.createStatement();
			String query1= "select * from emp where uname=\""+un+"\" and pwd=\""+pass+"\";";
			
			ResultSet rs = st1.executeQuery(query1);
			if(rs.next()) {
				HttpSession session=request.getSession();
				session.setAttribute("uname", un);
//				
				response.sendRedirect("Home.jsp");
			}
			else {
//				System.out.println("username doesn't exist");
				response.sendRedirect("Loginform"
						+ ".jsp");
			}
		
			
			st1.getConnection().close();
		} 
	
	catch (SQLException e) {	
			
		}
}
		
//		System.out.println("Employee Table Created");

	
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
