package model.database;

import java.sql.ResultSet;

import connection.ConnectDB;
import model.object.User;

public class UserDatabase implements DAO<User> {
	
	public ConnectDB con;
	
	public UserDatabase() {
		con = ConnectDB.getInstance();
	}
	
	public User login(String username, String password) {
		String query = String.format("SELECT * FROM msuser WHERE UserName = '%s' and UserPassword = '%s'", username, password);
	
		ResultSet rs = con.executeSelectQuery(query);
		User user = null;
		
		System.out.println(rs);
		
		try {
			
			if(rs.next()) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void insert(User obj) {
		String query = String.format("INSERT INTO msuser(UserName, UserPassword, UserRole, UserAge) VALUES('%s', '%s', '%s', %d)", obj.getUsername(), obj.getPassword(), obj.getUserRole(), obj.getUserAge());
		System.out.println(query);
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
	public User select(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
