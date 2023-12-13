package view;

import header.HeaderMenu;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.object.User;

public class MainPage {

	public class MainPageVar {
		private BorderPane homePane = new BorderPane();
		
		private User activeUser = User.getActiveUser();
		private Label homeLabel = new Label(String.format("Welcome, %s", activeUser.getUsername()));
		private Scene mainPageScene;
	}
	
	public Scene initialize(MainPageVar obj, Stage stage) {
		obj.homePane.setTop(new HeaderMenu().getMenuHeader(stage));
		obj.homePane.setCenter(obj.homeLabel);
		obj.mainPageScene = new Scene(obj.homePane, 800, 500);
		
		return obj.mainPageScene;
	}
	
	public MainPage(Stage stage) {
		MainPageVar obj = new MainPageVar();
		initialize(obj, stage);
		stage.setScene(obj.mainPageScene);
		Rectangle2D screenBound = Screen.getPrimary().getBounds();
		stage.setX((screenBound.getWidth() - stage.getWidth())/2);
		stage.setY((screenBound.getHeight() - stage.getHeight())/2);
	}
}
