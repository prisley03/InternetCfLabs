package view;

import controller.UserController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.Job;
import model.object.PC;
import model.object.User;
import view.BookPCPage.BookPCObj;
import view.ViewAllStaff.ViewAllStaffObj;

public class ViewTechnicianJobPage {
	
	public class ViewTechnicianJobObj {
		private Scene ViewTechnicianScene;
		private BorderPane ViewTechnicianpane = new BorderPane();
		
		private VBox allStaffcontainer = new VBox();
		
		private TableView<User> ViewTechnicianTableView = new TableView<>();	
		private TableColumn<User, Integer> StaffIDColumn = new TableColumn<>("UserID");
		private TableColumn<User, String> StaffUserNameColumn = new TableColumn<>("UserName");
		
		private TableView<Job> JobTableView = new TableView<>();	
		private TableColumn<Job, Integer> JobIDColumn = new TableColumn<>("Job_ID");
		private TableColumn<Job, String> PCIDColumn = new TableColumn<>("PC_ID");
		private TableColumn<Job, String> JobStatusColumn = new TableColumn<>("JobStatus");
		
		
		private Label titleLbl = new Label("View All Technician Job");
		private Label instructionLbl = new Label("Double click to show Technician Job"); 

	}
	public Scene initialize(ViewTechnicianJobObj obj, Stage stage, String role){
		obj.StaffIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		obj.StaffUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		
	    obj.ViewTechnicianTableView.getColumns().addAll(obj.StaffIDColumn, obj.StaffUserNameColumn);
		
	    
	    obj.allStaffcontainer.getChildren().addAll(obj.titleLbl, obj.instructionLbl);						
	    obj.ViewTechnicianpane.setTop(new HeaderMenu().getMenuHeader(stage, role)); //different roles will have different menus

	    obj.ViewTechnicianpane.setCenter(obj.allStaffcontainer);
	    obj.ViewTechnicianpane.setBottom( obj.ViewTechnicianTableView);
		obj.ViewTechnicianScene = new Scene(obj.ViewTechnicianpane, 800, 500);

		return obj.ViewTechnicianScene;
	}
	
	public void bindData(ViewTechnicianJobObj obj) {
		
		//Get all data from the database via controller to display
        ObservableList<User> staffList = FXCollections.observableArrayList(UserController.getInstance().getAllStaff());
		obj.ViewTechnicianTableView.setItems(staffList);
	}
	
	public void setStyle(ViewTechnicianJobObj obj) {

		
		obj.titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		obj.allStaffcontainer.setAlignment(Pos.CENTER);
	}
	
	
	public void setAction(Stage stage, ViewTechnicianJobObj obj, String role) {
		// Double click with primary button with mouse and page will navigate to ChangeUserRole Page
		obj.ViewTechnicianTableView.setOnMouseClicked((e) -> {
			if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2){
				User selectedUser = obj.ViewTechnicianTableView.getSelectionModel().getSelectedItem();
//				new ChangeUserRolePage(stage, selectedUser);
			}
		});
		
	
}
	
	public ViewTechnicianJobPage(Stage stage) {
		User user = User.getActiveUser();
		ViewTechnicianJobObj obj = new ViewTechnicianJobObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		setAction(stage, obj,  user.getUserRole());
		
		stage.setScene(obj.ViewTechnicianScene);
		stage.setTitle("Book PC");
		stage.setResizable(false);
		stage.show();
		
	}
	
}
