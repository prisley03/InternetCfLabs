package controller;

import java.util.ArrayList;

import model.database.PCDatabase;
import model.object.PC;

public class PCController {
	private static class SingletonHelper{
		private static final PCController INSTANCE = new PCController();
		private static final PCDatabase pcDB = new PCDatabase();
	}
	
	public static PCController getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public ArrayList<PC> getAllPCData(){
		return SingletonHelper.pcDB.getAllData();
	}
}
