package controller;

import java.util.ArrayList;

import javafx.stage.Stage;
import model.database.UserDatabase;
import model.object.User;
import utility.StringUtility;
import view.LoginPage;
import view.LoginPage.LoginComp;
import view.RegisterPage;
import view.RegisterPage.RegistComp;

public class UserController {
	
	private static class SingletonHelper{
		private static final UserController INSTANCE = new UserController();
		private static final UserDatabase userDB = new UserDatabase();
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
	
	public boolean validateRegister(RegistComp obj) {
		
		String username = obj.usernameField.getText();
		String password = obj.passwordField.getText();
		String confirmPassword = obj.cfPasswordField.getText();
		int userAge = obj.ageSpinner.getValue();
		
		UserDatabase userDB = new UserDatabase();
				
		if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
			obj.errorMessage.setText("Fields must not be empty!");
			return false;
		} else if (userDB.selectByName(username) != null) {
			obj.errorMessage.setText("Username must be unique!");
			return false;
		} else if (username.length() < 7 || password.length() < 6) {
		    obj.errorMessage.setText("Username length must be at least 7, and password must be at least 6 characters!");
			return false;
		} else if (!StringUtility.isAlphanumeric(password)) {
		    obj.errorMessage.setText("Password must be alphanumeric!");
			return false;
		} else if (!password.equalsIgnoreCase(confirmPassword)) {
		    obj.errorMessage.setText("Confirm password must equal to password!");
			return false;			
		} else if (userAge < 13 || userAge > 65) {
		    obj.errorMessage.setText("Age must be between 13 and 65!");
			return false;
		}
		
	    obj.errorMessage.setText("");
		return true;
	}
	
	public boolean validateLogin(LoginComp obj) {
		String username = obj.usernameField.getText();
		String password = obj.passwordField.getText();

		if (username.isEmpty() || password.isEmpty()) {
			obj.errorMessage.setText("Fields must not be empty!");
			return false;
		} else if (getUserData(obj.usernameField.getText(), obj.passwordField.getText()) == null) {
			obj.errorMessage.setText("User does not exist!");
			return false;
		}
		
		obj.errorMessage.setText("");
		return true;
	}
	
	public User getUserData(String Username, String Password) {		
		User user = SingletonHelper.userDB.login(Username, Password);
		return user;
	}
	
	public void addNewUser(String Username, String Password, int Age) {
		User user = new User(0, Username, Password, "Customer", Age);
		SingletonHelper.userDB.insert(user);
	}
	
	public ArrayList<User> getAllTechnician() {
		ArrayList<User> technicianList = new ArrayList<>();
		
		return technicianList;
	}
	
	public ArrayList<User> getAllUserData(){
		ArrayList<User> userList = new ArrayList<>();
		
		return userList;
	}
}
