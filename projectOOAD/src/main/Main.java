package main;

import connection.ConnectDB;
import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.PCDatabase;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		ConnectDB.getInstance().initializeConnection();
		UserController.getInstance().navigateToRegister(arg0);
	}
}
