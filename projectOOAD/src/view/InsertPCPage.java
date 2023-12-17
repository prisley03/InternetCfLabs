package view;


import java.util.ArrayList;

import controller.PCController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.object.PC;
import model.object.User;
import view.PCDetailPage.PCDetailobj;

public class InsertPCPage {
	PCController pcController = PCController.getInstance();
	
	public class InsertPCObj{
		private Scene InsertPCScene;
		private BorderPane InsertPCPane = new BorderPane();
		private GridPane formPane = new GridPane();
		private VBox centerContainer = new VBox();
		
		private Label pcIDlbl = new Label("PC ID : ");
		private Label pcConditionlbl = new Label("PC Condition : ");
		public TextField pcIDField = new TextField();
		private ComboBox<String> pcConditionCombo= new ComboBox<String>();
		
		
		private Button insertPCBtn = new Button("Insert PC");
		public Label errorMessage = new Label("");
		private Button backBtn = new Button("< Back");
	}
	
	public Scene initialize(InsertPCObj obj, Stage stage, String role){
		
		obj.formPane.add(obj.pcIDlbl, 0, 0);
		obj.formPane.add(obj.pcIDField, 1, 0);
		obj.formPane.add(obj.pcConditionlbl, 0, 1);
		obj.formPane.add(obj.pcConditionCombo, 1, 1);
		
		obj.centerContainer.getChildren().addAll(obj.formPane, obj.insertPCBtn, obj.errorMessage, obj.backBtn);
		
		obj.InsertPCPane.setTop(new HeaderMenu().getMenuHeader(stage, role)); //different roles will have different menus
		obj.InsertPCPane.setCenter(obj.centerContainer);
		
		obj.InsertPCScene = new Scene(obj.InsertPCPane, 800, 500);
		return obj.InsertPCScene;
	}
	
	public void bindData(InsertPCObj obj) {
		ArrayList<String> pcConditionAList = new ArrayList<>();
		pcConditionAList.add("Usable");
		pcConditionAList.add("Broken");
		pcConditionAList.add("Maintenance");
		obj.pcConditionCombo.setItems(FXCollections.observableArrayList(pcConditionAList));
		obj.pcConditionCombo.getSelectionModel().selectFirst();
	}
	
	public void setStyle(InsertPCObj obj) {
		obj.centerContainer.setAlignment(Pos.CENTER);
		obj.formPane.setAlignment(Pos.CENTER);
		obj.formPane.setHgap(10);
		obj.formPane.setVgap(5);
		
		obj.formPane.setPadding(new Insets(10));
		
		obj.errorMessage.setPadding(new Insets(10));
		
		obj.backBtn.setLineSpacing(10);
		obj.backBtn.setMinWidth(270);
		

		obj.errorMessage.setStyle("-fx-text-fill: RED;");			
		obj.errorMessage.setAlignment(Pos.CENTER);
	}
	
	public void setActions(Stage stage, InsertPCObj obj) {
		obj.backBtn.setOnMouseClicked(e ->{
			new ViewAllPC(stage);
		});
		
		obj.insertPCBtn.setOnMouseClicked(e -> {
			if(pcController.validateInsertPC(obj)){
				int pcID = Integer.parseInt(obj.pcIDField.getText());
				String pcCondition = obj.pcConditionCombo.getSelectionModel().getSelectedItem();
				if(pcController.addNewPC(pcID, pcCondition)) {
					obj.errorMessage.setText("PC inserted succesfully");
					obj.errorMessage.setStyle("-fx-text-fill: GREEN;");
				}
			}
		});
	}
	
	public InsertPCPage(Stage stage) {
		User user = User.getActiveUser();
		InsertPCObj obj = new InsertPCObj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj);
		setStyle(obj);
		setActions(stage, obj);
		
		stage.setScene(obj.InsertPCScene);
		stage.setTitle("Insert PC");
		stage.setResizable(false);
		stage.show();
	}

}
