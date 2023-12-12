package controller;

import javafx.stage.Stage;
import view.RegisterPage;
import view.RegisterPage.RegistComp;

public class UserController {
	
	private static class SingletonHelper{
		private static final UserController INSTANCE = new UserController();
	}
	
	public static UserController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public void navigate(Stage stage) {
		new RegisterPage(stage);
	}
	
	public void handleUserRegistration(RegistComp userRegis, Stage stage) {
		String userName = userRegis.usernameField.getText();
		String userPassword = userRegis.passwordField.getText();
		String confirmUserPassword = userRegis.cfPasswordField.getText();
		int userAge = userRegis.ageSpinner.getValue();
		
		userRegis.registButton.setOnMouseClicked(e -> {
			//ini masih belom bisa

		});
	}
	
}
