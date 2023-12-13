package controller;

import java.util.ArrayList;

import javafx.stage.Stage;
import model.database.UserDatabase;
import model.object.User;
import utility.StringUtility;
import view.LoginPage;
import view.RegisterPage;
import view.RegisterPage.RegistComp;

public class UserController {
	
	private static class SingletonHelper{
		private static final UserController INSTANCE = new UserController();
	}
	
	public static UserController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public void navigateToRegister(Stage stage) {
		new RegisterPage(stage);
	}
	
	public void navigateToLogin(Stage stage) {
		new LoginPage(stage);
	}
	
	
	public boolean validateNewUser(RegistComp obj) {
		
		String username = obj.usernameField.getText();
		String password = obj.passwordField.getText();
		String confirmPassword = obj.cfPasswordField.getText();
		int userAge = obj.ageSpinner.getValue();
				
		if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
			obj.errorMessage.setText("Fields cannot be empty");
			return false;
		} else if (username.length() < 7 || password.length() < 6) {
		    obj.errorMessage.setText("Username length must be at least 7, and password must be at least 6 characters");
			return false;
		} else if (!StringUtility.isAlphanumeric(password)) {
		    obj.errorMessage.setText("Password must be alphanumeric");
			return false;
		} else if (!password.equalsIgnoreCase(confirmPassword)) {
		    obj.errorMessage.setText("Confirm pasword must equal to password");
			return false;			
		} else if (userAge < 13 || userAge > 65) {
		    obj.errorMessage.setText("Age must be between 13 and 65");
			return false;
		}
		
	    obj.errorMessage.setText("");
		return true;
	}
	
	public User GetUserData(String Username, String Password) {
		User user = null;
		
		return user;
	}
	
	public void AddNewUser(String Username, String Password, int Age) {
		
		UserDatabase userDB = new UserDatabase();
		User user = new User(Username, Password, Age, "Customer");
		
		userDB.insert(user);
	}
	
	public ArrayList<User> GetAllTechnician() {
		ArrayList<User> technicianList = new ArrayList<>();
		
		return technicianList;
	}
	
	public ArrayList<User> GetAllUserData(){
		ArrayList<User> userList = new ArrayList<>();
		
		return userList;
	}
	
	
}
