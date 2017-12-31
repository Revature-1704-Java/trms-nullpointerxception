package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradeFormat;
import com.revature.beans.Reimbursement;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.ReimbursementDAO;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EmployeeDAO employeeDAO;
	private static ReimbursementDAO reimbursementDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        employeeDAO = EmployeeDAO.getInstance();
        reimbursementDAO = ReimbursementDAO.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("employee") == null) {
			response.sendRedirect("login");
		}else {
			Employee e = (Employee) request.getSession().getAttribute("employee");
			List<Reimbursement> reimbursements = reimbursementDAO.getAllByEmployee(e);
			List<EventType> eventTypes = reimbursementDAO.getEventTypes();
			List<GradeFormat> gradeFormats = reimbursementDAO.getGradeFormats();
			request.setAttribute("reimbursements", reimbursements);
			request.setAttribute("eventTypes", eventTypes);
			request.setAttribute("gradeFormats", gradeFormats);
			RequestDispatcher rd = request.getRequestDispatcher("views/dashboard.jsp");
			rd.forward(request, response);
		}
		System.out.println(request.getSession().getId());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
