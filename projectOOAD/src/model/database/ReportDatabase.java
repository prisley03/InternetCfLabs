package model.database;

import connection.ConnectDB;
import model.object.Report;

public class ReportDatabase implements DAO<Report> {

  public ConnectDB con;

  public ReportDatabase() {
    con = ConnectDB.getInstance();
  }

  @Override
  public Report selectById(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Report selectByName(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void insert(Report obj) {
    // TODO Auto-generated method stub

  }

  public void insert(Report obj, String role) {
    String query = String.format(
      "INSERT INTO msreport(UserRole, PC_ID, ReportNote) VALUES('%s', %d, '%s')",
      role,
      obj.getPcId(),
      obj.getReportNote()
    );
    con.executeUpdateQuery(query);

    return;
  }

  @Override
  public void update(Report obj) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(Report obj) {
    // TODO Auto-generated method stub

  }
}
