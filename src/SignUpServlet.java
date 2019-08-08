
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String al = request.getParameter("al");
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");

		Connection conn = DatabaseConnector.getConnection();

		String query = "INSERT INTO emp VALUES(?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, fname);
			ps.setString(2, al);
			ps.setString(3, uname);
			ps.setString(4, pwd);
			int ans = ps.executeUpdate();
			if (ans == 1)
				response.sendRedirect("login.jsp");
			else
				response.sendRedirect("signup.jsp");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
