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

public class HeaderMenu {
	
	public static class HeaderMenuVar{
		public MenuBar bar = new MenuBar();
		
		private Label homeMenuLbl = new Label("Home");
		private Menu homeMenu = new Menu("", homeMenuLbl);
		
		private Menu operationMenu = new Menu("Operations");
		private MenuItem viewAllPCMenuItem = new MenuItem("View All PC");
		private MenuItem viewTransactionHistoryMenuItem = new MenuItem("View Transaction History");
		private MenuItem bookPCMenuItem = new MenuItem("Book PC");
		private MenuItem makeReportMenuItem = new MenuItem("Make Report");
		
		private MenuItem viewAllStaffMenuItem = new MenuItem("View All Staff");
		private MenuItem viewAllTechnicianJobMenuItem = new MenuItem("View All Technician Job");
		private MenuItem viewAllReportMenuItem = new MenuItem("View All Report");
		
		private Menu userMenu = new Menu("User");
		private MenuItem logOutMenuItem = new MenuItem("Log Out");
		
		
	}
	
	public void initialize(HeaderMenuVar obj) {
		if(User.getActiveUser().getUserRole().equals("Customer")) {
			obj.operationMenu.getItems().addAll(obj.viewAllPCMenuItem, 
					obj.viewTransactionHistoryMenuItem, obj.bookPCMenuItem, obj.makeReportMenuItem);			
		}
		
		if(User.getActiveUser().getUserRole().equals("Admin")) {
			obj.operationMenu.getItems().addAll(obj.viewAllPCMenuItem, obj.viewAllStaffMenuItem, 
					obj.viewAllTechnicianJobMenuItem, obj.viewTransactionHistoryMenuItem, obj.viewAllReportMenuItem);
		}
		obj.userMenu.getItems().addAll(obj.logOutMenuItem);
		
		obj.bar.getMenus().addAll(obj.homeMenu, obj.operationMenu, obj.userMenu);
	}
	
	public void setActions(HeaderMenuVar obj, Stage stage) {
		obj.homeMenuLbl.setOnMouseClicked(e -> {
			new MainPage(stage);
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
		
		obj.bookPCMenuItem.setOnAction(e -> {
			new BookPCPage(stage);
		});
		
		obj.makeReportMenuItem.setOnAction(e -> {
			new MakeReportPage(stage);
		});
	}
	
	public MenuBar getMenuHeader(Stage stage) {
		HeaderMenuVar obj = new HeaderMenuVar();
		initialize(obj);
		setActions(obj, stage);
		return obj.bar;
	}
	
	public HeaderMenu() {}
	
}
