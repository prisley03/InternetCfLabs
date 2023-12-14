package main;

import java.util.ArrayList;

import connection.ConnectDB;
import controller.PCController;
import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.object.PC;
import view.BookPCPage;
import view.ViewAllPC;

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
