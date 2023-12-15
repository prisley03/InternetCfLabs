package view;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.TransactionController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.object.TransactionDetail;
import model.object.User;

public class TransactionHistoryPage {
	public class TransactionHistoryPageObj {
		private Scene transactionDetailHistoryScene;
		private BorderPane transactionHistoryPane = new BorderPane();
		private ScrollPane outerContainer = new ScrollPane();
		private TableView<TransactionDetail> transactionDetailHistoryTableView = new TableView<>();	
		private TableColumn<TransactionDetail, Integer> transactionIdColumn = new TableColumn<>("TransactionID");
		private TableColumn<TransactionDetail, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		private TableColumn<TransactionDetail, String> customerColumn = new TableColumn<>("CustomerName");
		private TableColumn<TransactionDetail, LocalDateTime> bookedTimeColumn = new TableColumn<>("Booked Time");
	}
	
	public Scene initialize(TransactionHistoryPageObj obj, Stage stage, String role){
		obj.transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		obj.customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		obj.bookedTimeColumn.setCellValueFactory(new PropertyValueFactory<>("bookedTime"));
	    
		obj.transactionDetailHistoryTableView.getColumns().addAll(
				obj.transactionIdColumn, obj.pcIDColumn,
				obj.customerColumn, obj.bookedTimeColumn);
		
	    obj.outerContainer.setContent(obj.transactionDetailHistoryTableView);
	    obj.transactionHistoryPane.setTop(new HeaderMenu().getMenuHeader(stage, role));

	    obj.transactionHistoryPane.setCenter(obj.transactionDetailHistoryTableView);
		obj.transactionDetailHistoryScene = new Scene(obj.transactionHistoryPane, 800, 500);

		return obj.transactionDetailHistoryScene;
	}
	
	public void bindData(TransactionHistoryPageObj obj) {
		User user = User.getActiveUser();
		ArrayList<TransactionDetail> detailList = TransactionController.getInstance().getUserTransactionDetail(user.getUserId());
        ObservableList<TransactionDetail> transactionList = FXCollections.observableArrayList(detailList);
		obj.transactionDetailHistoryTableView.setItems(transactionList);
	}

	public void setStyle(TransactionHistoryPageObj comp) {
		
	}
	
	public TransactionHistoryPage(Stage stage) {
		User user = User.getActiveUser();
		TransactionHistoryPageObj obj = new TransactionHistoryPageObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		
		stage.setScene(obj.transactionDetailHistoryScene);
		stage.setTitle("View Transaction History");
		stage.setResizable(false);
		stage.show();
	}
}
