package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage {
	
	public class RegistComp{
		Scene registerScene;
		BorderPane mainPane = new BorderPane();
		VBox sceneBox = new VBox();
		VBox registerContainer = new VBox(10);
		HBox genderContainer = new HBox(10);
		
		Label registPageTitle = new Label("Welcome to Internet CFLabs");
		Label usernameLabel = new Label("Username");
		Label passwordLabel = new Label("Password");
		Label cfPasswordLabel = new Label("Confirm Password");
		Label ageLabel = new Label("Age");
		
		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();
		PasswordField cfPasswordField = new PasswordField();
		Spinner<Integer> ageSpinner = new Spinner<>(0, 120, 20);
		
		Button registButton = new Button("Register");
		
		public RegistComp(){
			registButton.setOnMouseClicked(e->{
				switchLoginScene();
			});
		}
	}
	
	public Scene Initialization(RegistComp comp){
		comp.registerContainer.getChildren().addAll(comp.registPageTitle, comp.usernameLabel,comp.usernameField, comp.passwordLabel, comp.passwordField, comp.cfPasswordLabel, 
				comp.cfPasswordField, comp.ageLabel, comp.ageSpinner, comp.registButton);
		comp.sceneBox.getChildren().addAll(comp.registerContainer);
		comp.mainPane.setCenter(comp.sceneBox);
		comp.registerScene = new Scene(comp.mainPane, 800, 600);
		return comp.registerScene;
	}

	public RegisterPage(Stage stage) {
		
		RegistComp obj = new RegistComp();
		Initialization(obj);
		stage.setScene(obj.registerScene);
		stage.setTitle("Register Page");
		stage.setResizable(false);
		stage.show();
	}
	
	public void switchLoginScene(){
		LoginPage loginPage = new LoginPage();
	}
}
