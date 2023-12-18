package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import connection.ConnectDB;
import model.object.TransactionDetail;
import model.object.TransactionHeader;

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
	
	// Retrieve all transaction detail that exist
	public ArrayList<TransactionHeader> getAllTransactionHeader(){
		ArrayList<TransactionHeader> tdList = new ArrayList<TransactionHeader>();
		
		String query = ("SELECT * FROM transactionheader");
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int transactionId = rs.getInt("TransactionID");
				int staffID = rs.getInt("StaffID");
				String staffName = rs.getString("StaffName");
				Date transactionDate = rs.getDate("TransactionDate");
				
				tdList.add(new TransactionHeader(transactionId, staffID, staffName, transactionDate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tdList;
	}
	
}
