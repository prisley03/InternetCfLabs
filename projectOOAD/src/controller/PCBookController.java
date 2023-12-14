package controller;

import model.database.PCBookDatabase;
import model.database.PCDatabase;
import model.object.PCBook;
import model.object.User;
import view.BookPCPage.BookPCObj;

public class PCBookController {
	private static class SingletonHelper{
		private static final PCBookController INSTANCE = new PCBookController();
		private static final PCBookDatabase pcBookDB = new PCBookDatabase();
	}
	
	public static PCBookController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public void getPCBookedData(BookPCObj obj, int id, String date) {
		PCBook pcBook = SingletonHelper.pcBookDB.getPCBookedData(id, date);
		
		if(pcBook != null) {
			obj.errorMessage.setStyle("-fx-text-fill: RED;");
			obj.errorMessage.setText("Someone has already booked this PC!");
		} else {			
			SingletonHelper.pcBookDB.AddNewBook(id, User.getActiveUser().getUserId(), date);
			obj.errorMessage.setStyle("-fx-text-fill: GREEN;");
			obj.errorMessage.setText("PC Booked Successfully!");
		}
	
		return;
	}
}
