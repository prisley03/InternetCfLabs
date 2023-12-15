package view;

import controller.PCController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.PC;
import model.object.User;

public class ViewAllPC {
	
	//TableView will be used to display all PCs as a list
	public class ViewAllPCObj {
		private Scene allPCScene;
		private BorderPane allPCpane = new BorderPane();
		private ScrollPane outerContainer = new ScrollPane();
		private TableView<PC> allPCTableView = new TableView<>();	
		private VBox allPCcontainer = new VBox();
		private TableColumn<PC, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		private TableColumn<PC, String> pcConditionColumn = new TableColumn<>("PC_Condition");
		private Label titleLbl = new Label("View All PC");
		private Label instructionLbl = new Label("Double click to access Detail Page"); 
//		private TableColumn<Button, String> actionColumn = new TableColumn<>("Action");
//		private Button viewDetailBtn = new Button("View Detail");
		
	}
	
	public Scene initialize(ViewAllPCObj obj, Stage stage, String role){
		obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
		obj.pcConditionColumn.setCellValueFactory(new PropertyValueFactory<>("pcCondition"));
//		obj.actionColumn.setCellValueFactory(new PropertyValueFactory<>("pcAction") );
	    obj.allPCTableView.getColumns().addAll(obj.pcIDColumn, obj.pcConditionColumn);
		
	    obj.allPCcontainer.getChildren().addAll(obj.titleLbl, obj.instructionLbl);
	    
	    obj.outerContainer.setContent(obj.allPCTableView);
	    obj.allPCpane.setTop(new HeaderMenu().getMenuHeader(stage, role)); //different roles will have different menus

	    obj.allPCpane.setCenter(obj.allPCcontainer);
	    obj.allPCpane.setBottom( obj.allPCTableView);
		obj.allPCScene = new Scene(obj.allPCpane, 800, 500);

		return obj.allPCScene;
	}
	
	public void bindData(ViewAllPCObj obj) {
		//Get all data from the database via controller to display
        ObservableList<PC> pcList = FXCollections.observableArrayList(PCController.getInstance().getAllPCData());
		obj.allPCTableView.setItems(pcList);
	}

	public void setStyle(ViewAllPCObj obj) {
		obj.pcIDColumn.setMinWidth(obj.allPCpane.getWidth() /3);
		obj.pcConditionColumn.setMinWidth(obj.allPCpane.getWidth() /3);
		
		obj.titleLbl.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		obj.allPCcontainer.setAlignment(Pos.CENTER);
	}
	
	public void setAction(Stage stage, ViewAllPCObj obj) {
		obj.allPCTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
            	PC selectedPC = obj.allPCTableView.getSelectionModel().getSelectedItem();
            	new PCDetailPage(stage, selectedPC);
//                System.out.println(obj.allPCTableView.getSelectionModel().getSelectedItem().getPcId());
//                System.out.println(obj.allPCTableView.getSelectionModel().getSelectedItem().getPcCondition());
            }
        });
    }


	
	public ViewAllPC(Stage stage) {
		User user = User.getActiveUser();
		ViewAllPCObj obj = new ViewAllPCObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		setAction(stage, obj);
		
		stage.setScene(obj.allPCScene);
		stage.setTitle("View All PC");
		stage.setResizable(false);
		stage.show();
	}
}
