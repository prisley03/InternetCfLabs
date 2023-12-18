package main;

import connection.ConnectDB;
import controller.PCController;
import controller.UserController;
import java.util.ArrayList;
import javafx.application.Application;
//import model.database.PCDatabase;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
  	public void start(Stage arg0) throws Exception {
		//Setting up initial DB connection
		ConnectDB.getInstance().initializeConnection();
		//User will directly be redirected to the register page
		UserController.getInstance().navigateToRegister(arg0); 
	}
}
