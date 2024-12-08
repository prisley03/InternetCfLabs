package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import connection.ConnectDB;
import model.object.Report;
import model.object.TransactionDetail;

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
  
// Retrieve all report that exist
public ArrayList<Report> selectAll(){
	ArrayList<Report> reportList = new ArrayList<Report>();
	
	String query = ("SELECT * FROM msreport");
	ResultSet rs = con.executeSelectQuery(query);
	
	try {
		while(rs.next()) {
			int Report_ID = rs.getInt("Report_ID");
			String UserRole = rs.getString("UserRole");
			int PC_ID = rs.getInt("PC_ID");
			String ReportNote = rs.getString("ReportNote");
			
			Date bookedTime = rs.getDate("ReportDate");
			
			reportList.add(new Report(Report_ID, UserRole, PC_ID, ReportNote, bookedTime));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return reportList;
}
}
