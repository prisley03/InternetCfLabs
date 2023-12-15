package controller;

import java.util.Date;
import model.database.ReportDatabase;
import model.object.Report;
import view.MakeReportPage.MakeReportObj;

public class ReportController {

  private static class SingletonHelper {

    private static final ReportController INSTANCE = new ReportController();
    private static final ReportDatabase reportDB = new ReportDatabase();
  }

  public static ReportController getInstance() {
    return SingletonHelper.INSTANCE;
  }

  public boolean validateNewReport(MakeReportObj obj) {
    if (obj.notesInput.getText().isEmpty()) {
      obj.message.setText("Notes must be filled");
      return false;
    }
    if (obj.pcIDInput.getValue().equals("Select All")) {
      obj.message.setText("Please choose a PC ID");
      return false;
    }

    obj.message.setText("Successfully Inserted !");
    return true;
  }

  public void addNewReport(String role, String pcID, String notes) {
    int id = Integer.parseInt(pcID.split(" ", 2)[1]);
    Report report = new Report(0, role, id, notes, new Date());
    SingletonHelper.reportDB.insert(report, role);
  }
}
