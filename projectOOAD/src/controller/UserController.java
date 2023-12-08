package controller;

import javafx.stage.Stage;
import view.RegisterPage;

public class UserController {
	
	private static class SingletoneHelper{
		private static final UserController INSTANCE = new UserController();
	}
	
	public static UserController getInstance() {
		return SingletoneHelper.INSTANCE;
	}
	
	public void navigate(Stage stage) {
		// TODO Auto-generated method stub
		new RegisterPage(stage);
	}
}
