package controller;

import java.util.ArrayList;

import model.database.TransactionDetailDatabase;
import model.object.TransactionDetail;

public class TransactionController {
	private static class SingletonHelper{
		private static final TransactionController INSTANCE = new TransactionController();
		private static final TransactionDetailDatabase transactionDetailDB = new TransactionDetailDatabase();
	}
	
	public static TransactionController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public ArrayList<TransactionDetail> getUserTransactionDetail(int userId){
		return SingletonHelper.transactionDetailDB.getUserTransactionDetail(userId);
	}
}
