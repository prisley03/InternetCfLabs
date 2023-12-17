package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import connection.ConnectDB;
import model.object.PC;
import model.object.TransactionDetail;

public class TransactionDetailDatabase {
	public ConnectDB con;
		
	public TransactionDetailDatabase() {
		con = ConnectDB.getInstance();
	}
	
	// Retrieve all transaction detail that exist
	public ArrayList<TransactionDetail> getAllTransactionDetail(){
		ArrayList<TransactionDetail> tdList = new ArrayList<TransactionDetail>();
		
		String query = ("SELECT * FROM transactiondetail");
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int transactionId = rs.getInt("TransactionID");
				int pcID = rs.getInt("PC_ID");
				String customerName = rs.getString("CustomerName");
				LocalDateTime bookedTime = rs.getTimestamp("BookedTime").toLocalDateTime();
				
				tdList.add(new TransactionDetail(transactionId, pcID, customerName, bookedTime));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tdList;
	}
	
	//Get all user transaction based on ID
	public ArrayList<TransactionDetail> getUserTransactionDetail(int userId){
		String query = String.format(""
				+ "SELECT * FROM transactiondetail a\r\n"
				+ "JOIN msuser b on a.CustomerName = b.UserName\r\n"
				+ "WHERE b.UserID = %d", userId);
		
		ResultSet rs = con.executeSelectQuery(query);
		ArrayList<TransactionDetail> detailList = new ArrayList<>();
		
		try {
			while(rs.next()) {
				int transactionID = rs.getInt("TransactionID");
				int pcID = rs.getInt("PC_ID");
				String customerName = rs.getString("CustomerName");
		        LocalDateTime bookedTime = rs.getTimestamp("BookedTime").toLocalDateTime(); 
		        
				detailList.add(new TransactionDetail(transactionID, pcID, customerName, bookedTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return detailList;
	}
	
	//Add new user transaction
	public boolean addNewTransactionDetail(int pcID, String custName, String date) {
		String query = String.format("INSERT INTO transactiondetail(PC_ID, CustomerName, BookedTime) VALUES (%d, '%s', '%s')", pcID, custName, date);
		con.executeUpdateQuery(query);
		return true;
	}
	
	
	public ArrayList<TransactionDetail> getTransactionDetailByTransactionID(int transactionID){
		ArrayList<TransactionDetail> tdList = new ArrayList<TransactionDetail>();
		
		String query = (String.format("SELECT * FROM transactiondetail WHERE TransactionID = %d", transactionID ));
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int transactionId = rs.getInt("TransactionID");
				int pcID = rs.getInt("PC_ID");
				String customerName = rs.getString("CustomerName");
				LocalDateTime bookedTime = rs.getTimestamp("BookedTime").toLocalDateTime();
				
				tdList.add(new TransactionDetail(transactionId, pcID, customerName, bookedTime));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tdList;
	}
}
