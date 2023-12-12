package model;

public class User {
	private int userId;
	private String username;
	private String password;
	private int userAge;
	private String userRole;
	public User(int userId, String username, String password, int userAge, String userRole) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userAge = userAge;
		this.userRole = userRole;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	

}
