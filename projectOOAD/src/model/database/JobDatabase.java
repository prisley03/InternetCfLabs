package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import connection.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.object.Job;
import model.object.PC;
import model.object.PCBook;

public class JobDatabase implements DAO<Job> {
	public ConnectDB con;
	
	public JobDatabase() {
		con = ConnectDB.getInstance();
	}
	
	public ArrayList<Job> getAllData(){ 
		ArrayList<Job> jobList = new ArrayList<Job>();
		
		String query = ("SELECT * FROM msjob");
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int jobID = rs.getInt("Job_ID");
				int userId = rs.getInt("UserID");
	            int pcId = rs.getInt("PC_ID");
	            String jobStatus = rs.getString("JobStatus");
	            
	            jobList.add(new Job(jobID, userId, pcId, jobStatus));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jobList;
	}

	@Override
	public Job selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Job obj) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Job obj) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Job> getJobUncompleteData() {
		ArrayList<Job>uncompletePC = new ArrayList<>();
		String query = String.format("SELECT * FROM msjob a\r\n"
				+ "JOIN msjob b ON a.PC_ID = b.PC_ID\r\n"
				+ "WHERE a.JobStatus LIKE 'Uncomplete'\r\n");

		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int jobID = rs.getInt("Job_ID");
				int userId = rs.getInt("UserID");
	            int pcId = rs.getInt("PC_ID");
	            String jobStatus = rs.getString("JobStatus");
	            
	            uncompletePC.add(new Job(jobID, userId, pcId, jobStatus));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uncompletePC;
	}
	
	public void update(Job obj) {
		
		int pcId = obj.getPcId();
		// TODO Auto-generated method stub
		String updateJobStatusQuery = String.format("UPDATE `msjob` SET JobStatus = 'Complete' WHERE PC_ID = %d", pcId);
		con.executeUpdateQuery(updateJobStatusQuery);
	}
}
