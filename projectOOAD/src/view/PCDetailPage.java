package view;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import controller.PCController;
import controller.UserController;
import header.HeaderMenu;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.PC;
import model.object.User;

public class PCDetailPage {
	
	private PCController pcController = PCController.getInstance();
	
	public class PCDetailobj{
		private Scene PCDetailScene;
		private BorderPane PCDetailPane = new BorderPane();
		private GridPane formPane = new GridPane();
		private VBox centerContainer = new VBox();
		private VBox BackBtnContainer = new VBox();
		private HBox buttonContainer = new HBox();
		private Label pcIDlbl = new Label("");
		private Label pcConditionlbl = new Label("PC Condition : ");
		private ComboBox<String> pcConditionCombo= new ComboBox<String>();
		
		private Button updateBtn = new Button("Update");
		private Button deleteBtn = new Button("Delete");
		private Button backBtn = new Button("< Back");
		private Label errorMessage = new Label("");
		
	}
	public Scene initialize(PCDetailobj obj, Stage stage, String role) {
		obj.buttonContainer.getChildren().addAll(obj.updateBtn, obj.deleteBtn);
		obj.BackBtnContainer.getChildren().add(obj.backBtn);
		obj.centerContainer.getChildren().addAll(obj.formPane, obj.buttonContainer,obj.errorMessage,obj.BackBtnContainer);
		
		obj.PCDetailPane.setTop(new HeaderMenu().getMenuHeader(stage, role));
		obj.PCDetailPane.setCenter(obj.centerContainer);
		
		
		
		obj.formPane.add(obj.pcIDlbl, 0, 0);
		obj.formPane.add(obj.pcConditionlbl, 0, 1);
		obj.formPane.add(obj.pcConditionCombo, 1, 1);
		obj.PCDetailScene = new Scene(obj.PCDetailPane, 800, 500);
		
		return obj.PCDetailScene;
	}
	public void setStyle(PCDetailobj obj) {
		obj.centerContainer.setAlignment(Pos.CENTER);
		obj.formPane.setAlignment(Pos.CENTER);
		obj.formPane.setHgap(10);
		obj.formPane.setVgap(5);
		
		obj.buttonContainer.setAlignment(Pos.CENTER);
		obj.buttonContainer.setPadding(new Insets(10));
		obj.buttonContainer.setSpacing(10);
		
		obj.backBtn.setMinWidth(270);
		
		obj.BackBtnContainer.setPadding(new Insets(50));
		obj.BackBtnContainer.setAlignment(Pos.TOP_CENTER);
		
		
		obj.errorMessage.setStyle("-fx-text-fill: RED;");
		obj.errorMessage.setAlignment(Pos.CENTER);
	}
	
	public void bindData(PCDetailobj obj, PC spc) {
		obj.pcIDlbl.setText(String.format("PC ID : %d", spc.getPcId()));
		
		ArrayList<String> pcConditionAList = new ArrayList<>();
		pcConditionAList.add("Usable");
		pcConditionAList.add("Broken");
		pcConditionAList.add("Maintenance");
		obj.pcConditionCombo.setItems(FXCollections.observableArrayList(pcConditionAList));
		
		for(String condition : pcConditionAList) {
			if(condition.equals(spc.getPcCondition())) {
				obj.pcConditionCombo.getSelectionModel().select(condition);
			}
		}

	}
	
	public void setActions(Stage stage, PCDetailobj obj, PC spc) {
		obj.updateBtn.setOnMouseClicked(e ->{
			int pcID = spc.getPcId();
			String pcCondition = obj.pcConditionCombo.getSelectionModel().getSelectedItem();

			if(pcController.updatePCCondition(pcID, pcCondition)) {
				new ViewAllPC(stage);
			}else {
				obj.errorMessage.setText("Something wrong!");
			}
		});
		
		obj.deleteBtn.setOnMouseClicked(e -> {
			if(!pcController.checkOnGoingBookByPCID(spc.getPcId())
					&& pcController.deletePCByID(spc.getPcId())) {
				new ViewAllPC(stage);
			}else {
				obj.errorMessage.setText("This pc is currently booked by a customer");
			}
		});
		
		obj.backBtn.setOnMouseClicked(e ->{
			new ViewAllPC(stage);
		});
	}
	
	public PCDetailPage(Stage stage, PC selectedPC) {
		User user = User.getActiveUser();
		PCDetailobj obj = new PCDetailobj();
		initialize(obj, stage, user.getUserRole());
		bindData(obj, selectedPC);
		setStyle(obj);
		setActions(stage, obj, selectedPC);
		
		stage.setScene(obj.PCDetailScene);
		stage.setTitle("Detail PC" + selectedPC.getPcId());
		stage.setResizable(false);
		stage.show();
		
		
		// TODO Auto-generated constructor stub
	}
	
}
