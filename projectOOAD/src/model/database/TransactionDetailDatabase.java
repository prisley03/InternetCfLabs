package model.database;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connection.ConnectDB;
import model.object.PC;
import model.object.TransactionDetail;

public class TransactionDetailDatabase {
	public ConnectDB con;
		
	public TransactionDetailDatabase() {
		con = ConnectDB.getInstance();
	}
	
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
	
	public boolean addNewTransactionDetail(int pcID, String custName, String date) {
		String query = String.format("INSERT INTO transactiondetail(PC_ID, CustomerName, BookedTime) VALUES (%d, '%s', '%s')", pcID, custName, date);
		con.executeUpdateQuery(query);
		return true;
	}
}
