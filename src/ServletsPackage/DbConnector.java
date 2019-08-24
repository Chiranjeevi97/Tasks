package ServletsPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	
	private static Connection conn = null;
	public DbConnector() {
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
	}
	
	public static Connection getInstance() {
		if(conn == null)
			new DbConnector();
		return conn;
	}
	
	
}
