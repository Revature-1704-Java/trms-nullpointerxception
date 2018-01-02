package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestUrl = request.getRequestURI().substring(request.getContextPath().length());
		
		if(requestUrl.equals("/dashboad")) {
			if(request.getSession().getAttribute("employee") == null) {
				response.sendRedirect("login");
			}else {
				response.sendRedirect("dashboard");
			}
		}else if(requestUrl.equals("/login")) {
			if(request.getSession().getAttribute("employee") == null) {
				response.sendRedirect("login");
			}else {
				response.sendRedirect("dashboard");
			}
		}else if(requestUrl.equals("/")) {
			if(request.getSession().getAttribute("employee") == null) {
				response.sendRedirect("login");
			}else {
				response.sendRedirect("dashboard");
			}
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
