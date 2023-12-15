package view;

import header.HeaderMenu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.User;

public class MainPage {

	public class MainPageObj {
		private BorderPane homePane = new BorderPane();
		
		private User activeUser = User.getActiveUser();
		private Label homeLabel = new Label(String.format("Welcome, %s", activeUser.getUsername()));
		private Label userRole = new Label(String.format("Role : %s", activeUser.getUserRole()));
		private Label messageLabel = new Label("Please choose the menu options you'd like to access from the menu bar.");
		private Scene mainPageScene;
		private VBox messageContainer = new VBox(10);
	}
	
	public Scene initialize(MainPageObj obj, Stage stage) {
		obj.homePane.setTop(new HeaderMenu().getMenuHeader(stage));
		obj.messageContainer.getChildren().addAll(obj.homeLabel, obj.userRole, obj.messageLabel);
		obj.homePane.setCenter(obj.messageContainer);
		obj.mainPageScene = new Scene(obj.homePane, 800, 500);
		
		return obj.mainPageScene;
	}
	
	public void setStyle(MainPageObj obj) {
		obj.homeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		obj.userRole.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
		obj.messageContainer.setAlignment(Pos.CENTER);
		obj.homePane.setCenter(obj.messageContainer);
	}
	
	public MainPage(Stage stage) {
		MainPageObj obj = new MainPageObj();
		initialize(obj, stage);
		setStyle(obj);
	
		stage.setScene(obj.mainPageScene);
		stage.show(); // jangan lupa delete, cuma untuk direct acces ke mainpage pas run
	}
}
