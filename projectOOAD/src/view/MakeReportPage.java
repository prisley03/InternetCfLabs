package view;

import controller.PCController;
import controller.ReportController;
import header.HeaderMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.object.PC;
import model.object.User;

public class MakeReportPage {

  public class MakeReportObj {

    private Scene makeReportScene;
    private BorderPane reportPane = new BorderPane();
    private ScrollPane scrollPane = new ScrollPane();
    private VBox containerBox = new VBox();

    private VBox tableBox = new VBox();
    private Label tableLabel = new Label("Available PC's");
    private TableView<PC> allPCTableView = new TableView<>();
    private TableColumn<PC, Integer> pcIDColumn = new TableColumn<>("PC_ID");
    private TableColumn<PC, String> pcConditionColumn = new TableColumn<>(
      "PC_Condition"
    );

    private VBox reportBox = new VBox();
    private Label titleLabel = new Label("Make Report");

    private VBox pcBox = new VBox();
    public Label pcLabel = new Label("PC ID");
    public ComboBox<String> pcIDInput = new ComboBox<String>();

    private VBox notesBox = new VBox();
    public Label notesLabel = new Label("Notes");
    public TextArea notesInput = new TextArea();

    public Button submitButton = new Button("Submit");
    public Label message = new Label("");
  }

  @SuppressWarnings("unchecked")
  public Scene initialize(MakeReportObj obj, Stage stage, String role) {
    obj.pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcId"));
    obj.pcConditionColumn.setCellValueFactory(
      new PropertyValueFactory<>("pcCondition")
    );
    obj.allPCTableView
      .getColumns()
      .setAll(obj.pcIDColumn, obj.pcConditionColumn);
    obj.tableBox.getChildren().addAll(obj.tableLabel, obj.allPCTableView);

    obj.pcIDColumn.setPrefWidth(50);
    obj.pcConditionColumn.setPrefWidth(290);

    obj.pcBox.getChildren().addAll(obj.pcLabel, obj.pcIDInput);
    obj.notesBox.getChildren().addAll(obj.notesLabel, obj.notesInput);
    obj.reportBox
      .getChildren()
      .addAll(
        obj.titleLabel,
        obj.pcBox,
        obj.notesBox,
        obj.submitButton,
        obj.message
      );

    obj.containerBox.getChildren().addAll(obj.tableBox, obj.reportBox);
    obj.scrollPane.setContent(obj.containerBox);
    obj.reportPane.setTop(new HeaderMenu().getMenuHeader(stage, role));

    Insets vboxPadding = new Insets(10, 0, 10, 0);
    obj.tableBox.setSpacing(10);
    obj.reportBox.setSpacing(10);
    obj.containerBox.setSpacing(20);
    obj.containerBox.setPadding(vboxPadding);

    obj.reportPane.setCenter(obj.scrollPane);
    obj.makeReportScene = new Scene(obj.reportPane, 800, 600);

    return obj.makeReportScene;
  }

  public void bindData(MakeReportObj obj) {
    ObservableList<PC> pcList = FXCollections.observableArrayList(
      PCController.getInstance().getAllPCData()
    );
    obj.allPCTableView.setItems(pcList);

    obj.pcIDInput.getItems().add("Select All");

    for (PC pc : pcList) {
      obj.pcIDInput.getItems().add(String.format("PC %d", pc.getPcId()));
    }

    obj.pcIDInput.getSelectionModel().selectFirst();
  }

  public void setActions(MakeReportObj obj) {
    ReportController reportController = ReportController.getInstance();

    obj.submitButton.setOnMouseClicked(e -> {
      User user = User.getActiveUser();

      if (reportController.validateNewReport(obj)) {
        reportController.addNewReport(
          user.getUserRole(),
          obj.pcIDInput.getValue(),
          obj.notesInput.getText()
        );
      }
    });
  }

  public void setStyle(MakeReportObj comp) {
    comp.tableBox.setAlignment(Pos.CENTER);

    comp.reportBox.setAlignment(Pos.CENTER);

    comp.containerBox.setAlignment(Pos.CENTER);
    comp.containerBox.setMaxWidth(350);
    
    comp.titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
    comp.tableLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
    
    comp.message.setStyle("-fx-text-fill: RED;");
    if(comp.message.equals("Successfully Inserted")) {
    	comp.message.setStyle("-fx-text-fill: GREEN;");
    }
    
    comp.reportPane.setCenter(comp.containerBox);
  }

  public MakeReportPage(Stage stage) {
	User user = User.getActiveUser();
    MakeReportObj obj = new MakeReportObj();
    initialize(obj, stage, user.getUserRole());
    bindData(obj);
    setStyle(obj);
    setActions(obj);

    stage.setScene(obj.makeReportScene);
    stage.setTitle("Make Report");
    stage.setResizable(false);
    stage.show();
  }
}
