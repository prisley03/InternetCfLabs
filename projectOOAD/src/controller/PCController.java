package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.database.PCDatabase;
import model.database.TransactionDetailDatabase;
import model.object.PC;
import model.object.PCBook;
import model.object.TransactionDetail;
import utility.StringUtility;
import view.InsertPCPage.InsertPCObj;

public class PCController {
	private static class SingletonHelper{
		private static final PCController INSTANCE = new PCController();
		private static final PCDatabase pcDB = new PCDatabase();
		private static final PCBookController PCBookController = new PCBookController();
	}
	
	public static PCController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public ArrayList<PC> getAllPCData(){
		return SingletonHelper.pcDB.getAllData();
	}
	
	public ArrayList<PC> getPCDetailByDateAndId(int id, String bookingDate){
		return SingletonHelper.pcDB.getPCDataByDateAndId(id, bookingDate);
	}

	public PC getPCDetail(int id) {
		return SingletonHelper.pcDB.selectById(id);
	}
	
	// Update PC Condition 
	public boolean updatePCCondition(int pcID, String pcCondition) {
		SingletonHelper.pcDB.update(pcID, pcCondition);
		return true;
		
	}
	
	// Check if the PC with PC_ID passed is booked by customer
	public boolean checkOnGoingBookByPCID(int pcID) {
		ArrayList<PCBook> pcBookList =  SingletonHelper.PCBookController.getAllPCBookedData();
		
		for(PCBook pcBookedList : pcBookList) {
			if(pcBookedList.getPcId() == pcID) {
				return true;
			}
		}
		return false;
	} 
	
	// Delete PC by its ID
	public boolean deletePCByID(int pcID) {
		SingletonHelper.pcDB.delete(pcID);
		return true;
	}
	
	public boolean validateInsertPC(InsertPCObj obj) {
		
		String pcID = obj.pcIDField.getText();
		if(pcID.isEmpty()) {
			obj.errorMessage.setText("PC ID must be filled !");
			return false;
		}else if(!StringUtility.isNumeric(pcID)) {
			obj.errorMessage.setText("PC ID must be integer(number)");
			return false;
		}else if(SingletonHelper.pcDB.selectById(Integer.parseInt(pcID)) != null) {
			obj.errorMessage.setText("PC already exist");
			return false;
		}
		
		return true;
	}
	
	public boolean addNewPC(int pcID, String pcCondition) {
		PC newPC = new PC(pcID, pcCondition);
		SingletonHelper.pcDB.insert(newPC);
		return true;
	}
	
}
