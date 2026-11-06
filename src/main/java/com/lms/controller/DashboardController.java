package com.lms.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.lms.pojo.BookIssued;
import com.lms.pojo.DashboardStats;
import com.lms.service.BookService;
import com.lms.service.DashboardService;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.serviceImpl.DashboardServiceImpl;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/DashboardController")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		DashboardService dashboardService = new DashboardServiceImpl();
		BookService bookService = new BookServiceImpl();
		
		if ("viewDashboard".equalsIgnoreCase(action)) {
			
			DashboardStats dashboardStats = dashboardService.fetchDashboardStats();
			List<BookIssued> issuedList = bookService.getAllIssuedBookListForDashboard();
						
			if (issuedList != null && issuedList.size() > 0) {
				LocalDate today = LocalDate.now();
				for (BookIssued bookIssued: issuedList) {
					LocalDate dueDate = bookIssued.getDueDate();
					
					if (dueDate.isBefore(today)) {
						bookIssued.setDueDateStatus("Overdue");
					} else if (dueDate.isEqual(today)) {
						bookIssued.setDueDateStatus("Due Today");
					} else {
						bookIssued.setDueDateStatus("Active");
					}
				}
			}
			
			request.setAttribute("issuedList", issuedList);
			request.setAttribute("dashboardStats", dashboardStats);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/dashboard.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/error.jsp");
			dispatcher.forward(request, response);
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
