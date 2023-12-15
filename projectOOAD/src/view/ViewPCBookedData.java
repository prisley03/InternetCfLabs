package view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import controller.PCBookController;
import controller.PCController;
import controller.TransactionController;
import controller.UserController;
import header.HeaderMenu;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.PC;
import model.object.PCBook;
import model.object.User;

public class ViewPCBookedData {
	
	private UserController userController = UserController.getInstance();
	private PCController pcController = PCController.getInstance();
	
	public class ViewBookedDataObj {
		private Scene viewBookedScene;
		private BorderPane bookedPCPane = new BorderPane();
		private ScrollPane listPCPane = new ScrollPane();
		private VBox containerBox = new VBox();
		
		private VBox tableBox = new VBox();
		private Label pageTitle = new Label("List Booked PC");
		private TableView<PCBook> allPCTableView = new TableView<>();
		private TableColumn<PCBook, Integer> bookIDColumn = new TableColumn<>("Book_ID");
	    private TableColumn<PCBook, Integer> pcIDColumn = new TableColumn<>("PC_ID");
	    private TableColumn<PCBook, String> userName = new TableColumn<>("User Name");
	    private TableColumn<PCBook, Date> bookDate = new TableColumn<>("Book Date");
	    
	    private VBox editBox = new VBox();
	    public Label editLabel = new Label("Edit Booked PC");
	    private HBox editHorizontalBox = new HBox();
	    
	    private VBox cancelBox = new VBox();
	    public Label cancelLabel = new Label("Cancel Book");
	    public DatePicker cancelBookPicker = new DatePicker();
	    public Button cancelButton = new Button("Cancel");
	    
	    private VBox finishBox = new VBox();
	    public Label finishLabel = new Label("Finish Book");
	    public DatePicker finishBookPicker = new DatePicker();
	    public Button finishButton = new Button("Finish");
	    
	    public Label cancelMessage = new Label("");
	    public Label finishMessage = new Label("");
	    
	    private VBox assignUserBox = new VBox();
	    public Label assignLabel = new Label("Change User's PC Book");
	    public DatePicker assignBookPicker = new DatePicker();
	    
	    private HBox assignBox = new HBox();
	    private VBox pcIDInputBox = new VBox();
	    public Label pcLabel = new Label("PC ID");
	    public ComboBox<String> pcIDInput = new ComboBox<String>();
	    
	    private VBox userInputBox = new VBox();
	    public Label userLabel = new Label("User Name");
	    public ComboBox<String> userNameInput = new ComboBox<String>();
	    
	    public Button updateButton = new Button("Update");
	    public Label assignMessage = new Label("");
	}
	
	@SuppressWarnings("unchecked")
	public Scene initialize(ViewBookedDataObj obj, Stage stage, String role) {
		obj.bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
	    obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
	    obj.userName.setCellValueFactory(cellData -> {
	    	int userId = cellData.getValue().getUserId();
            String userName = "";

            if (userId > 0) {
                User user = userController.getUserDataById(userId);
                if (user != null) {
                    userName = user.getUsername();
                }
            }

            return new SimpleStringProperty(userName);
        });
	    obj.bookDate.setCellValueFactory(new PropertyValueFactory<>("bookedDate"));
	    
	    obj.bookIDColumn.setPrefWidth(150);
	    obj.pcIDColumn.setPrefWidth(150);
	    obj.userName.setPrefWidth(150);
	    obj.bookDate.setPrefWidth(150);
	    
	    obj.allPCTableView.getColumns().setAll(obj.bookIDColumn, obj.pcIDColumn, obj.userName, obj.bookDate);
	    obj.tableBox.getChildren().addAll(obj.pageTitle, obj.allPCTableView);
	    
	    obj.cancelBox.getChildren().addAll(obj.cancelLabel, obj.cancelBookPicker, obj.cancelButton, obj.cancelMessage);
	    obj.finishBox.getChildren().addAll(obj.finishLabel, obj.finishBookPicker, obj.finishButton, obj.finishMessage);
	    
	    obj.pcIDInputBox.getChildren().addAll(obj.pcLabel, obj.pcIDInput);
	    obj.userInputBox.getChildren().addAll(obj.userLabel, obj.userNameInput);
	    
	    obj.assignBox.getChildren().addAll(obj.userInputBox, obj.pcIDInputBox);
	    obj.assignUserBox.getChildren().addAll(obj.assignLabel, obj.assignBookPicker, obj.assignBox, obj.updateButton, obj.assignMessage);
	    
	    obj.editHorizontalBox.getChildren().addAll(obj.cancelBox, obj.finishBox, obj.assignUserBox);
	    obj.editBox.getChildren().addAll(obj.editLabel, obj.editHorizontalBox);
	    
	    obj.containerBox.getChildren().addAll(obj.tableBox, obj.editBox);
	    obj.listPCPane.setContent(obj.containerBox);
	    
	    obj.bookedPCPane.setTop(new HeaderMenu().getMenuHeader(stage, role));
	    obj.bookedPCPane.setCenter(obj.listPCPane);
	    
	    obj.viewBookedScene = new Scene(obj.bookedPCPane, 800, 600);
	    return obj.viewBookedScene;
	}
	
