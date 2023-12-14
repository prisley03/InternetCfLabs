package model.database;

import connection.ConnectDB;

public class ReportDatabase {
	public ConnectDB con;
	
	public ReportDatabase() {
		con = ConnectDB.getInstance();
	}
	
	
}
