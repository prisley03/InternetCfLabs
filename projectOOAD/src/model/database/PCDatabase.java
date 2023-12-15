package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import model.object.PC;
import model.object.User;

public class PCDatabase implements DAO<PC>{
	
	public ConnectDB con;
	
	public PCDatabase() {
		con = ConnectDB.getInstance();
	}
	
	public ArrayList<PC> getAllData(){ 
		ArrayList<PC> pcList = new ArrayList<PC>();
		
		String query = ("SELECT * FROM mspc");
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int pcID = rs.getInt("PC_ID");
				String pcCondition = rs.getString("PC_Condition");
				
				pcList.add(new PC(pcID, pcCondition));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return pcList;
	}

	@Override
	public PC selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PC selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(PC obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PC obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PC obj) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<PC> getPCDataByDateAndId(int id, String bookingDate) {
		ArrayList<PC> pcList = new ArrayList<PC>();
		
		String query = String.format((
				"SELECT *\r\n"
				+ "FROM mspc a\r\n"
				+ "WHERE a.PC_Condition LIKE 'Usable'\r\n"
				+ "AND (%d = -1 OR a.PC_ID = %d)\r\n"
				+ "AND NOT EXISTS (\r\n"
				+ "    SELECT 1\r\n"
				+ "    FROM mspcbook b\r\n"
				+ "    WHERE a.PC_ID = b.PC_ID\r\n"
				+ "    AND DATE(b.BookedDate) = '%s'\r\n"
				+ ");"), id, id, bookingDate);
		
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int pcID = rs.getInt("PC_ID");
				String pcCondition = rs.getString("PC_Condition");
				
				pcList.add(new PC(pcID, pcCondition));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return pcList;
	}

	public PC getPCDetail(int id) {
		String query = String.format("SELECT * FROM mspc WHERE PC_ID = %d", id);
		
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			if(rs.next()) {
				int pcId  = rs.getInt("PC_ID");
				String pcCondition = rs.getString("PC_Condition");
				return new PC(pcId, pcCondition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
