package database;

import connection.ConnectDB;
import model.User;

public class UserDatabase implements DAO<User> {
	
	public ConnectDB con;
	
	public UserDatabase() {
		con = ConnectDB.getInstance();
	}

	@Override
	public void insert(User obj) {
		// TODO Auto-generated method stub
		
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
	public User getObjById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
