package view;

import controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterPage {
	
	public class RegistComp{
		Scene registerScene;
		BorderPane mainPane = new BorderPane();
		VBox sceneBox = new VBox();
		VBox registerContainer = new VBox(10);
		
		Label registPageTitle = new Label("Welcome to Internet CFLabs");
		Label usernameLabel = new Label("Username");
		Label passwordLabel = new Label("Password");
		Label cfPasswordLabel = new Label("Confirm Password");
		Label ageLabel = new Label("Age");
		public Label errorMessage = new Label();
		
		public TextField usernameField = new TextField();
		public PasswordField passwordField = new PasswordField();
		public PasswordField cfPasswordField = new PasswordField();
		public Spinner<Integer> ageSpinner = new Spinner<>(0, 65, 13);
		
		public Button registButton = new Button("Register");
		
	    Hyperlink loginLink = new Hyperlink("Already have an account? Login here");
	}
	
	public Scene Initialization(RegistComp comp){
		comp.registerContainer.getChildren().addAll(comp.registPageTitle, comp.usernameLabel,comp.usernameField, comp.passwordLabel, comp.passwordField, comp.cfPasswordLabel, 
				comp.cfPasswordField, comp.ageLabel, comp.ageSpinner, comp.registButton, comp.loginLink, comp.errorMessage);
		comp.sceneBox.getChildren().addAll(comp.registerContainer);

		comp.mainPane.setCenter(comp.sceneBox);
		comp.registerScene = new Scene(comp.mainPane, 800, 500);
		return comp.registerScene;
	}
	
	public void setStyle(RegistComp comp) {
		comp.errorMessage.setStyle("-fx-text-fill: RED;");
		comp.registPageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		comp.registerContainer.setMaxWidth(500);
		comp.registerContainer.setAlignment(Pos.CENTER);
		comp.mainPane.setCenter(comp.registerContainer);
	}
	
	public void setActions(RegistComp obj, Stage stage) {
		UserController userController = UserController.getInstance();

		obj.registButton.setOnMouseClicked(e -> {
			if(userController.validateRegister(obj)) {
				userController.addNewUser(obj.usernameField.getText(), obj.passwordField.getText(), obj.ageSpinner.getValue());			
				userController.navigateToLogin(stage);				
			}			
		});
		
		obj.loginLink.setOnAction(e -> {
        	userController.navigateToLogin(stage);
        });
	}

	public RegisterPage(Stage stage) {
		RegistComp obj = new RegistComp();
		Initialization(obj);
		setStyle(obj);
		setActions(obj, stage);
		
		stage.setScene(obj.registerScene);
		stage.setTitle("Register Page");
		stage.setResizable(false);
		stage.show();
	}
}
