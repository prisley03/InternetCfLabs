package header;

import controller.UserController;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.object.User;
import view.BookPCPage;
import view.MainPage;
import view.MakeReportPage;
import view.TransactionHistoryPage;
import view.ViewAllPC;
import view.ViewAllStaff;
import view.ViewPCBookedData;
import view.ViewTechnicianJobPage;

public class HeaderMenu {

  public static class HeaderMenuVar {

    public MenuBar bar = new MenuBar();

    private Menu operationMenu = new Menu("Operations");

    // all role
    private MenuItem viewAllPCMenuItem = new MenuItem("View All PC");
    private Label homeMenuLbl = new Label("Home");
		private Menu homeMenu = new Menu("", homeMenuLbl);

    // customer
    private MenuItem viewTransactionHistoryMenuItem = new MenuItem(
      "View Transaction History"
    );
    private MenuItem bookPCMenuItem = new MenuItem("Book PC");

    private MenuItem makeReportMenuItem = new MenuItem("Make Report");

    // operator
    private MenuItem viewPCBookedData = new MenuItem("View PC Booked Data");

    // technician
    private MenuItem viewTechnicianJob = new MenuItem("View Technician Job");

    // admin
    private MenuItem viewAllStaff = new MenuItem("View All Staff");
    private MenuItem viewAllTechnicianJob = new MenuItem("View All Technician Job");
    private MenuItem viewAllReport = new MenuItem("View All Report");
    private MenuItem viewAllTransactionHistory = new MenuItem(
      "View All Transaction History"
    );

    private Menu userMenu = new Menu("User");
    private MenuItem logOutMenuItem = new MenuItem("Log Out");
  }

  public void initialize(HeaderMenuVar obj, String role) {
    switch (role) {
      case "Customer":
        obj.operationMenu
          .getItems()
          .addAll(
            obj.viewAllPCMenuItem,
            obj.viewTransactionHistoryMenuItem,
            obj.bookPCMenuItem,
            obj.makeReportMenuItem
          );
        break;
      case "Operator":
        obj.operationMenu
          .getItems()
          .addAll(
            obj.viewAllPCMenuItem,
            obj.viewPCBookedData,
            obj.makeReportMenuItem
          );
        break;
      case "Computer Technician":
        obj.operationMenu
          .getItems()
          .addAll(obj.viewAllPCMenuItem, obj.viewTechnicianJob);
        break;
      case "Admin":
        obj.operationMenu
          .getItems()
          .addAll(
            obj.viewAllPCMenuItem,
            obj.viewAllReport,
            obj.viewAllStaff,
            obj.viewAllTechnicianJob,
            obj.viewAllTransactionHistory
          );
        break;
    }

    obj.userMenu.getItems().addAll(obj.logOutMenuItem);

    obj.bar.getMenus().addAll(obj.homeMenu,obj.operationMenu, obj.userMenu);
  }

  public void setActions(HeaderMenuVar obj, Stage stage, String role) {
    obj.homeMenuLbl.setOnMouseClicked(e -> {
			new MainPage(stage, role);
		});

    obj.logOutMenuItem.setOnAction(e -> {
      User.setActiveUser(null);
      UserController.getInstance().navigateToRegister(stage);
    });

    obj.viewAllPCMenuItem.setOnAction(e -> {
      new ViewAllPC(stage);
    });

    obj.viewTransactionHistoryMenuItem.setOnAction(e -> {
      new TransactionHistoryPage(stage);
    });
    
    obj.viewPCBookedData.setOnAction(e -> {
    	new ViewPCBookedData(stage);
    });

    obj.bookPCMenuItem.setOnAction(e -> {
      new BookPCPage(stage);
    });

    obj.makeReportMenuItem.setOnAction(e -> {
      new MakeReportPage(stage);
    });
    
    obj.viewAllStaff.setOnAction(e -> {
    	new ViewAllStaff(stage);
    });
    
    obj.viewAllTechnicianJob.setOnAction(e -> {
    	new ViewTechnicianJobPage(stage);
    });
  }

  public MenuBar getMenuHeader(Stage stage, String role) {
    HeaderMenuVar obj = new HeaderMenuVar();
    initialize(obj, role);
    setActions(obj, stage, role);
    return obj.bar;
  }

  public HeaderMenu() {}
}
