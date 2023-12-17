package view;

import controller.UserController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.User;

public class ViewAllStaff {
	
	public class ViewAllStaffObj {
		private Scene allStaffScene;
		private BorderPane allStaffpane = new BorderPane();
		
		private VBox allStaffcontainer = new VBox();
		
		private TableView<User> allStaffTableView = new TableView<>();	
		private TableColumn<User, Integer> StaffIDColumn = new TableColumn<>("UserID");
		private TableColumn<User, String> StaffUserNameColumn = new TableColumn<>("UserName");
		private TableColumn<User, String> StafPasswordColumn = new TableColumn<>("UserPassword");
		private TableColumn<User, String> StaffRoleColumn = new TableColumn<>("UserRole");
		private TableColumn<User, String> StaffAgeColumn = new TableColumn<>("UserAge");
		
		
		private Label titleLbl = new Label("View All Staff");
		private Label instructionLbl = new Label(""); 
	}
	
	public Scene initialize(ViewAllStaffObj obj, Stage stage, String role, String purpose){
		obj.StaffIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		obj.StaffUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		obj.StafPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		obj.StaffRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		obj.StaffAgeColumn.setCellValueFactory(new PropertyValueFactory<>("userAge"));
		
	    obj.allStaffTableView.getColumns().addAll(obj.StaffIDColumn, obj.StaffUserNameColumn, obj.StafPasswordColumn, obj.StaffRoleColumn, obj.StaffAgeColumn);
		
	    
	    obj.allStaffcontainer.getChildren().addAll(obj.titleLbl, obj.instructionLbl);						
	    obj.allStaffpane.setTop(new HeaderMenu().getMenuHeader(stage, role)); //different roles will have different menus

	    obj.allStaffpane.setCenter(obj.allStaffcontainer);
	    obj.allStaffpane.setBottom( obj.allStaffTableView);
		obj.allStaffScene = new Scene(obj.allStaffpane, 800, 500);
		
		// Change staff roles and update technician job that showing the same layout is merge with if else for eficciency
		if(purpose.equals("ChangeRole")) {
			obj.instructionLbl.setText("Double click to update Staff Role");
		}else if(purpose.equals("UpdateJob")) {
			obj.instructionLbl.setText("Double click to see Technician Job");
		}
		return obj.allStaffScene;
	}
	
	public void bindData(ViewAllStaffObj obj, String purpose) {
		
		//Get all data from the database via controller to display
		
		if(purpose.equals("ChangeRole")) {
			ObservableList<User> staffList = FXCollections.observableArrayList(UserController.getInstance().getAllStaff());
			obj.allStaffTableView.setItems(staffList);
		}else if(purpose.equals("UpdateJob")) {
			ObservableList<User> technicianList = FXCollections.observableArrayList(UserController.getInstance().getAllTechnician());
			obj.allStaffTableView.setItems(technicianList);
		}
		
	}
	
	public void setStyle(ViewAllStaffObj obj) {
		obj.allStaffcontainer.setSpacing(10);
		
		obj.titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		obj.allStaffcontainer.setAlignment(Pos.CENTER);
	}
	
	public void setAction(Stage stage, ViewAllStaffObj obj, String role, String purpose) {
			// Double click with primary button with mouse and page will navigate to ChangeUserRole Page
			obj.allStaffTableView.setOnMouseClicked((e) -> {
				if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2){
					User selectedUser = obj.allStaffTableView.getSelectionModel().getSelectedItem();
					if(purpose.equals("ChangeRole")) {
						new ChangeUserRolePage(stage, selectedUser);
					}else if(purpose.equals("UpdateJob")) {
						new ViewAllTechnicianJob(stage, selectedUser);
					}
				}
			});
			
		
    }
	
	// kalau yang diganti itu user yang lagi aktif, maka direct ke logout
	public ViewAllStaff(Stage stage, String purpose) {
		System.out.println(purpose);
		User user = User.getActiveUser();
		ViewAllStaffObj obj = new ViewAllStaffObj();
	
		initialize(obj, stage, user.getUserRole(), purpose);
		bindData(obj, purpose);
		setStyle(obj);
		setAction(stage, obj, user.getUserRole(), purpose);
		
		stage.setScene(obj.allStaffScene);
		stage.setTitle("View All Staff");
		stage.setResizable(false);
		stage.show();
	}
}
