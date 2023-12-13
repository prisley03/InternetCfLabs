package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.RegisterPage.RegistComp;

public class LoginPage {
	
	public class LoginComp{
		Scene LoginScene;
		BorderPane mainPane = new BorderPane();
		VBox sceneBox = new VBox();
		VBox loginContainer = new VBox(10);

		Label loginPageTitle = new Label("Login");
		public Label usernameLbl  = new Label("Username");
		public TextField usernameField = new TextField();
		public Label passwordLbl = new Label("Password");
		public PasswordField passwordField = new PasswordField();
		public Label errorMessage = new Label();

		public Button loginBtn = new Button("Login");
		
	    Hyperlink regisLink = new Hyperlink("Don't have an account yet? Register here");

	}
	
	public Scene Initialization(LoginComp comp){
		comp.loginContainer.getChildren().addAll(comp.loginPageTitle, 
				comp.usernameLbl, comp.usernameField,
				comp.passwordLbl, comp.passwordField,
				comp.loginBtn, comp.regisLink, comp.errorMessage);
		comp.sceneBox.getChildren().addAll(comp.loginContainer);
		comp.mainPane.setCenter(comp.sceneBox);
		comp.LoginScene = new Scene(comp.mainPane, 800, 600);
		return comp.LoginScene;
	}
	
	public void setStyle(LoginComp comp) {
		comp.errorMessage.setStyle("-fx-text-fill: RED;");
	}
	
	public LoginPage(Stage stage) {
		UserController userController = UserController.getInstance();
		LoginComp obj = new LoginComp();
		Initialization(obj);
		setStyle(obj);
		
		obj.regisLink.setOnAction(e -> {
        	userController.navigateToRegister(stage);
        });
			
		stage.setScene(obj.LoginScene);
		stage.setTitle("Login Page");
		stage.setResizable(false);
		stage.show();
	}


	
	
	
}

	
