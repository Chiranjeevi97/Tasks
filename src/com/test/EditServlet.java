package com.test;

import java.io.IOException;
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

import com.test.DatabaseConnector;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String al = request.getParameter("al");
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");

		Connection conn = DatabaseConnector.getConnection();

		String query = "UPDATE emp SET fname = ?, accesslevel = ?, pwd = ? WHERE uname = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, fname);
			ps.setString(2, al);
			ps.setString(3, pwd);
			ps.setString(4, uname);
			ps.executeUpdate();
			query = "SELECT * FROM emp";
    		ps = conn.prepareStatement(query);
    		ResultSet rs1 = ps.executeQuery();
    		
    		request.setAttribute("users", rs1);
    		RequestDispatcher rd = request.getRequestDispatcher("view.jsp");
    		rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
