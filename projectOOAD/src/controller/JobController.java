package controller;

import java.util.ArrayList;

import model.database.JobDatabase;
import model.object.Job;
import model.object.PC;
import model.object.User;

public class JobController {
	private static class SingletonHelper{
		private static final JobController INSTANCE = new JobController();
		private static final JobDatabase jobDB = new JobDatabase();
		private static final PCController pcController = new PCController();
	}
	

	public static JobController getInstance() {
		return SingletonHelper.INSTANCE;
	}
//	mendapatkan semua job data dari singleton	
	public ArrayList<Job> getAllJobData(){
		return SingletonHelper.jobDB.getAllData();
	}

//	mendapatkan semua job data dari singleton khusus untuk technician yang login dengan userid tertentu
	public ArrayList<Job> getJobforTechinician(){
		int userId = User.getActiveUser().getUserId();
		return SingletonHelper.jobDB.getDataforTechnician(userId);
	}
	public ArrayList<Job> getAllJobDataByTechID(int techID){
		return SingletonHelper.jobDB.getAllJobByTechID(techID);
	}
// mendapatkan data sesuai dengan technician yang login dengan userid tertentu namun hanya menunjukkan yang job status "uncomplete"

	public ArrayList<Job> getJobUncompleteData(){
		int userId = User.getActiveUser().getUserId();
		return SingletonHelper.jobDB.getJobUncompleteData(userId);
	}

// mengubah pc id yang ditekan agar jobstatus diubah dari "Uncomplete" menjadi "Complete"
	public void markComplete(int pcId, int userId) {
		ArrayList<Job>jobUncompleteData = SingletonHelper.jobDB.getJobUncompleteData(userId);
		
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
	
//	Insert job to choosen techID
	public boolean addJob(int pcID, String jobStatus, int techID) {
		return SingletonHelper.jobDB.insertJob(pcID, jobStatus, techID);
	}
	
//	Get all possible maintenance
	public ArrayList<Integer> gettAllPossibleMaintenancePCID(){
		return SingletonHelper.jobDB.gettAllPossibleMaintenancePCID();
	}
}
