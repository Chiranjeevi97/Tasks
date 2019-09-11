package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.DatabaseConnector;
public class CreateTable {

	public CreateTable() throws Exception {
		
		Connection conn = DatabaseConnector.getConnection();
		
		/*
		 * String driver = "com.mysql.jdbc.Driver"; try { Class.forName(driver); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); }
		 * 
		 * String url = "jdbc:mysql://localhost:3306/test?useSSL=false"; String username
		 * = "Ashutosh"; String password ="ashutosh"; try { Connection conn =
		 * DriverManager.getConnection(url, username, password);
		 * 
		 * } catch (SQLException e) { e.printStackTrace();
		 * //System.out.println("Database doesn not exist."); }
		 */
		
		Statement st = conn.createStatement();
		String droptable = "DROP TABLE IF EXISTS emp";
		st.executeUpdate(droptable);
		String query = "CREATE TABLE emp(fname varchar(25), accesslevel varchar(25), uname varchar(25), pwd varchar(25), PRIMARY KEY(uname))";
		st.executeUpdate(query);
		st.getConnection().close();
		System.out.println("Employee Table Created");
	}
	public static void main(String[] args) throws Exception {
		new CreateTable();
	}
}
