package main;

import connection.ConnectDB;
import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		ConnectDB.getInstance().initializeConnection();
		UserController.getInstance().navigate(arg0);

		//String query = String.format("INSERT INTO msuser(UserName, UserPassword, UserRole, UserAge) VALUES('%s', '%s', '%s', %d)", "Name", "Password", "Role", 20);
		//ConnectDB.getInstance().executeUpdateQuery(query);		
	}

}
