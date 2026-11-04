package com.lms.pojo;

import java.util.Date;
import java.time.LocalDate;


public class BookIssued {
	
	private int issueId;
	private Book book;
	private User user;
	private Date issueDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private String status;
	private String bookCondition;
	private String assignmentNotes;
	private String returnNotes;
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBookCondition() {
		return bookCondition;
	}
	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}
	public String getAssignmentNotes() {
		return assignmentNotes;
	}
	public void setAssignmentNotes(String assignmentNotes) {
		this.assignmentNotes = assignmentNotes;
	}
	public String getReturnNotes() {
		return returnNotes;
	}
	public void setReturnNotes(String returnNotes) {
		this.returnNotes = returnNotes;
	}
	
	
	
}