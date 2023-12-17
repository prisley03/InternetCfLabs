package view;

import java.util.ArrayList;

import controller.UserController;
import header.HeaderMenu;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.object.PC;
import model.object.User;
import view.PCDetailPage.PCDetailobj;

public class ChangeUserRolePage {
	private UserController userController = UserController.getInstance();
	
	
	public class ChangeRoleObj{
		private Scene ChangeRoleScene;
		private BorderPane ChangeRolePane = new BorderPane();
		private GridPane formPane = new GridPane();
		private VBox centerContainer = new VBox();
		private VBox BackBtnContainer = new VBox();
		private HBox buttonContainer = new HBox();
		private Label userIDlbl = new Label("");
		private Label userNamelbl = new Label("");
		private Label userPasswordlbl = new Label("");
		private Label userRolelbl = new Label("Role : ");
		private Label userAgelbl = new Label("");
		private ComboBox<String> userRoleCombo= new ComboBox<String>();
		
		private Button updateBtn = new Button("Update");
		private Button backBtn = new Button("< Back");
		
	}
	
	public Scene initialize(ChangeRoleObj obj, Stage stage, String role) {
		obj.buttonContainer.getChildren().addAll(obj.updateBtn);
		obj.BackBtnContainer.getChildren().add(obj.backBtn);
		obj.centerContainer.getChildren().addAll(obj.formPane, obj.buttonContainer, obj.BackBtnContainer);
		
		obj.ChangeRolePane.setTop(new HeaderMenu().getMenuHeader(stage, role));
		obj.ChangeRolePane.setCenter(obj.centerContainer);
		
		
		
		obj.formPane.add(obj.userIDlbl, 0, 0);
		obj.formPane.add(obj.userNamelbl, 0, 1);
		obj.formPane.add(obj.userPasswordlbl, 0, 3);
		obj.formPane.add(obj.userRolelbl, 0, 4);
		obj.formPane.add(obj.userAgelbl, 0, 5);
		obj.formPane.add(obj.userRoleCombo, 2, 4);
		obj.ChangeRoleScene = new Scene(obj.ChangeRolePane, 800, 500);
		
		return obj.ChangeRoleScene;
	}
	
	public void setStyle(ChangeRoleObj obj) {
		obj.centerContainer.setAlignment(Pos.CENTER);
		obj.formPane.setAlignment(Pos.CENTER);
		obj.formPane.setHgap(10);
		obj.formPane.setVgap(5);
		
		obj.buttonContainer.setAlignment(Pos.CENTER);
		obj.buttonContainer.setPadding(new Insets(10));
		obj.buttonContainer.setSpacing(10);
		
		obj.backBtn.setMinWidth(270);
		
		obj.BackBtnContainer.setPadding(new Insets(50));
		obj.BackBtnContainer.setAlignment(Pos.TOP_CENTER);
		
		
	}
	
	public void bindData(ChangeRoleObj obj, User sUser) {
		obj.userIDlbl.setText(String.format("User ID : %d", sUser.getUserId()));
		obj.userNamelbl.setText(String.format("Username : %s", sUser.getUsername()));
		obj.userPasswordlbl.setText(String.format("Password : %s", sUser.getPassword()));
		obj.userAgelbl.setText(String.format("Username : %d", sUser.getUserAge()));
		
		ArrayList<String> userRoleList = new ArrayList<>();
		userRoleList.add("Customer");
		userRoleList.add("Operator");
		userRoleList.add("Technician");
		userRoleList.add("Admin");
		
		obj.userRoleCombo.setItems(FXCollections.observableArrayList(userRoleList));
		
		for(String role : userRoleList) {
			if(role.equals(sUser.getUserRole())) {
				obj.userRoleCombo.getSelectionModel().select(role);
			}
		}

	}
	
	public void setActions(Stage stage, ChangeRoleObj obj, User sUser) {
		
		 obj.updateBtn.setOnMouseClicked(e -> {
			 // runLater function prevents UI thread from violating Quantum Toolkit : RunWithourRenderException
			Platform.runLater(() -> {
			int userID = sUser.getUserId();
			String role = obj.userRoleCombo.getSelectionModel().getSelectedItem();
			if(userController.deleteUser(sUser.getUserId())&&
			userController.addNewUser(sUser.getUsername(), sUser.getPassword(), role, sUser.getUserAge()) 
			) {
				// if the changed user's role is the active user, then it wil navigate the active user to login page
				// It was done to ensure that user can access the menu bar based on the new role
				if(userID == User.getActiveUser().getUserId()) {
					User.setActiveUser(null);
					UserController.getInstance().navigateToLogin(stage);
				}else {
					new ViewAllStaff(stage, "ChangeRole");					
				}
			}
			});
		});
		
		obj.backBtn.setOnMouseClicked(e ->{
			new ViewAllStaff(stage, "ChangeRole");
		});
	}
	
	public ChangeUserRolePage(Stage stage, User selectedUser) {
		User user = User.getActiveUser();
		ChangeRoleObj obj = new ChangeRoleObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj, selectedUser);
		setStyle(obj);
		setActions(stage, obj, selectedUser);
		
		stage.setScene(obj.ChangeRoleScene);
		stage.setTitle("Detail User" + selectedUser.getUserId());
		stage.setResizable(false);
		stage.show();
		
	}

}
