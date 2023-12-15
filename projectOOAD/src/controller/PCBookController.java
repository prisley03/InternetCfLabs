package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import model.database.PCBookDatabase;
import model.object.PCBook;
import model.object.User;
import view.BookPCPage.BookPCObj;
import view.ViewPCBookedData.ViewBookedDataObj;

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
	
	public boolean validatePCBook(BookPCObj obj) {
        if (obj.pcComboBox.getValue().equals("Select All")) {
            obj.errorMessage.setText("Please select a PC!");
            return false;
        } else if (obj.bookingDatePicker.getValue().isBefore(LocalDate.now())) {
            obj.errorMessage.setText("Please select an upcoming date!");
            return false;
        }

        obj.errorMessage.setText("");
        return true;
    }
	
	public ArrayList<PCBook> getAllPCBookedData() {
		return SingletonHelper.pcBookDB.getAllPCBooked();
	}
	
	public boolean validateCancelDate(ViewBookedDataObj obj) {
		LocalDate selectedDate = obj.cancelBookPicker.getValue();
		ArrayList<PCBook> list = this.getAllPCBookedData();
		
		if(selectedDate == null) {
			obj.cancelMessage.setText("Please choose a date!");
			return false;
		} else if(selectedDate.isBefore(LocalDate.now()) && selectedDate != null) {
			obj.cancelMessage.setText("Must after today");
			return false;
		}
		
		for(PCBook pcBook : list) {
			Date sqlDate = (Date) pcBook.getBookedDate();
	        LocalDateTime localDateTime = sqlDate.toLocalDate().atStartOfDay();
	        LocalDate bookedDateWithoutTime = localDateTime.toLocalDate();
	        
			if(bookedDateWithoutTime.equals(selectedDate)) {
				obj.cancelMessage.setText("Successfully Canceled!");
				return true;
			} else {
				obj.cancelMessage.setText("Data not found");
			}
		}
		
		return false;
	}
	
	public boolean validateFinishDate(ViewBookedDataObj obj) {
		LocalDate selectedDate = obj.finishBookPicker.getValue();
		ArrayList<PCBook> list = this.getAllPCBookedData();
		
		if(selectedDate == null) {
			obj.finishMessage.setText("Please choose a date!");
			return false;
		} else if(selectedDate.isAfter(LocalDate.now()) && selectedDate != null) {
			obj.finishMessage.setText("Must today or before");
			return false;
		}
		
		for(PCBook pcBook : list) {
			Date sqlDate = (Date) pcBook.getBookedDate();
	        LocalDateTime localDateTime = sqlDate.toLocalDate().atStartOfDay();
	        LocalDate bookedDateWithoutTime = localDateTime.toLocalDate();
	        
			if(bookedDateWithoutTime.equals(selectedDate)) {
				obj.finishMessage.setText("Book Finished!");
				return true;
			} else {
				obj.finishMessage.setText("Data not found");
			}
		}
		
		return false;
	}
	
	public boolean deleteBook(ViewBookedDataObj obj, String type) {
		switch(type) {
			case "Cancel":
				SingletonHelper.pcBookDB.delete(obj.cancelBookPicker.getValue());
			case "Finish":
				SingletonHelper.pcBookDB.delete(obj.finishBookPicker.getValue());
		}
		
		return true;
	}
	
	public boolean validateAssignDate(ViewBookedDataObj obj) {
		LocalDate selectedDate = obj.assignBookPicker.getValue();
		ArrayList<PCBook> list = this.getAllPCBookedData();
		
		if(selectedDate == null) {
			obj.assignMessage.setText("Please choose a date!");
			return false;
		}
		
		for(PCBook pcBook : list) {
			Date sqlDate = (Date) pcBook.getBookedDate();
	        LocalDateTime localDateTime = sqlDate.toLocalDate().atStartOfDay();
	        LocalDate bookedDateWithoutTime = localDateTime.toLocalDate();
	        
			if(bookedDateWithoutTime.equals(selectedDate)) {
				return true;
			} else {
				obj.assignMessage.setText("Data not found");
			}
		}
		
		return false;
	}
	
	public boolean updateChangedPCToUser(int pcID, int userID, String date) {
		SingletonHelper.pcBookDB.updateChangedPCToUser(pcID, userID, date);
		return true;
	}
}
