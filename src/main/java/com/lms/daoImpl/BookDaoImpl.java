package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.BookDao;
import com.lms.pojo.Book;
import com.lms.pojo.BookIssued;
import com.lms.util.DbUtil;

public class BookDaoImpl implements BookDao {

	@Override
	public boolean addBook(Book book) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String sql = "Insert INTO books(title, author, category, isbn, publisher, total_copies, available_copies, created_at, status) " + 
					"VALUES(?,?,?,?,?,?,?,?,?)";
			
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getCategory());
			preparedStatement.setString(4, book.getIsbn());
			preparedStatement.setString(5,  book.getPublisher());
			preparedStatement.setInt(6, book.getTotalCopies());
			preparedStatement.setInt(7, book.getAvailableCopies());
			preparedStatement.setDate(8, new Date(book.getCreatedAt().getTime()));
			preparedStatement.setString(9, book.getStatus());
			
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String sql = "UPDATE books set title = ?, author = ?, category = ?, isbn = ?, publisher = ?, total_copies = ? " + 
					"WHERE book_id = ?";
			
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getCategory());
			preparedStatement.setString(4, book.getIsbn());
			preparedStatement.setString(5,  book.getPublisher());
			preparedStatement.setInt(6, book.getTotalCopies());
			preparedStatement.setLong(7, book.getBookId());
			
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public List<Book> getAllBookList() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<>();
		
		try {
			String sql = "Select * from books order by book_id desc";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setPublisher(rs.getString("publisher"));
				book.setTotalCopies(rs.getInt("total_copies"));
				book.setAvailableCopies(rs.getInt("available_copies"));
				book.setStatus(rs.getString("status"));
				bookList.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return bookList;
	}

	@Override
	public List<Book> getAllBookByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookById(long bookId) {
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "Select * from books where book_id = ?";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, bookId);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setPublisher(rs.getString("publisher"));
				book.setTotalCopies(rs.getInt("total_copies"));
				book.setAvailableCopies(rs.getInt("available_copies"));
				book.setStatus(rs.getString("status"));
				
				return book;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public boolean assignBook(BookIssued bookIssued) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String sql = "Insert INTO book_issued(book_id, user_id, issue_date, due_date, status, assignment_notes) " + 
					"VALUES(?,?,?,?,?,?)";
			
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, bookIssued.getBook().getBookId());
			preparedStatement.setLong(2, bookIssued.getUser().getUserId());
			preparedStatement.setDate(3, new Date(bookIssued.getIssueDate().getTime()));
			preparedStatement.setDate(4, Date.valueOf(bookIssued.getDueDate()));
			preparedStatement.setString(5, bookIssued.getStatus());
			preparedStatement.setString(6, bookIssued.getAssignmentNotes());
			
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean updateAvailableBook(long bookId, int availableCopies) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String sql = "UPDATE books set available_copies = ? WHERE book_id = ?";
			
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, availableCopies);
			preparedStatement.setLong(2, bookId);

			
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
			
			
	}

	@Override
	public List<Book> getAllAvailableBookList() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<>();
		
		try {
			String sql = "Select * from books where available_copies > 0";
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setPublisher(rs.getString("publisher"));
				book.setTotalCopies(rs.getInt("total_copies"));
				book.setAvailableCopies(rs.getInt("available_copies"));
				book.setStatus(rs.getString("status"));
				bookList.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return bookList;
	}

	@Override
	public List<BookIssued> getAllIssuedBooks() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<BookIssued> issuedList = new ArrayList<>();
		
		try {
		//	String sql = "SELECT bi.issue_id, bi.book_id, bi.user_id, bi.issue_date, bi.due_date, "
		//			+ "bi.return_date, bi.status, bi.book_condition"
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return issuedList;
	}

	@Override
	public BookIssued getIssuedBookById(long issueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBookReturn(BookIssued bookIssued) {
		// TODO Auto-generated method stub
		return false;
	}

}
