
package view;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.TransactionController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.object.TransactionDetail;
import model.object.User;

public class ViewTransactionDetailHistoryPage {
	
	TransactionController transactionController = TransactionController.getInstance();
	
	//TableView will be used to view all transaction history data
	public class TransactionDetailPageObj {
		private Scene transactionDetailHistoryScene;
		private BorderPane transactionHistoryPane = new BorderPane();
		private ScrollPane outerContainer = new ScrollPane();
		
		private VBox leftContainer = new VBox();
		
		private TableView<TransactionDetail> transactionDetailHistoryTableView = new TableView<>();	
		private TableColumn<TransactionDetail, Integer> transactionIdColumn = new TableColumn<>("TransactionID");
		private TableColumn<TransactionDetail, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		private TableColumn<TransactionDetail, String> customerColumn = new TableColumn<>("CustomerName");
		private TableColumn<TransactionDetail, LocalDateTime> bookedTimeColumn = new TableColumn<>("Booked Time");
		
		private Label titleLbl = new Label();
		private Button backBtn = new Button("< Back");
	}
	
	public Scene initialize(TransactionDetailPageObj obj, Stage stage, String role){
		obj.transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		obj.customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		obj.bookedTimeColumn.setCellValueFactory(new PropertyValueFactory<>("bookedTime"));
	    
		obj.leftContainer.getChildren().addAll(obj.backBtn, obj.titleLbl);
		
		obj.transactionDetailHistoryTableView.getColumns().addAll(
				obj.transactionIdColumn, obj.pcIDColumn,
				obj.customerColumn, obj.bookedTimeColumn);
		
	    obj.outerContainer.setContent(obj.transactionDetailHistoryTableView);
	    obj.transactionHistoryPane.setTop(new HeaderMenu().getMenuHeader(stage, role));

	    obj.transactionHistoryPane.setCenter(obj.transactionDetailHistoryTableView);
	    obj.transactionHistoryPane.setLeft(obj.leftContainer);
		obj.transactionDetailHistoryScene = new Scene(obj.transactionHistoryPane, 800, 500);

		return obj.transactionDetailHistoryScene;
	}
	
	// TransactionID passed to retrieve transaction detail that has the same transactionID with selected transaction header
	public void bindData(TransactionDetailPageObj obj, int transactionID) {
		obj.titleLbl.setText("Transaction Detail : T"+ transactionID );
		ArrayList<TransactionDetail> detailList = TransactionController.getInstance().getTransactionDetailByTransactionID(transactionID);
        ObservableList<TransactionDetail> transactionList = FXCollections.observableArrayList(detailList);
		obj.transactionDetailHistoryTableView.setItems(transactionList);
	}

	public void setStyle(TransactionDetailPageObj comp) {
		comp.leftContainer.setSpacing(10);
		comp.leftContainer.setPadding(new Insets(10));
	}
	
	public void setActions(TransactionDetailPageObj obj, Stage stage) {
		obj.backBtn.setOnMouseClicked(e ->{
			new ViewAllTransactionHistoryPage(stage);
		});
	}
	
	public ViewTransactionDetailHistoryPage(Stage stage, int transactionID) {
		User user = User.getActiveUser();
//		TransactionDetail sTD = transactionController.getTransactionDetailByTransactionID(transactionID)
		TransactionDetailPageObj obj = new TransactionDetailPageObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj, transactionID);
		setStyle(obj);
		setActions(obj, stage);
		
		stage.setScene(obj.transactionDetailHistoryScene);
		stage.setTitle("Transaction Detail Page - " + transactionID );
		stage.setResizable(false);
		stage.show();
	}
}

