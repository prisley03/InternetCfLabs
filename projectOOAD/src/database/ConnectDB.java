package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	
	private final String HOST = "localhost:3306";
	private final String DATABASE = "internetcf";
	
	private final String URL = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection con;
	private Statement st;
	
	public static ConnectDB connect;
	public ResultSet rs;
	
	public static ConnectDB getInstance() {
		if(connect==null) {
			return new ConnectDB();
		}
		return connect;
		
	}
	
}
