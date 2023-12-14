package model.database;

import java.sql.ResultSet;
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

}
