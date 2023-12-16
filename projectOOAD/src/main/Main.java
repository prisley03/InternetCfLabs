package main;

import connection.ConnectDB;
import controller.PCController;
import controller.UserController;
import java.util.ArrayList;
import javafx.application.Application;
//import model.database.PCDatabase;
import javafx.stage.Stage;
import model.object.User;
import view.MainPage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
  	public void start(Stage arg0) throws Exception {
		//Setting up initial DB connection
		ConnectDB.getInstance().initializeConnection();
		//User will directly be redirected to the register page
//		User.setActiveUser(UserController.getInstance().getUserData("AveAdmin", "ave123"));
//		UserController.getInstance().navigateToRegister(arg0); 
		User.setActiveUser(UserController.getInstance().getUserData("aveAdmin", "ave123"));
		UserController.getInstance().navigateToMainPage(arg0, User.getActiveUser().getUserRole()); 
	}
}
