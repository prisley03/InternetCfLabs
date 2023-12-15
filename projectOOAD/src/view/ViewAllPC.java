package view;

import controller.PCController;
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
import model.object.PC;
import model.object.User;

public class ViewAllPC {
	public class ViewAllPCObj {
		private Scene allPCScene;
		private BorderPane allPCpane = new BorderPane();
		private ScrollPane outerContainer = new ScrollPane();
		private TableView<PC> allPCTableView = new TableView<>();	
		private TableColumn<PC, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		private TableColumn<PC, String> pcConditionColumn = new TableColumn<>("PC_Condition");
		
	}
	
	public Scene initialize(ViewAllPCObj obj, Stage stage, String role){
		obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		obj.pcConditionColumn.setCellValueFactory(new PropertyValueFactory<>("pcCondition"));
	    obj.allPCTableView.getColumns().addAll(obj.pcIDColumn, obj.pcConditionColumn);
		
	    obj.outerContainer.setContent(obj.allPCTableView);
	    obj.allPCpane.setTop(new HeaderMenu().getMenuHeader(stage, role));

	    obj.allPCpane.setCenter(obj.allPCTableView);
		obj.allPCScene = new Scene(obj.allPCpane, 800, 500);

		return obj.allPCScene;
	}
	
	public void bindData(ViewAllPCObj obj) {
        ObservableList<PC> pcList = FXCollections.observableArrayList(PCController.getInstance().getAllPCData());
		obj.allPCTableView.setItems(pcList);
	}

	public void setStyle(ViewAllPCObj comp) {
		
	}
	
	public ViewAllPC(Stage stage) {
		User user = User.getActiveUser();
		ViewAllPCObj obj = new ViewAllPCObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		
		stage.setScene(obj.allPCScene);
		stage.setTitle("View All PC");
		stage.setResizable(false);
		stage.show();
	}
}
