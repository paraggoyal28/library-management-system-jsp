package com.lms.dao;

import com.lms.pojo.Book;
import java.util.List;

public interface BookDao {

	public boolean addBook(Book book);
	
	public boolean updateBook(Book book);
	
	public List<Book> getAllBookList();
	
	public List<Book> getAllBookByStatus(String status);	
	
	public Book getBookById(long bookId);
}
