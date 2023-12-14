package header;

import controller.UserController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.object.User;
import view.ViewAllPC;

public class HeaderMenu {
	
	public static class HeaderMenuVar{
		public MenuBar bar = new MenuBar();
		
		private Menu operationMenu = new Menu("Operations");
		private MenuItem viewAllPCMenuItem = new MenuItem("View All PC");
		private MenuItem viewTransactionHistoryMenuItem = new MenuItem("View Transaction History");
		private MenuItem bookPCMenuItem = new MenuItem("Book PC");
		private MenuItem makeReportMenuItem = new MenuItem("Make Report");
		
		private Menu userMenu = new Menu("User");
		private MenuItem logOutMenuItem = new MenuItem("Log Out");
		
	}
	
	public void initialize(HeaderMenuVar obj) {
		obj.operationMenu.getItems().addAll(obj.viewAllPCMenuItem, 
				obj.viewTransactionHistoryMenuItem, obj.bookPCMenuItem, obj.makeReportMenuItem);
		
		obj.userMenu.getItems().addAll(obj.logOutMenuItem);
		
		obj.bar.getMenus().addAll(obj.operationMenu, obj.userMenu);
	}
	
	public void setActions(HeaderMenuVar obj, Stage stage) {
		obj.logOutMenuItem.setOnAction(e -> {
			User.setActiveUser(null);
			UserController.getInstance().navigateToRegister(stage);
		});
		
		obj.viewAllPCMenuItem.setOnAction(e -> {
			new ViewAllPC(stage);
		});
		
		obj.viewTransactionHistoryMenuItem.setOnAction(e -> {
					
		});
		
		obj.bookPCMenuItem.setOnAction(e -> {
			
		});
		
		obj.makeReportMenuItem.setOnAction(e -> {
			
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
