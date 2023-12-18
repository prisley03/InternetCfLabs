package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import model.object.Job;
import model.object.User;

public class JobDatabase implements DAO<Job> {
	public ConnectDB con;
	
	public JobDatabase() {
		con = ConnectDB.getInstance();
	}
//	retrive all the data from database
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
	
//retrieve the data based on Technician' userId
	public ArrayList<Job> getDataforTechnician(int userID){
		ArrayList<Job> jobList = new ArrayList<>();
		String query = String.format("SELECT * FROM msjob where UserID = %d", userID);
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int jobID = rs.getInt("Job_ID");
	            int pcId = rs.getInt("PC_ID");
	            String jobStatus = rs.getString("JobStatus");
	            
	            jobList.add(new Job(jobID, userID, pcId, jobStatus));
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

//	retrieve the data for just combobox to just show the job status that is uncomplete  
	public ArrayList<Job> getJobUncompleteData(int UserId) {
		ArrayList<Job>uncompletePC = new ArrayList<>();
		String query = String.format("SELECT * FROM msjob WHERE JobStatus LIKE 'Uncomplete' AND UserID = %d", UserId);

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

//	update the job status from "Uncomplete" to "Complete
	public void update(Job obj) {
		
		int pcId = obj.getPcId();
		// TODO Auto-generated method stub
		String updateJobStatusQuery = String.format("UPDATE `msjob` SET JobStatus = 'Complete' WHERE PC_ID = %d", pcId);
		con.executeUpdateQuery(updateJobStatusQuery);
	}
}
