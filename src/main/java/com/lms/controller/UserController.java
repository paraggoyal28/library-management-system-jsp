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
import java.util.UUID;

import com.lms.pojo.Book;
import com.lms.pojo.User;
import com.lms.service.UserService;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		UserService userService = new UserServiceImpl();
		List<User> users = new ArrayList<>();

		if ("showAddUser".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addUser.jsp");
			dispatcher.forward(request, response);
		} else if ("addUser".equalsIgnoreCase(action)) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNo = request.getParameter("phone");
			String address = request.getParameter("address");
			
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPhone_no(phoneNo);
			user.setAddress(address);
			user.setRole("READER");
			
			String randomPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
			user.setPassword(randomPassword);
			user.setCreatedAt(new Date());
			boolean userAdded = userService.addUser(user);

			if (userAdded) {
				users = userService.getAllUserList();
				if (users != null && users.size() > 0) {
					request.setAttribute("userList", users);
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Something went wrong");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addUser.jsp");
					dispatcher.forward(request, response);
				}
				
			} else {
				request.setAttribute("errorMessage", "Something went wrong");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addUser.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
		} else if ("allUserList".equalsIgnoreCase(action)) {
			users = userService.getAllUserList();
			if (users != null && users.size() > 0) {
				request.setAttribute("userList", users);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/userList.jsp");
				dispatcher.forward(request, response);
			} 
		} else if ("viewUser".equalsIgnoreCase(action)) {
			long userId = Long.parseLong(request.getParameter("userId"));
			User user = userService.getUserById(userId);

			if (user != null) {
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editUser.jsp");
				dispatcher.forward(request, response);
			} else {
				users = userService.getAllUserList();

				if (users != null && users.size() > 0) {
					request.setAttribute("errorMessage", "User not found!!");
					request.setAttribute("userList", users);
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/userList.jsp");
					dispatcher.forward(request, response);
				} 
			}
		} else if ("updateUser".equalsIgnoreCase(action)) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");

			
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPhone_no(phone);
			user.setAddress(address);
			user.setUserId(Long.parseLong(request.getParameter("userId")));

			boolean userUpdated = userService.updateUser(user);
			
			if (userUpdated) {
				request.setAttribute("user", user);
				request.setAttribute("successMessage", "User updated successfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editUser.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Something went wrong");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editUser.jsp");
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
