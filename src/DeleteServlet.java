
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = DatabaseConnector.getConnection();

		String uname = request.getParameter("uname");
		String query = "DELETE FROM emp WHERE uname = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, uname);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		query = "SELECT * FROM emp";
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs1 = ps.executeQuery();

			request.setAttribute("users", rs1);

			PrintWriter out = response.getWriter();
			out.print("Updating the user table now.");

			response.sendRedirect("view.jsp");

			System.out.println("Going to updated view now");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
