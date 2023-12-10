package model;

import java.time.LocalDateTime;

public class TransactionDetail {
	private int transactionId;
	private int pcId;
	private String customerName;
	private LocalDateTime bookedTime;
	public TransactionDetail(int transactionId, int pcId, String customerName, LocalDateTime bookedTime) {
		super();
		this.transactionId = transactionId;
		this.pcId = pcId;
		this.customerName = customerName;
		this.bookedTime = bookedTime;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getPcId() {
		return pcId;
	}
	public void setPcId(int pcId) {
		this.pcId = pcId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public LocalDateTime getBookedTime() {
		return bookedTime;
	}
	public void setBookedTime(LocalDateTime bookedTime) {
		this.bookedTime = bookedTime;
	}
}
