package ServletsPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class DbConnection {
	Connection conn=null;
	public DbConnection() {
	
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
 	e.printStackTrace();
	}
	}
	public Connection toConnect() {
		if(conn==null) {
			new DbConnection();
		}
		return conn;
		
	}
	
	
}
