package controller;


import model.database.PCDatabase;
import model.database.ReportDatabase;
import view.MakeReportPage.MakeReportPageObj;

public class ReportController {
	private static class SingletonHelper{
		private static final ReportController INSTANCE = new ReportController();
		private static final ReportDatabase pcDB = new ReportDatabase();
	}
	
	public static ReportController getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public Object validateNewReport(MakeReportPageObj obj) {
		
		if(obj.reportField.getText().isEmpty()) {
			
		}
		
		return null;
	}
}
