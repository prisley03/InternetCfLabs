package view;

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

public class ViewTechnicianJobPage {
	
	JobController jobController = JobController.getInstance();
//	pembuatan variabel menu tampilan untuk Technician Job
	public class TechnicianJobComp{
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

	    Button submitButton = new Button("Submit");
	    Label message = new Label("");
	}
	
//Pemasangan tampilan menu
	public Scene initialize(TechnicianJobComp comp, Stage stage, String role){
		comp.jobBox.getChildren().addAll(
		        comp.tableBox,
		        comp.pcBox,
		        comp.submitButton,
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

	    comp.jobBox.setSpacing(20);
	    comp.tableBox.setSpacing(10);
	    comp.pcBox.setSpacing(10);

	    comp.bpTechnician.setCenter(comp.scrollpane);
	    comp.allTechnicianJobScene = new Scene(comp.bpTechnician, 800, 600);
	    return comp.allTechnicianJobScene;
	}

//	Mengebind data
	public void bindData(TechnicianJobComp comp) {
		comp.pcIDInput.getItems().clear();
		ObservableList<Job> jobList = FXCollections.observableArrayList(JobController.getInstance().getJobforTechinician());
		ObservableList<Job> uncompleteJobList = FXCollections.observableArrayList(JobController.getInstance().getJobUncompleteData());
		comp.allJobTableView.setItems(jobList);
		comp.pcIDInput.getItems().add("Select PC");
		
	    for (Job job : uncompleteJobList) {
	    	int pcId = job.getPcId();
	    	comp.pcIDInput.getItems().add(String.format("PC %d", pcId));
	    	
	    }
	    comp.pcIDInput.getSelectionModel().selectFirst();
	}

//	Action untuk submit button mengubah value pada status 'uncomplete' menjadi 'complete'
	public void setAction(TechnicianJobComp comp) {
		
		comp.submitButton.setOnAction(e->{
			String selectedUncompletePC = comp.pcIDInput.getValue();
			if(!"Select PC".equals(selectedUncompletePC)) {
				int userID = User.getActiveUser().getUserId();
				String[] pcId = selectedUncompletePC.split(" ");
				int pcID = Integer.parseInt(pcId[1]);
				jobController.markComplete(pcID, userID);
				bindData(comp);
				comp.message.setText("Succesfully update status :" + selectedUncompletePC);
				}
			else {
				comp.message.setText("Please choose PC");
			}
		});
	}

// menset styling pada tampilan
	public void setStyle(TechnicianJobComp comp) {
	    comp.bpTechnician.setCenter(comp.jobBox);
	    comp.jobBox.setAlignment(Pos.CENTER);
		comp.tableBox.setAlignment(Pos.CENTER);
		comp.pcBox.setAlignment(Pos.CENTER);
		
//		Menstyling tulisan pada title
		comp.tableLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		
		comp.allJobTableView.setMaxWidth(250);
	}

//Menampilkan stage
	public ViewTechnicianJobPage(Stage stage) {
		// TODO Auto-generated constructor stub
		User user = User.getActiveUser();
		TechnicianJobComp comp = new TechnicianJobComp();
		initialize(comp, stage, user.getUserRole());
		bindData(comp);
		setAction(comp);
		setStyle(comp);
		stage.setScene(comp.allTechnicianJobScene);
		stage.setTitle("View All Job");
		stage.setResizable(false);
		stage.show();
	}
}
