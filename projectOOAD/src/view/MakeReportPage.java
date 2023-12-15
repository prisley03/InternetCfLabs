package view;

import java.time.LocalDate;

import controller.PCBookController;
import controller.PCController;
import controller.ReportController;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.PC;
import view.BookPCPage.BookPCObj;

public class MakeReportPage {
	public class MakeReportPageObj {
		private Scene makeReportScene;
		private BorderPane outerContainer = new BorderPane();
		private SplitPane splitPane = new SplitPane();
		private TableView<PC> pcTableView = new TableView<>();	
		private TableColumn<PC, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		private TableColumn<PC, String> pcConditionColumn = new TableColumn<>("PC_Condition");
		private VBox reportPCForm = new VBox(10);
				
		private Label reportPCFormLabel = new Label("Report PC");
		private Label choosePCLabel = new Label("Select PC to Report");
		private ComboBox<String> pcComboBox = new ComboBox<>();
		
		private Label insertReportNoteLabel = new Label("Insert Report Note");
		public TextField reportField = new TextField();
		private Button reportButton = new Button("Report Now");
		public Label errorMessage = new Label();

	}
	
	public Scene initialize(MakeReportPageObj obj, Stage stage){
		obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		obj.pcConditionColumn.setCellValueFactory(new PropertyValueFactory<>("pcCondition"));
	    obj.pcTableView.getColumns().addAll(obj.pcIDColumn, obj.pcConditionColumn);
	    
	    obj.reportPCForm.getChildren().addAll(
	    		obj.reportPCFormLabel, obj.choosePCLabel, 
	    		obj.pcComboBox, obj.insertReportNoteLabel, 
	    		obj.reportField, obj.reportButton, obj.errorMessage);
	    
	   	obj.outerContainer.setTop(new HeaderMenu().getMenuHeader(stage));
	    obj.splitPane.setDividerPositions(0.18);
	    obj.splitPane.getItems().addAll(obj.pcTableView, obj.reportPCForm);
	    obj.outerContainer.setCenter(obj.splitPane);
        
        obj.makeReportScene = new Scene(obj.outerContainer, 800, 500);
		return obj.makeReportScene;
	}

	public void bindData(MakeReportPageObj obj) {
        ObservableList<PC> pcList = FXCollections.observableArrayList(
        		PCController.getInstance().getAllPCData());
		obj.pcTableView.setItems(pcList);
		
		obj.pcComboBox.getItems().add("Select All");
		
		for(PC pc : pcList) {
			obj.pcComboBox.getItems().add(String.format("PC %d", pc.getPcId()));
		}
		
		obj.pcComboBox.getSelectionModel().selectFirst();
	}

	public void setStyle(MakeReportPageObj obj) {
		obj.reportPCFormLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		obj.reportPCForm.setAlignment(Pos.CENTER);
		obj.pcComboBox.setPrefWidth(200);
		obj.reportField.setPrefWidth(200);
		obj.errorMessage.setStyle("-fx-text-fill: RED;");
	}
	
	public void setActions(MakeReportPageObj obj) {
		obj.reportButton.setOnMouseClicked(e -> {
			if (obj.pcComboBox.getValue().equals("Select All")) {
				obj.errorMessage.setText("Please select a PC!");
			} else {
				obj.errorMessage.setText("");
				
				int id = Integer.parseInt(obj.pcComboBox.getValue().split(" ", 2)[1]);

				if(ReportController.getInstance().validateNewReport(obj) != null) {
					//PCBookController.getInstance().getPCBookedData(obj, id, obj.bookingDatePicker.getValue().toString());
				} else {
					obj.errorMessage.setText("PC does not exist");
				}
			}
		});
	}

	public MakeReportPage(Stage stage) {
		MakeReportPageObj obj = new MakeReportPageObj();
		initialize(obj, stage);
		bindData(obj);
		setStyle(obj);
		setActions(obj);
		
		stage.setScene(obj.makeReportScene);
		stage.setTitle("Make Report");
		stage.setResizable(false);
		stage.show();
	}
}
