package view;


import java.util.ArrayList;

import controller.JobController;
import controller.PCController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.object.PC;
import model.object.User;
import view.PCDetailPage.PCDetailobj;

public class AddJobPage {
	PCController pcController = PCController.getInstance();
	JobController jobController = JobController.getInstance();
	
	public class AddJobObj{
		private Scene AddJobScene;
		private BorderPane AddJobPane = new BorderPane();
		private GridPane formPane = new GridPane();
		private VBox centerContainer = new VBox();
		

		
		private Label pcIDlbl = new Label("PC ID : ");
		private Label jobStatuslbl = new Label("Job Status : ");
		
		private ComboBox<Integer> pcIDCombo= new ComboBox<Integer>();
		private ComboBox<String> jobStatusCombo= new ComboBox<String>();
		
		
		private Button addJobBtn = new Button("Add Job");
		public Label errorMessage = new Label("");
		private Button backBtn = new Button("< Back");
	}
	
	public Scene initialize(AddJobObj obj, Stage stage, String role){
		
		obj.formPane.add(obj.pcIDlbl, 0, 0);
		obj.formPane.add(obj.pcIDCombo, 1, 0);
		obj.formPane.add(obj.jobStatuslbl, 0, 1);
		obj.formPane.add(obj.jobStatusCombo, 1, 1);
		
		obj.centerContainer.getChildren().addAll(obj.formPane, obj.addJobBtn, obj.errorMessage, obj.backBtn);
		
		obj.AddJobPane.setTop(new HeaderMenu().getMenuHeader(stage, role)); //different roles will have different menus
		obj.AddJobPane.setCenter(obj.centerContainer);
		
		obj.AddJobScene = new Scene(obj.AddJobPane, 800, 500);
		return obj.AddJobScene;
	}
	
	public void bindData(AddJobObj obj) {
		ArrayList<String> jobStatusList = new ArrayList<>();
		jobStatusList.add("Complete");
		jobStatusList.add("UnComplete");
		
		obj.jobStatusCombo.setItems(FXCollections.observableArrayList(jobStatusList));
		obj.jobStatusCombo.getSelectionModel().selectFirst();
		
//		//Retrieve all PC exist
//		ArrayList<PC> allPCList = new ArrayList<>();
//		allPCList = pcController.getAllPCData();
//		
//		//Prepare array list fo rstoring all PC ID
//		ArrayList<Integer> allPCIDList = new ArrayList<>();
//		
//		//Storing all PCID that exist
//		for( PC pc : allPCList) {
//			allPCIDList.add(pc.getPcId());
//		}
		
		//Inset into combobox
		obj.pcIDCombo.setItems(FXCollections.observableArrayList(jobController.gettAllPossibleMaintenancePCID()));	
		obj.pcIDCombo.getSelectionModel().selectFirst();
		
		
		
	}
	
	public void setStyle(AddJobObj obj) {
		obj.centerContainer.setAlignment(Pos.CENTER);
		obj.formPane.setAlignment(Pos.CENTER);
		obj.formPane.setHgap(10);
		obj.formPane.setVgap(5);
		
		obj.formPane.setPadding(new Insets(10));
		
		obj.errorMessage.setPadding(new Insets(10));
		
		obj.backBtn.setLineSpacing(10);
		obj.backBtn.setMinWidth(270);
		

		obj.errorMessage.setStyle("-fx-text-fill: RED;");			
		obj.errorMessage.setAlignment(Pos.CENTER);
	}
	
	public void setActions(Stage stage, AddJobObj obj, User sT) {
		obj.backBtn.setOnMouseClicked(e ->{
			new ViewAllTechnicianJob(stage, sT);
		});
		
		obj.addJobBtn.setOnMouseClicked(e -> {
			// dont need to validate the field cause the data is already retrieve ffrom database (combobox)
			// the field also ensured already field by selecFirst()
			int pcID = obj.pcIDCombo.getSelectionModel().getSelectedItem();
			String jobStatus = obj.jobStatusCombo.getSelectionModel().getSelectedItem();
		
			if(jobController.addJob(pcID, jobStatus, sT.getUserId())){
				new ViewAllTechnicianJob(stage, sT);
			}
		});
	}
	
	public AddJobPage(Stage stage, User selectedTech) {
		User user = User.getActiveUser();
		AddJobObj obj = new AddJobObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		setActions(stage, obj, selectedTech);
		
		stage.setScene(obj.AddJobScene);
		stage.setTitle("Insert PC");
		stage.setResizable(false);
		stage.show();
	}

}
