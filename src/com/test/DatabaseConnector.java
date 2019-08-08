package com.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	
	
	// These are just the comments to test pushing to a new branch
	
	private static Connection conn;
	public DatabaseConnector() {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/teja?useSSL=false";
		String username = "Ashutosh";
		String password  ="ashutosh";
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		new DatabaseConnector();
		return conn;
	}
	
}
