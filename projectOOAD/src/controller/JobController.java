package controller;

import java.util.ArrayList;

import model.database.JobDatabase;
import model.object.Job;
import model.object.PC;

public class JobController {
	private static class SingletonHelper{
		private static final JobController INSTANCE = new JobController();
		private static final JobDatabase jobDB = new JobDatabase();
		private static final PCController pcController = new PCController();
	}
	
	public static JobController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public ArrayList<Job> getAllJobData(){
		return SingletonHelper.jobDB.getAllData();
	}
	
	public ArrayList<Job> getAllJobDataByTechID(int techID){
		return SingletonHelper.jobDB.getAllJobByTechID(techID);
	}
	
	public ArrayList<Job> getJobUncompleteData(){
		return SingletonHelper.jobDB.getJobUncompleteData();
	}
	
	public void markComplete(int pcId) {
		ArrayList<Job>jobUncompleteData = SingletonHelper.jobDB.getJobUncompleteData();
		
		for(Job job: jobUncompleteData) {
			if(job.getPcId() == pcId) {
				SingletonHelper.jobDB.update(job);
			}
		}
	}
	
	public boolean updateStatusJob(int pcId, String jobStatus) {
		//Retrieve job that related to pcID 
		Job toBeUpdatedJob = SingletonHelper.jobDB.getJobByPCID(pcId);
		
		//Save job status in temporary variable
		toBeUpdatedJob.setJobStatus(jobStatus);
		
		///Update PC condition based on jobStatus
		PC updatedPC =  SingletonHelper.pcController.getPCDetail(pcId);
		if(jobStatus.equals("Complete")) {
			SingletonHelper.pcController.updatePCCondition(pcId, "Usable");
		}else if(jobStatus.equals("UnComplete")) {
			SingletonHelper.pcController.updatePCCondition(pcId, "Maintenance");
		}
		
		//Pass the temporary variable to Database to update
		return SingletonHelper.jobDB.updateJobStatus(toBeUpdatedJob);
	}
	
	public boolean addJob(int pcID, String jobStatus, int techID) {
		return SingletonHelper.jobDB.insertJob(pcID, jobStatus, techID);
	}
	
	public ArrayList<Integer> gettAllPossibleMaintenancePCID(){
		return SingletonHelper.jobDB.gettAllPossibleMaintenancePCID();
	}
}
