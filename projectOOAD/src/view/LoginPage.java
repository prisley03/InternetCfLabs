package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LoginPage {
	Scene LoginScene;
	BorderPane mainPane = new BorderPane();
	VBox sceneBox = new VBox();
	
	Label usernameLbl  = new Label("Username");
	TextField usernamField = new TextField();
	Label passwordLbl = new Label("Password");
	PasswordField passwordField = new PasswordField();
	
	Button loginBtn = new Button("Login");
	
	
	public Scene Initialization(){
		
		return LoginScene;
	}
}

	
