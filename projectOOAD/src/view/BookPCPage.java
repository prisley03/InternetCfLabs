package view;

import java.time.LocalDate;

import controller.PCBookController;
import controller.PCController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.PC;
import model.object.User;
import view.ViewAllPC.ViewAllPCObj;

public class BookPCPage {
	public class BookPCObj {
		private Scene bookPCScene;
		private BorderPane outerContainer = new BorderPane();
		private SplitPane splitPane = new SplitPane();
		private TableView<PC> pcTableView = new TableView<>();	
		private TableColumn<PC, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		private TableColumn<PC, String> pcConditionColumn = new TableColumn<>("PC_Condition");
		private VBox bookPCForm = new VBox(10);
				
		private Label bookingFormLabel = new Label("Book PC");
		private Label bookingDateLabel = new Label("Select Booking Date");
		public DatePicker bookingDatePicker = new DatePicker();
		
		private Label pcBookLabel = new Label("Select PC to Book");
		public ComboBox<String> pcComboBox = new ComboBox<>();
		private Button bookButton = new Button("Book Now");
		public Label errorMessage = new Label();

	}
	
	public Scene initialize(BookPCObj obj, Stage stage, String role){
		obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		obj.pcConditionColumn.setCellValueFactory(new PropertyValueFactory<>("pcCondition"));
	    obj.pcTableView.getColumns().addAll(obj.pcIDColumn, obj.pcConditionColumn);
	    
	    obj.bookingDatePicker.setValue(LocalDate.now());
	    obj.bookPCForm.getChildren().addAll(
	    		obj.bookingFormLabel, obj.pcBookLabel, 
	    		obj.pcComboBox, obj.bookingDateLabel, 
	    		obj.bookingDatePicker, obj.bookButton, obj.errorMessage);
	    
	   	obj.outerContainer.setTop(new HeaderMenu().getMenuHeader(stage, role));
	    obj.splitPane.setDividerPositions(0.18);
	    obj.splitPane.getItems().addAll(obj.pcTableView, obj.bookPCForm);
	    obj.outerContainer.setCenter(obj.splitPane);
        
        obj.bookPCScene = new Scene(obj.outerContainer, 800, 500);
		return obj.bookPCScene;
	}

	public void bindData(BookPCObj obj) {
        ObservableList<PC> pcList = FXCollections.observableArrayList(
        		PCController.getInstance().getPCDetailByDateAndId(-1, LocalDate.now().toString()));
		obj.pcTableView.setItems(pcList);
		
		obj.pcComboBox.getItems().add("Select All");
		
		for(PC pc : pcList) {
			obj.pcComboBox.getItems().add(String.format("PC %d", pc.getPcId()));
		}
		
		obj.pcComboBox.getSelectionModel().selectFirst();
	}

	public void setStyle(BookPCObj obj) {
		obj.bookingFormLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		obj.bookPCForm.setAlignment(Pos.CENTER);
		obj.pcComboBox.setPrefWidth(200);
		obj.bookingDatePicker.setPrefWidth(200);
		obj.errorMessage.setStyle("-fx-text-fill: RED;");
	}
	
	public void setActions(BookPCObj obj) {
				
		obj.bookingDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
			
			int actualId = -1;
			
			if (!obj.pcComboBox.getValue().equals("Select All")) {
				actualId = Integer.parseInt(obj.pcComboBox.getValue().split(" ", 2)[1]);
			}
			
	        ObservableList<PC> pcList = FXCollections.observableArrayList(
	        		PCController.getInstance().getPCDetailByDateAndId(actualId, newValue.toString()));
	        
			obj.pcTableView.setItems(pcList);
			
			obj.pcComboBox.getItems().clear();
						
			obj.pcComboBox.getItems().add("Select All");
			for(PC pc : pcList) {
				obj.pcComboBox.getItems().add(String.format("PC %d", pc.getPcId()));
			}
			obj.pcComboBox.getSelectionModel().selectFirst();			
        });
		
		obj.pcComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				
				int actualId = -1;
	            if (!obj.pcComboBox.getValue().equals("Select All")) {
					actualId = Integer.parseInt(newValue.split(" ", 2)[1]);
				}

	            ObservableList<PC> pcList = FXCollections.observableArrayList(
	                    PCController.getInstance().getPCDetailByDateAndId(actualId, obj.bookingDatePicker.getValue().toString()));

	            obj.pcTableView.setItems(pcList);
	        }
        });
		
		obj.bookButton.setOnMouseClicked(e -> {
            if(PCBookController.getInstance().validatePCBook(obj)) {
                int id = Integer.parseInt(obj.pcComboBox.getValue().split(" ", 2)[1]);

                if(PCController.getInstance().getPCDetail(id) != null) {
                    PCBookController.getInstance().getPCBookedData(obj, id, obj.bookingDatePicker.getValue().toString());
                } else {
                    obj.errorMessage.setText("PC does not exist");
                }
            }
        });
	}

	public BookPCPage(Stage stage) {
		User user = User.getActiveUser();
		BookPCObj obj = new BookPCObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		setActions(obj);
		
		stage.setScene(obj.bookPCScene);
		stage.setTitle("Book PC");
		stage.setResizable(false);
		stage.show();
	}
}
