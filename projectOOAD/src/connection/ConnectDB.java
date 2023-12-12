package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	
	private final String HOST = "localhost:3306";
	private final String DATABASE = "internetcf";
	
	private final String URL = String.format("jdbc:mysql://%s/%s?user=%s&password=%s", HOST, DATABASE, USERNAME, PASSWORD);
	
	private static Connection con;
	private static Statement st;
	
	public static ConnectDB connect;
	public ResultSet rs;
	
	public static ConnectDB getInstance() {
		if(connect==null) {
			return new ConnectDB();
		}
		return connect;
	}
		
	public void initializeConnection() {
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Use this function to select data
	public ResultSet executeSelectQuery(String query) {
		try {
			rs = st.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//Use this function to carry out insert, update, delete operations
	public void executeUpdateQuery(String query) {
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
