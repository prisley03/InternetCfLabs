package controller;

import java.util.ArrayList;

import model.database.JobDatabase;
import model.object.Job;
import model.object.User;

public class JobController {
	private static class SingletonHelper{
		private static final JobController INSTANCE = new JobController();
		private static final JobDatabase jobDB = new JobDatabase();
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
	
}
