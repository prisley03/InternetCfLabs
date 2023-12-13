package model.object;

import java.util.Date;

public class Report {
	private int reportId;
	private String userRole;
	private String pcId;
	private String reportNote;
	private Date reportDate;
	public Report(int reportId, String userRole, String pcId, String reportNote, Date reportDate) {
		super();
		this.reportId = reportId;
		this.userRole = userRole;
		this.pcId = pcId;
		this.reportNote = reportNote;
		this.reportDate = reportDate;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getReportNote() {
		return reportNote;
	}
	public void setReportNote(String reportNote) {
		this.reportNote = reportNote;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	
}
