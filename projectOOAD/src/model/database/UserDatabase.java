package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import connection.ConnectDB;
import model.object.PCBook;
import model.object.User;

public class UserDatabase implements DAO<User> {
	
	public ConnectDB con;
	
	public UserDatabase() {
		con = ConnectDB.getInstance();
	}
	
	public User login(String username, String password) {
		String query = String.format("SELECT * FROM msuser WHERE UserName = '%s' and UserPassword = '%s'", username, password);
	
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			if(rs.next()) {
				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userRole = rs.getString("UserRole");
				int userAge = rs.getInt("UserAge");
				
				return new User(userID, userName, userPassword, userRole, userAge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void insert(User obj) {
		String query = String.format("INSERT INTO msuser(UserName, UserPassword, UserRole, UserAge) VALUES('%s', '%s', '%s', %d)", obj.getUsername(), obj.getPassword(), obj.getUserRole(), obj.getUserAge());
		con.executeUpdateQuery(query);		
		return;
	}

	@Override
	public void update(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User selectByName(String username) {
		String query = String.format("SELECT * FROM msuser WHERE UserName = '%s'", username);
		
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			if(rs.next()) {
				
				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userRole = rs.getString("UserRole");
				int userAge = rs.getInt("UserAge");
				
				return new User(userID, userName, userPassword, userRole, userAge);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User selectById(int id) {
		String query = String.format("SELECT * FROM msuser WHERE UserID = '%d'", id);
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			if(rs.next()) {
				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userRole = rs.getString("UserRole");
				int userAge = rs.getInt("UserAge");
				
				return new User(userID, userName, userPassword, userRole, userAge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<User> selectAllStaff(){
		ArrayList<User> staffList = new ArrayList<User>();

		String query = "SELECT * FROM msuser WHERE NOT msuser.UserRole = 'Customer'";
		ResultSet rs = con.executeSelectQuery(query);
		
		try {
			while(rs.next()) {
				int userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				String userRole = rs.getString("UserRole");
				int userAge = rs.getInt("UserAge");
				
				staffList.add(new User(userID, userName, userPassword, userRole, userAge));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return staffList;
	
	}
	public void delete(int userID) {
		String query = String.format("DELETE FROM msuser WHERE UserID = %d",userID);
		con.executeUpdateQuery(query);
	}
}
