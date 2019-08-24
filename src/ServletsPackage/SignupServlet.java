package ServletsPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Signup1")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fn = request.getParameter("fname");
		String un = request.getParameter("uname");
		String pass = request.getParameter("password");

		Connection conn = DbConnector.getInstance();
		try {
			String query = "insert into emp values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, fn);
			ps.setString(2, un);
			ps.setString(3, pass);
			int success = ps.executeUpdate();
			if (success > 0) {
				response.sendRedirect("Loginform.jsp");
			} else {

			}
			// ps.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
