package view;

import java.util.ArrayList;
import java.util.Date;

import controller.ReportController;
import header.HeaderMenu;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import model.object.Report;
import model.object.User;

public class ViewAllReport {
	
//	ReportController reportController = ReportController.getInstance();
	
	//TableView will be used to display all reports as a list
	public class ViewAllReportObj {
		private Scene allReportScene;
		private BorderPane allReportpane = new BorderPane();
//		private ScrollPane outerContainer = new ScrollPane();
		
		private VBox allReportcontainer = new VBox();
		
		private TableView<Report> allReportTableView = new TableView<>();	
		private TableColumn<Report, Integer> reportIDColumn = new TableColumn<>("Report_ID");
		private TableColumn<Report, String> userRoleColumn = new TableColumn<>("UserRole");
		private TableColumn<Report, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		private TableColumn<Report, String> reportNoteColumn = new TableColumn<>("ReportNote");
		private TableColumn<Report, Date> ReportDateColumn = new TableColumn<>("ReportDate");
		
		
		
		private Label titleLbl = new Label("View All Report");
	}
	
	public Scene initialize(ViewAllReportObj obj, Stage stage, String role){
		obj.reportIDColumn.setCellValueFactory(new PropertyValueFactory<>("reportId"));
		obj.userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		obj.reportNoteColumn.setCellValueFactory(new PropertyValueFactory<>("reportNote"));
		obj.ReportDateColumn.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
		
		obj.allReportTableView.getColumns().addAll(obj.reportIDColumn, obj.userRoleColumn,obj.pcIDColumn,obj.reportNoteColumn,obj.ReportDateColumn );
		
		obj.allReportcontainer.getChildren().addAll(obj.titleLbl);
		
//	    obj.outerContainer.setContent(obj.allReportTableView);
	    obj.allReportpane.setTop(new HeaderMenu().getMenuHeader(stage, role)); //different roles will have different menus

	    obj.allReportpane.setCenter(obj.allReportcontainer);
	    obj.allReportpane.setBottom( obj.allReportTableView);
		obj.allReportScene = new Scene(obj.allReportpane, 800, 500);

		return obj.allReportScene;
	}
	
	public void bindData(ViewAllReportObj obj) {
		
		ArrayList<Report> reportAList = ReportController.getInstance().getAllReport();
		//Get all data from the database via controller to display
        
        
        Platform.runLater(() -> {
        	ObservableList<Report> reportList = FXCollections.observableArrayList(reportAList);
            obj.allReportTableView.setItems(reportList);
        });
		
	}

	public void setStyle(ViewAllReportObj obj) {
		obj.titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		obj.allReportcontainer.setAlignment(Pos.CENTER);
	}
	
	public void setAction(Stage stage, ViewAllReportObj obj, String role) {

    }


	
	public ViewAllReport(Stage stage) {
		User user = User.getActiveUser();
		ViewAllReportObj obj = new ViewAllReportObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		setAction(stage, obj, user.getUserRole());
		
		stage.setScene(obj.allReportScene);
		stage.setTitle("View All Report");
		stage.setResizable(false);
		stage.show();
	}
}
