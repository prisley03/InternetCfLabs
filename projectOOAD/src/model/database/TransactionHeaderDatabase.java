package model.database;

import connection.ConnectDB;

public class TransactionHeaderDatabase {
	public ConnectDB con;

	public TransactionHeaderDatabase() {
		con = ConnectDB.getInstance();
	}
	
	public boolean addNewTransactionHeader(int id, String name, String bookedDate) {
		String query = String.format("INSERT INTO transactionheader(StaffID, StaffName, TransactionDate) VALUES (%d, '%s', '%s')", id, name, bookedDate);
		con.executeUpdateQuery(query);
		return true;
	}
}
