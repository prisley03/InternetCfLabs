package view;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.TransactionController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.object.PC;
import model.object.TransactionDetail;
import model.object.TransactionHeader;
import model.object.User;

public class ViewAllTransactionHistoryPage {
	
	//TableView will be used to view all transaction history data
	public class AllTransactionHistoryObj {
		private Scene transactionHeaderHistoryScene;
		private BorderPane transactionHeaderHistoryPane = new BorderPane();
		private ScrollPane outerContainer = new ScrollPane();
		
		private TableView<TransactionHeader> transactionHeaderHistoryTableView = new TableView<>();	
		private TableColumn<TransactionHeader, Integer> transactionIdColumn = new TableColumn<>("TransactionID");
		private TableColumn<TransactionHeader, Integer> staffIDColumn = new TableColumn<>("StaffID");
		private TableColumn<TransactionHeader, String> staffNameColumn = new TableColumn<>("StaffName");
		private TableColumn<TransactionHeader, LocalDateTime> TransactionDateColumn = new TableColumn<>("TransactionDate");
		
	}
	
	public Scene initialize(AllTransactionHistoryObj obj, Stage stage, String role){
		obj.transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		obj.staffIDColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));
		obj.staffNameColumn.setCellValueFactory(new PropertyValueFactory<>("staffName"));
		obj.TransactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
	    
		obj.transactionHeaderHistoryTableView.getColumns().addAll(
				obj.transactionIdColumn, obj.staffIDColumn,
				obj.staffNameColumn, obj.TransactionDateColumn);
		
	    obj.outerContainer.setContent(obj.transactionHeaderHistoryTableView);
	    obj.transactionHeaderHistoryPane.setTop(new HeaderMenu().getMenuHeader(stage, role));

	    obj.transactionHeaderHistoryPane.setCenter(obj.transactionHeaderHistoryTableView);
		obj.transactionHeaderHistoryScene = new Scene(obj.transactionHeaderHistoryPane, 800, 500);

		return obj.transactionHeaderHistoryScene;
	}
	
	public void bindData(AllTransactionHistoryObj obj) {
		User user = User.getActiveUser(); //User is retrieved in order to pass UserID into the transaction database
		ArrayList<TransactionHeader> headerList = TransactionController.getInstance().getAllTransactionHeader();
        ObservableList<TransactionHeader> transactionHeaderList = FXCollections.observableArrayList(headerList);
		obj.transactionHeaderHistoryTableView.setItems(transactionHeaderList);
	}

	
	public void setActions(Stage stage, AllTransactionHistoryObj obj ) {
		obj.transactionHeaderHistoryTableView.setOnMouseClicked((e) -> {
			if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2){
				TransactionHeader selectedTH = obj.transactionHeaderHistoryTableView.getSelectionModel().getSelectedItem();
				new ViewTransactionDetailHistoryPage(stage, selectedTH.getTransactionId());
			}
		});	
	}
	
	public void setStyle(AllTransactionHistoryObj comp) {
		
	}
	
	public ViewAllTransactionHistoryPage(Stage stage) {
		User user = User.getActiveUser();
		AllTransactionHistoryObj obj = new AllTransactionHistoryObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		setActions(stage, obj);
		
		stage.setScene(obj.transactionHeaderHistoryScene);
		stage.setTitle("View All Transaction Header");
		stage.setResizable(false);
		stage.show();
	}
}
