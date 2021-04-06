package com.cognixia.jump.model;

public class BookCheckout {

	private int checkout_id;
	private int patron_id;
	private String isbn;
	private String checkoutdate;
	private String duedate;
	private String returned;
	public int getCheckout_id() {
		return checkout_id;
	}
	public void setCheckout_id(int checkout_id) {
		this.checkout_id = checkout_id;
	}
	public int getPatron_id() {
		return patron_id;
	}
	public void setPatron_id(int patron_id) {
		this.patron_id = patron_id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCheckoutdate() {
		return checkoutdate;
	}
	public void setCheckoutdate(String checkoutdate) {
		this.checkoutdate = checkoutdate;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getReturned() {
		return returned;
	}
	public void setReturned(String returned) {
		this.returned = returned;
	}
	@Override
	public String toString() {
		return "BookCheckout [checkout_id=" + checkout_id + ", patron_id=" + patron_id + ", isbn=" + isbn
				+ ", checkoutdate=" + checkoutdate + ", duedate=" + duedate + ", returned=" + returned + "]";
	}
	public BookCheckout(int checkout_id, int patron_id, String isbn, String checkoutdate, String duedate,
			String returned) {
		super();
		this.checkout_id = checkout_id;
		this.patron_id = patron_id;
		this.isbn = isbn;
		this.checkoutdate = checkoutdate;
		this.duedate = duedate;
		this.returned = returned;
	}
	
	
	
	
}
