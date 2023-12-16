package controller;

import java.util.ArrayList;

import model.database.JobDatabase;
import model.object.Job;
import model.object.PC;

public class JobController {
	private static class SingletonHelper{
		private static final JobController INSTANCE = new JobController();
		private static final JobDatabase jobDB = new JobDatabase();
	}
	
	public static JobController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public ArrayList<Job> getAllJobData(){
		return SingletonHelper.jobDB.getAllData();
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
	
}
