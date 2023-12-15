package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import connection.ConnectDB;
import model.object.PC;
import model.object.PCBook;

public class PCBookDatabase implements DAO<PCBook> {
	
	public ConnectDB con;
	
	public PCBookDatabase() {
		con = ConnectDB.getInstance();
	}
	
	@Override
	public PCBook selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PCBook selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(PCBook obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PCBook obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PCBook obj) {
		// TODO Auto-generated method stub
		
	}
	
	public void delete(LocalDate date) {
		String query = String.format("DELETE FROM mspcbook WHERE BookedDate = '%s'", date);
		con.executeUpdateQuery(query);
		return;
	}
	
	//Retrieve PCBook data based on PCID and BookingDate
	public PCBook getPCBookedData(int id, String date) {
		String query = String.format("SELECT * FROM mspc a\r\n"
				+ "JOIN mspcbook b ON a.PC_ID = b.PC_ID\r\n"
				+ "WHERE a.PC_Condition LIKE 'Usable'\r\n"
				+ "AND a.PC_ID = %d\r\n"
				+ "AND DATE(b.BookedDate) = '%s'", id, date);
		
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			if(rs.next()) {
				int bookId  = rs.getInt("BookID");
				int pcId  = rs.getInt("PC_ID");
				int userId  = rs.getInt("UserID");
				Date bookedDate = rs.getDate("BookedDate");
				return new PCBook(bookId, pcId, userId, bookedDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Insert new PCBook
	public void AddNewBook(int pcId, int userId, String bookedDate) {
		String query = String.format("INSERT INTO mspcbook(PC_ID, UserID, BookedDate) VALUES(%d, %d, '%s')", pcId, userId, bookedDate);
		con.executeUpdateQuery(query);
		return;
	}
	
	//Retrieving all data without filter
	public ArrayList<PCBook> getAllPCBooked() {
		ArrayList<PCBook> bookedPC = new ArrayList<PCBook>();
		
		String query = "SELECT * FROM mspcbook";
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int bookID = rs.getInt("BookID");
				int pcID = rs.getInt("PC_ID");
				int userID = rs.getInt("UserID");
				Date bookedDate = rs.getDate("BookedDate");
				
				bookedPC.add(new PCBook(bookID, pcID, userID, bookedDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookedPC;
	}
	
	//Change user on existing PC booking
	public void updateChangedPCToUser(int pcID, int userID, String date) {
		String query = String.format("UPDATE mspcbook SET PC_ID = %d WHERE UserID = %d AND BookedDate = '%s'", pcID, userID, date);
		con.executeUpdateQuery(query);
	}

}
