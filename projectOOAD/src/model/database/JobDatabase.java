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
		
	public ArrayList<Job> getAllJobByTechID(int techID){ 
		ArrayList<Job> jobList = new ArrayList<Job>();
		
		String query = (String.format("SELECT * FROM msjob WHERE UserID = %d", techID));
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
	
	public Job getJobByPCID(int pcID){ 
		Job ketemu= null;
		
		String query = (String.format("SELECT * FROM msjob WHERE PC_ID = %d", pcID));
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int jobID = rs.getInt("Job_ID");
				int userId = rs.getInt("UserID");
	            int pcId = rs.getInt("PC_ID");
	            String jobStatus = rs.getString("JobStatus");
	            
	            ketemu = new Job(jobID, userId, pcId, jobStatus);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketemu;
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
	
	// public ArrayList<Job> getJobUncompleteDataByTechID(int techID) {
	// 	ArrayList<Job>uncompletePC = new ArrayList<>();
	// 	String query = String.format("SELECT Job_ID\r\n"
	// 			+ "FROM msjob a\r\n"
	// 			+ "JOIN mspc b ON a.PC_ID = b.PC_ID\r\n"
	// 			+ "WHERE a.JobStatus LIKE 'Uncomplete' AND a.UserID = %d\r\n", techID );

	// 	ResultSet rs = con.executeSelectQuery(query);
		
	// 	try {
	// 		while(rs.next()) {
	// 			int jobID = rs.getInt("Job_ID");
	// 			int userId = rs.getInt("UserID");
	//             int pcId = rs.getInt("PC_ID");
	//             String jobStatus = rs.getString("JobStatus");
	            
	//             uncompletePC.add(new Job(jobID, userId, pcId, jobStatus));  
	// 		}
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 	}
		
	// 	return uncompletePC;
	// }
	
	public ArrayList<Job> getJobUncompleteDataByTechID(int techID) {
		ArrayList<Job>uncompletePC = new ArrayList<>();
		String query = String.format("SELECT Job_ID\r\n"
				+ "FROM msjob a\r\n"
				+ "JOIN mspc b ON a.PC_ID = b.PC_ID\r\n"
				+ "WHERE a.JobStatus LIKE 'Uncomplete' AND a.UserID = %d\r\n", techID );

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
	
	
	
	public boolean updateJobStatus(Job obj) {
		
		int pcId = obj.getPcId();
		// TODO Auto-generated method stub
		String updateJobStatusQuery = String.format("UPDATE `msjob` SET JobStatus = '%s' WHERE PC_ID = %d", obj.getJobStatus(), pcId);
		con.executeUpdateQuery(updateJobStatusQuery);
		return true;
	}
	
	public boolean insertJob(int pcID, String jobStatus, int techID) {
		String query = (String.format("INSERT INTO msjob (UserID, PC_ID, JobStatus ) VALUES \r\n"
				+ "(%d,%d,'%s')", techID, pcID, jobStatus));
		con.executeUpdateQuery(query);
		return true;
	}
	
	public ArrayList<Integer> gettAllPossibleMaintenancePCID(){
		ArrayList<Integer> possiblePC = new ArrayList<>();
		String query = String.format("SELECT a.PC_ID, a.PC_Condition\r\n"
				+ "FROM mspc a\r\n"
				+ "LEFT JOIN msjob b ON a.PC_ID = b.PC_ID\r\n"
				+ "WHERE b.PC_ID IS NULL;\r\n"
		 );

		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
	            int pcId = rs.getInt("PC_ID");

	            possiblePC.add(pcId);  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return possiblePC;
	}
}
