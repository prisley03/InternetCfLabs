package controller;

import java.util.ArrayList;

import model.database.TransactionDetailDatabase;
import model.database.TransactionHeaderDatabase;
import model.object.TransactionDetail;
import model.object.TransactionHeader;

public class TransactionController {
	private static class SingletonHelper{
		private static final TransactionController INSTANCE = new TransactionController();
		private static final TransactionDetailDatabase transactionDetailDB = new TransactionDetailDatabase();
		private static final TransactionHeaderDatabase transactionHeaderDB = new TransactionHeaderDatabase();
	}
	
	public static TransactionController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public ArrayList<TransactionDetail> getUserTransactionDetail(int userId){
		return SingletonHelper.transactionDetailDB.getUserTransactionDetail(userId);
	}
	
	public ArrayList<TransactionHeader> getAllTransactionHeader(){
		return SingletonHelper.transactionHeaderDB.getAllTransactionHeader();
	}
	
	public ArrayList<TransactionDetail> getAllTransactionDetail(){
		return SingletonHelper.transactionDetailDB.getAllTransactionDetail();
	}
	
	public  ArrayList<TransactionDetail> getTransactionDetailByTransactionID(int transactionID){
		return SingletonHelper.transactionDetailDB.getTransactionDetailByTransactionID(transactionID);
	}
	
	public void addNewTransactionHeader(int id, String name, String date) {
		SingletonHelper.transactionHeaderDB.addNewTransactionHeader(id, name, date);
	}
	
	public void addNewTransactionDetail(int pcID, String custName, String date) {
		SingletonHelper.transactionDetailDB.addNewTransactionDetail(pcID, custName, date);
	}
	

}