	public void bindData(ViewBookedDataObj obj) {
	    ObservableList<PCBook> pcBookList = FXCollections.observableArrayList(PCBookController.getInstance().getAllPCBookedData());
	    obj.allPCTableView.setItems(pcBookList);
	    
	    ArrayList<PC> pcList = pcController.getAllPCData();
	    ArrayList<PCBook> bookedPCList = PCBookController.getInstance().getAllPCBookedData();
	    
	    obj.assignBookPicker.setOnAction(e -> {
	    	LocalDate selectedDate = obj.assignBookPicker.getValue();
		    
		    obj.userNameInput.getItems().clear();
		    obj.pcIDInput.getItems().clear();
		    
		    for (PCBook bookedPC : bookedPCList) {
		    	Date sqlDate = (Date) bookedPC.getBookedDate();
		        LocalDateTime localDateTime = sqlDate.toLocalDate().atStartOfDay();
		        LocalDate bookedDateWithoutTime = localDateTime.toLocalDate();
		        
		        if (bookedDateWithoutTime.equals(selectedDate)) {
		            User user = userController.getUserDataById(bookedPC.getUserId());
		            if (user != null) {
		                obj.userNameInput.getItems().add(user.getUsername());
		            }
		        }
		    }

		    for (PC pc : pcList) {
		        boolean isBooked = false;
		        
		        for (PCBook bookedPC : bookedPCList) {
		        	Date sqlDate = (Date) bookedPC.getBookedDate();
			        LocalDateTime localDateTime = sqlDate.toLocalDate().atStartOfDay();
			        LocalDate bookedDateWithoutTime = localDateTime.toLocalDate();
			        
		            if (bookedPC.getPcId() == pc.getPcId() && bookedDateWithoutTime.equals(selectedDate) && pc.getPcCondition().equals("Usable")) {
		                isBooked = true;
		                break;
		            }
		        }
		        
		        if (!isBooked && pc.getPcCondition().equals("Usable")) {
		            obj.pcIDInput.getItems().add(String.format("PC %d", pc.getPcId()));
		        }
		    }

		    obj.userNameInput.getSelectionModel().selectFirst();
		    obj.pcIDInput.getSelectionModel().selectFirst();;
	    });
	}
	
