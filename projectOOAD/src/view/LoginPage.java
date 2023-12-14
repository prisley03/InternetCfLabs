package view;

import controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.User;

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

		public Button loginButton = new Button("Login");
		
	    Hyperlink regisLink = new Hyperlink("Don't have an account yet? Register here");
	}
	
	public Scene initialize(LoginComp comp){
		comp.loginContainer.getChildren().addAll(comp.loginPageTitle, 
				comp.usernameLbl, comp.usernameField,
				comp.passwordLbl, comp.passwordField,
				comp.loginButton, comp.regisLink, comp.errorMessage);
		
		comp.sceneBox.getChildren().addAll(comp.loginContainer);
		comp.mainPane.setCenter(comp.sceneBox);
		comp.LoginScene = new Scene(comp.mainPane, 500, 600);
		
		return comp.LoginScene;
	}
	
	public void setStyle(LoginComp comp) {
		comp.errorMessage.setStyle("-fx-text-fill: RED;");
		comp.loginPageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		comp.loginContainer.setMaxWidth(350);
		comp.loginContainer.setAlignment(Pos.CENTER);
		comp.mainPane.setCenter(comp.loginContainer);
	}
	
	public void setActions(LoginComp obj, Stage stage) {
		UserController userController = UserController.getInstance();

		obj.loginButton.setOnMouseClicked(e -> {
			if(userController.validateLogin(obj)) {
				User activeUser = userController.getUserData(obj.usernameField.getText(), obj.passwordField.getText());			
				User.setActiveUser(activeUser);
				new ViewAllPC(stage);			
			}			
		});
		
		obj.regisLink.setOnAction(e -> {
        	userController.navigateToRegister(stage);
        });
	}
	
	public LoginPage(Stage stage) {
		LoginComp obj = new LoginComp();
		initialize(obj);
		setStyle(obj);
		setActions(obj, stage);
			
		stage.setScene(obj.LoginScene);
		stage.setTitle("Login Page");
		stage.setResizable(false);
		stage.show();
	}
}

	
