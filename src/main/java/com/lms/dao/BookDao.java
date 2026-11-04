package com.lms.dao;

import com.lms.pojo.Book;
import com.lms.pojo.BookIssued;

import java.util.List;

public interface BookDao {

	public boolean addBook(Book book);
	
	public boolean updateBook(Book book);
	
	public List<Book> getAllBookList();
	
	public List<Book> getAllBookByStatus(String status);	
	
	public Book getBookById(long bookId);
	
	public List<Book> getAllAvailableBookList();	

	public boolean assignBook(BookIssued bookIssued);
	
	public boolean updateAvailableBook(long bookId, int availableCopies);
	
	public List<BookIssued> getAllIssuedBooks();
	
	public BookIssued getIssuedBookById(long issueId);
	
	public boolean updateBookReturn(BookIssued bookIssued);
}