	public void setStyle(ViewBookedDataObj obj) {
		obj.tableBox.setAlignment(Pos.CENTER);
		obj.editHorizontalBox.setAlignment(Pos.CENTER);
		obj.editBox.setAlignment(Pos.CENTER);
		
		obj.pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		obj.editLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		
		obj.cancelLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		obj.finishLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		obj.assignLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		
		obj.cancelMessage.setStyle("-fx-text-fill: RED;");
	    if(obj.cancelMessage.equals("Successfully Canceled!")) {
	    	obj.cancelMessage.setStyle("-fx-text-fill: GREEN;");
	    }
	    
	    obj.finishMessage.setStyle("-fx-text-fill: RED;");
	    if(obj.finishMessage.equals("Book Finished!")) {
	    	obj.finishMessage.setStyle("-fx-text-fill: GREEN;");
	    }
	    
	    obj.assignMessage.setStyle("-fx-text-fill: RED;");
	    if(obj.assignMessage.equals("Update Successfully")) {
	    	obj.assignMessage.setStyle("-fx-text-fill: GREEN;");
	    }
		
		obj.tableBox.setSpacing(10);
		obj.editHorizontalBox.setSpacing(10);
		obj.editBox.setSpacing(10);
		obj.containerBox.setSpacing(10);
		obj.cancelBox.setSpacing(10);
		obj.finishBox.setSpacing(10);
		obj.assignBox.setSpacing(10);
		obj.assignUserBox.setSpacing(10);
		
		obj.allPCTableView.setMaxHeight(300);
		
		obj.containerBox.setAlignment(Pos.CENTER);
		obj.containerBox.setMaxWidth(600);
		obj.cancelMessage.setMaxWidth(200);
		obj.finishMessage.setMaxWidth(200);
		
		obj.bookedPCPane.setCenter(obj.containerBox);
	}
	
	public void setActions(ViewBookedDataObj obj) {
		PCBookController pcBookController = PCBookController.getInstance();
		TransactionController transactionController = TransactionController.getInstance();
		User user = User.getActiveUser();
		ArrayList<PCBook> bookedPC = pcBookController.getAllPCBookedData();
		
		obj.cancelButton.setOnMouseClicked(e -> {
			if(pcBookController.validateCancelDate(obj)) {
				pcBookController.deleteBook(obj, "Cancel");
				bindData(obj);
			}
		});
		
		obj.finishButton.setOnMouseClicked(e -> {
			if(pcBookController.validateFinishDate(obj)) {
				String date = obj.finishBookPicker.getValue().toString();
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			    String formattedDateTime = now.format(formatter);
			    
				transactionController.addNewTransactionHeader(user.getUserId(), user.getUsername(), formattedDateTime);
				
				int pcID = 0;
				String custName = "";
				LocalDate selectedDate = obj.finishBookPicker.getValue();
				
				for(PCBook item : bookedPC) {
					Date sqlDate = (Date) item.getBookedDate();
			        LocalDateTime localDateTime = sqlDate.toLocalDate().atStartOfDay();
			        LocalDate bookedDateWithoutTime = localDateTime.toLocalDate();
			        
					if(bookedDateWithoutTime.equals(selectedDate)) {
						pcID = item.getPcId();
						
						if (item.getUserId()> 0) {
			                User customer = userController.getUserDataById(item.getUserId());
			                if (customer != null) {
			                	custName = customer.getUsername();
			                }
			            }
						
						break;
					}
				}
				
				transactionController.addNewTransactionDetail(pcID, custName, date);
				pcBookController.deleteBook(obj, "Finish");
				bindData(obj);
			}
		});
		
		obj.updateButton.setOnMouseClicked(e -> {
			if(pcBookController.validateAssignDate(obj)) {
				int pcID = Integer.parseInt(obj.pcIDInput.getValue().split(" ", 2)[1]);
				User customer = userController.getUserByName(obj.userNameInput.getValue());
				
				String date = obj.assignBookPicker.getValue().toString();
				
				if(pcBookController.updateChangedPCToUser(pcID, customer.getUserId(), date)) {
					obj.assignMessage.setText("Update Successfully");
				}
				bindData(obj);
			}
		});
	}
	
	public ViewPCBookedData(Stage stage) {
		User user = User.getActiveUser();
		ViewBookedDataObj obj = new ViewBookedDataObj();
	    initialize(obj, stage, user.getUserRole());
	    bindData(obj);
	    setStyle(obj);
	    setActions(obj);

	    stage.setScene(obj.viewBookedScene);
	    stage.setTitle("Make Report");
	    stage.setResizable(false);
	    stage.show();
	}
}
