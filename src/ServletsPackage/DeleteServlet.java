package ServletsPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
    
		Connection conn = DbConnector.getInstance();
            try {
			String query="delete from emp where uname=?" ;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, uname);
			ps.executeUpdate();
//			response.sendRedirect("viewServlet.jsp");
			

			//ps.getConnection().close();
		} catch (SQLException e) {	
	 	e.printStackTrace();
		}
            String query = "select * from emp";
    		PreparedStatement ps;
    		try {
    			ps = conn.prepareStatement(query);
    			ResultSet rs = ps.executeQuery();
    			// request.setAttribute("results", rs);
    			// RequestDispatcher rd=request.getRequestDispatcher("view.jsp");
    			// rd.forward(request,response);
    			List<User> users = new ArrayList<User>();
    			while (rs.next()) {
    				User u1 = new User();
    				u1.setFirstname(rs.getString(1));
    				u1.setUsername(rs.getString(2));
    				u1.setPassword(rs.getString(3));
    				users.add(u1);
    			}

    			request.setAttribute("us", users);
    			RequestDispatcher rd = request.getRequestDispatcher("view.jsp");
    			rd.forward(request, response);
	
    		} catch (SQLException e) {
    		}
	}
}

	


	
	
	


