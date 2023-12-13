package model.object;

public class User {
	private String username;
	private String password;
	private int userAge;
	private String userRole;
	public User(String username, String password, int userAge, String userRole) {
		super();
		this.username = username;
		this.password = password;
		this.userAge = userAge;
		this.userRole = userRole;
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
