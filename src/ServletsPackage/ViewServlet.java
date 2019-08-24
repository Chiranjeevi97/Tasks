package ServletsPackage;

import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/View1")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Connection conn = DbConnection.getInstance();
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
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
		} catch (SQLException e) {
			System.out.println("Database Connection Unsuccessful. Throw your laptop out the window");
		}
		String query = "select * from emp";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
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
			//ps.getConnection().close();
		} catch (SQLException e) {
		}

		
	}

	// System.out.println("Employee Table Created");


	// response.getWriter().append("Served at: ").append(request.getContextPath());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
