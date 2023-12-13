package model.object;

public class Job {
	private int jobId;
	private int userId;
	private int pcId;
	private String jobStatus;
	public Job(int jobId, int userId, int pcId, String jobStatus) {
		super();
		this.jobId = jobId;
		this.userId = userId;
		this.pcId = pcId;
		this.jobStatus = jobStatus;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPcId() {
		return pcId;
	}
	public void setPcId(int pcId) {
		this.pcId = pcId;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	
}
