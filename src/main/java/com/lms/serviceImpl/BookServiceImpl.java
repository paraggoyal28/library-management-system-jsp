package com.lms.serviceImpl;

import java.util.Date;
import java.util.List;

import com.lms.daoImpl.BookDaoImpl;
import com.lms.dao.BookDao;
import com.lms.pojo.Book;
import com.lms.pojo.BookIssued;
import com.lms.service.BookService;

public class BookServiceImpl implements BookService {

	BookDao bookDao = new BookDaoImpl();
	
	@Override
	public boolean addBook(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public boolean updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public List<Book> getAllBookList() {
		return bookDao.getAllBookList();
	}

	@Override
	public List<Book> getAllBookByStatus(String status) {
		return null;
	}

	@Override
	public Book getBookById(long bookId) {
		return bookDao.getBookById(bookId);
	}

	@Override
	public boolean assignBook(BookIssued bookIssued) {
		bookIssued.setIssueDate(new Date());
		bookIssued.setStatus("ISSUED");
		
		boolean assignFlag = false;
		Book book = bookDao.getBookById(bookIssued.getBook().getBookId());
		if (book != null) {
			int availableCopies = book.getAvailableCopies() - 1;
			
			boolean updatedFlag = bookDao.updateAvailableBook(bookIssued.getBook().getBookId(), availableCopies);
			
			if (updatedFlag) {
				assignFlag = bookDao.assignBook(bookIssued);
				
				if (!assignFlag) {
					Book book1 = bookDao.getBookById(bookIssued.getBook().getBookId());
					int newAvailableCopies = book1.getAvailableCopies() + 1;
					bookDao.updateAvailableBook(bookIssued.getBook().getBookId(), newAvailableCopies);
				}
			}
		}
		return assignFlag;
	}

	@Override
	public boolean updateAvailableBook(long bookId, int availableCopies) {
		return bookDao.updateAvailableBook(bookId, availableCopies);
	}

	@Override
	public List<Book> getAllAvailableBookList() {
		return bookDao.getAllAvailableBookList();
	}

	@Override
	public List<BookIssued> getAllIssuedBookList() {
		return bookDao.getAllIssuedBookList();
	}

	@Override
	public BookIssued getIssuedBookById(long issueId) {
		return bookDao.getIssuedBookById(issueId);
	}

	@Override
	public boolean updateBookReturn(BookIssued bookIssued) {
		bookIssued.setStatus("RETURN");
		boolean returnFlag = false;
		BookIssued bookIssued1 = bookDao.getIssuedBookById(bookIssued.getIssueId());
		if (bookIssued1 != null) {
			int availableCopies = bookIssued1.getBook().getAvailableCopies() + 1;
			boolean updateFlag = bookDao.updateAvailableBook(bookIssued1.getBook().getBookId(), availableCopies);
			if (updateFlag) {
				returnFlag =  bookDao.updateBookReturn(bookIssued);
				
				if (!returnFlag) {
					
					BookIssued bookIssued2 = bookDao.getIssuedBookById(bookIssued.getIssueId());
					int availableCopies1 = bookIssued2.getBook().getAvailableCopies() - 1;
					bookDao.updateAvailableBook(bookIssued2.getBook().getBookId(), availableCopies1);
				}
			}
		}
		
		return returnFlag;
	}
	
}
