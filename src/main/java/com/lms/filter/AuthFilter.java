package com.lms.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebFilter("/*")
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String url = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();
		HttpSession session = (HttpSession) httpRequest.getSession();
		
		boolean loggedIn = (session != null && session.getAttribute("user") != null);
		boolean allowedUrls = url.equals(contextPath + "/") || 
                url.equals(contextPath + "/AuthenticationController") || 
                url.equals(contextPath + "jsp/login.jsp");
		
		
		if (loggedIn || allowedUrls) {
			filterChain.doFilter(request, response);
			
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
