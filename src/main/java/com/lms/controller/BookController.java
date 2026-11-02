package com.lms.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lms.pojo.Book;
import com.lms.pojo.User;
import com.lms.service.UserService;
import com.lms.service.BookService;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		BookService bookService = new BookServiceImpl();
		List<Book> books = new ArrayList<>();

		if ("showAddBook".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
			dispatcher.forward(request, response);
		}
		else if ("addBook".equalsIgnoreCase(action)) {
			
			String title = request.getParameter("bookTitle");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			String category = request.getParameter("category");
			String publisher = request.getParameter("publisher");
			String availableCopies = request.getParameter("availableCopies"); 
			String totalCopies = request.getParameter("totalCopies"); 
			
			Book book = new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setIsbn(isbn);
			book.setCategory(category);
			book.setPublisher(publisher);
			book.setTotalCopies(Integer.parseInt(totalCopies));
			book.setAvailableCopies(Integer.parseInt(availableCopies));
			book.setCreatedAt(new Date());
			book.setStatus("AVAILABLE");
			
			boolean bookAdded = bookService.addBook(book);
			
			if (bookAdded) {
				
				books = bookService.getAllBookList();
				
				if (books != null && books.size() > 0) {
					request.setAttribute("successMessage", "Book added successfully!!");
					request.setAttribute("bookList", books);
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Something went wrong.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
					dispatcher.forward(request, response);
				}
				
			} else {
				request.setAttribute("errorMessage", "Something went wrong");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
				dispatcher.forward(request, response);
			}
		} 
		else if ("allBookList".equalsIgnoreCase(action)) {
			books = bookService.getAllBookList();
			if (books != null && books.size() > 0) {
				request.setAttribute("bookList", books);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
				dispatcher.forward(request, response);
			}
		
		} 
		else if ("viewBook".equalsIgnoreCase(action)) {
			long bookId = Long.parseLong(request.getParameter("bookId"));
			Book book = bookService.getBookById(bookId);
			if (book != null) {
				request.setAttribute("book", book);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editBook.jsp");
				dispatcher.forward(request, response);
			} else {
				books = bookService.getAllBookList();
				if (books != null && books.size() > 0) {
					request.setAttribute("bookList", books);
					request.setAttribute("errorMessage", "Book with given id not found");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
		else if ("updateBook".equalsIgnoreCase(action)) {
			String title = request.getParameter("bookTitle");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			String category = request.getParameter("category");
			String publisher = request.getParameter("publisher");
			String totalCopies = request.getParameter("totalCopies"); 
			String availableCopies = request.getParameter("availableCopies"); 

			
			Book book = new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setIsbn(isbn);
			book.setCategory(category);
			book.setPublisher(publisher);
			book.setTotalCopies(Integer.parseInt(totalCopies));
			book.setAvailableCopies(Integer.parseInt(availableCopies));
			book.setBookId(Long.parseLong(request.getParameter("bookId")));

			boolean bookUpdated = bookService.updateBook(book);
			
			if (bookUpdated) {
				request.setAttribute("book", book);
				request.setAttribute("successMessage", "Book updated successfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editBook.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Something went wrong");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editBook.jsp");
				dispatcher.forward(request, response);
			}
		}
		else {
			System.out.print("No action found");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
