package model.object;

import java.util.Date;

public class PCBook {
	private int bookId;
	private int pcId;
	private int userId;
	private Date bookedDate;
	public PCBook(int bookId, int pcId, int userId, Date bookedDate) {
		super();
		this.bookId = bookId;
		this.pcId = pcId;
		this.userId = userId;
		this.bookedDate = bookedDate;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getPcId() {
		return pcId;
	}
	public void setPcId(int pcId) {
		this.pcId = pcId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}
	
	

}
