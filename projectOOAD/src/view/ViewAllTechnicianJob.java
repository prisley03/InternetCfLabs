
package view;

import java.util.ArrayList;

import controller.JobController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.database.JobDatabase;
import model.object.Job;
import model.object.User;

public class ViewAllTechnicianJob {
	
	JobController jobController = JobController.getInstance();
//	pembuatan variabel menu tampilan untuk Technician Job
	public class ViewAllTechnicianJobObj{
		Scene allTechnicianJobScene;
		BorderPane bpTechnician = new BorderPane();
		ScrollPane scrollpane = new ScrollPane();
		VBox tableBox = new VBox();
	    Label tableLabel = new Label("List of Technician Job");
	    
	    TableView<Job> allJobTableView = new TableView<>();
		TableColumn<Job, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		TableColumn<Job, String> jobstatusColumn = new TableColumn<>("Job_Status");
		
		VBox jobBox = new VBox();
	    VBox pcBox = new VBox();
	    Label pcLabel = new Label("Please Choose The Job to Update Status");
	    ComboBox<String> pcIDInput = new ComboBox<String>();
	    private ComboBox<String> jobStatusCombo= new ComboBox<String>();
	    
	    Button submitButton = new Button("Update");
	    private Button insertBtn = new Button("Add Job");
	    Label message = new Label("");
	}
	
//Pemasangan tampilan menu
	@SuppressWarnings("unchecked")
	public Scene initialize(ViewAllTechnicianJobObj comp, Stage stage, String role){
		comp.jobBox.getChildren().addAll(
		        comp.tableBox,
		        comp.pcBox, comp.jobStatusCombo,
		        comp.submitButton,
		        comp.insertBtn,
		        comp.message
		      );		
		comp.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		comp.jobstatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
	    comp.allJobTableView
	      .getColumns()
	      .setAll(comp.pcIDColumn, comp.jobstatusColumn);
	    comp.tableBox.getChildren().addAll(comp.tableLabel, comp.allJobTableView);

	    comp.pcBox.getChildren().addAll(comp.pcLabel, comp.pcIDInput);
	    
	    comp.scrollpane.setContent(comp.jobBox);
	    comp.bpTechnician.setTop(new HeaderMenu().getMenuHeader(stage, role));

	    comp.tableBox.setSpacing(10);
	    comp.jobBox.setSpacing(20);
	    comp.pcBox.setSpacing(10);

	    comp.bpTechnician.setCenter(comp.scrollpane);
	    comp.allTechnicianJobScene = new Scene(comp.bpTechnician, 800, 600);
	    return comp.allTechnicianJobScene;
	}

//	Mengebind data
	public void bindData(Stage stage, ViewAllTechnicianJobObj comp, User sT) {
		comp.pcIDInput.getItems().clear();
		ObservableList<Job> jobList = FXCollections.observableArrayList(JobController.getInstance().getAllJobDataByTechID(sT.getUserId()));
		ObservableList<Job> uncompleteJobList = FXCollections.observableArrayList(JobController.getInstance().getAllJobDataByTechID(sT.getUserId()));
		comp.allJobTableView.setItems(jobList);
		comp.pcIDInput.getItems().add("Select PC");
		
	    for (Job job : uncompleteJobList) {
	    	int pcId = job.getPcId();
	    	comp.pcIDInput.getItems().add(String.format("PC %d", pcId));
	    	
	    }
	    comp.pcIDInput.getSelectionModel().selectFirst();
	    
	    ArrayList<String> jobStatusList = new ArrayList<>();
		jobStatusList.add("Complete");
		jobStatusList.add("UnComplete");
		
		comp.jobStatusCombo.setItems(FXCollections.observableArrayList(jobStatusList));
		comp.jobStatusCombo.getSelectionModel().selectFirst();
	    

	    
	}

//	Action untuk submit button mengubah value pada status 'uncomplete' menjadi 'complete'
	public void setAction(Stage stage, ViewAllTechnicianJobObj comp, User selectedTech) {
		comp.submitButton.setOnAction(e->{
			String selectedUncompletePC = comp.pcIDInput.getValue();
			if(selectedUncompletePC != "Select PC") {
				
				String[] pcId = selectedUncompletePC.split(" ");
				String jobStatus = comp.jobStatusCombo.getSelectionModel().getSelectedItem();
				
				int pcID = Integer.parseInt(pcId[1]);
				jobController.updateStatusJob(pcID, jobStatus);
				bindData(stage, comp, selectedTech);
				comp.message.setText("Succesfully update status :" + selectedUncompletePC);
				}
			else {
				comp.message.setText("Please choose PC");
			}
		});
		comp.insertBtn.setOnMouseClicked(e ->{
			new AddJobPage(stage, selectedTech);
		});
	}
	
	public void setStyle(ViewAllTechnicianJobObj obj) {
		obj.bpTechnician.setCenter(obj.jobBox);
	    obj.jobBox.setAlignment(Pos.CENTER);
		obj.tableBox.setAlignment(Pos.CENTER);
		obj.pcBox.setAlignment(Pos.CENTER);
		
//		Menstyling tulisan pada title
		obj.tableLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		
		obj.allJobTableView.setMaxWidth(250);
	}
	
//Menampilkan stage
	public ViewAllTechnicianJob(Stage stage, User selectedTech) {
		// TODO Auto-generated constructor stub
		User user = User.getActiveUser();
		ViewAllTechnicianJobObj comp = new ViewAllTechnicianJobObj();
		initialize(comp, stage, user.getUserRole());
		bindData(stage, comp, selectedTech);
		setStyle(comp);
		setAction(stage, comp, selectedTech);
		stage.setScene(comp.allTechnicianJobScene);
		stage.setTitle("View All Technician Job");
		stage.setResizable(false);
		stage.show();
	}
}
