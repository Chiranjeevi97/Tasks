package ServletsPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

	public CreateTables() {
		String driver = "com.mysql.jdbc.Driver";
		Connection conn;
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
			Statement st = conn.createStatement();
			String droptable = "DROP TABLE IF EXISTS emp";
			st.executeUpdate(droptable);
			String query = "CREATE TABLE emp(fname varchar(25), accesslevel varchar(25), uname varchar(25), pwd varchar(25), PRIMARY KEY(uname))";
			st.executeUpdate(query);
			st.getConnection().close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
		
		System.out.println("Employee Table Created");

	}
	public static void main(String[] args) {
		new CreateTables();
	}

}
