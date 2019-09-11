package com.test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
	// These are just the comments to test pushing to a new branch
	private static Connection conn = null;
	public DatabaseConnector() {
		Properties prop = QueryProperties.getProp();
		
		String driver = prop.getProperty("task.driver");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}

		String url = prop.getProperty("task.url");
		String username = prop.getProperty("task.username");
		String password  = prop.getProperty("task.password");
		try {
		conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	public static Connection getConnection() 
	{		
		if(conn == null)
		{
			new DatabaseConnector();
		}
		
		return conn;
	}
	
}
