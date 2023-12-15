package main;

import java.util.ArrayList;

import connection.ConnectDB;
import controller.PCController;
import controller.UserController;
import javafx.application.Application;
import model.object.PC;
import model.object.User;
import javafx.stage.Stage;
import view.BookPCPage;
import view.MainPage;
import view.MakeReportPage;
import view.ViewAllPC;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		ConnectDB.getInstance().initializeConnection();
		User.setActiveUser(UserController.getInstance().getUserData("AveAdmin", "ave123"));
//		UserController.getInstance().navigateToRegister(arg0);
	}
}
