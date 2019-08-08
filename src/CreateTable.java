import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

	public CreateTable() throws Exception {
		
		Connection conn = DatabaseConnector.getConnection();
		
		Statement st = conn.createStatement();
		String droptable = "DROP TABLE IF EXISTS emp";
		st.executeUpdate(droptable);
		String query = "CREATE TABLE emp(fname varchar(25), accesslevel varchar(25), uname varchar(25), pwd varchar(25), PRIMARY KEY(uname))";
		st.executeUpdate(query);
		st.getConnection().close();
		System.out.println("Employee Table Created");
		//conn.close();
	}
	public static void main(String[] args) throws Exception {
		new CreateTable();
	}
}
