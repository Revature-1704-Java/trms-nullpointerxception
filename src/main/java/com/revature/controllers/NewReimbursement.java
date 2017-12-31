package com.revature.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDAO;

/**
 * Servlet implementation class NewReimbursement
 */
@WebServlet("/newreimbursement")
@MultipartConfig
public class NewReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> p = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : p.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(Arrays.toString(entry.getValue()));
		}
		
		ReimbursementDAO reimbursementDAO = ReimbursementDAO.getInstance();
		Employee employee = (Employee) request.getSession().getAttribute("employee");
	
		double cost = Double.parseDouble(request.getParameter("cost"));
		Integer timeMissed;
		try{
			timeMissed = Integer.parseInt(request.getParameter("timeMissed"));
		} catch (NumberFormatException e) {
			timeMissed = 0;
		}
		String[] date = request.getParameter("startDate").split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		Date startDate = new Date(calendar.getTimeInMillis());
		
		//attachment
		byte[] attachment = request.getPart("attachment").getInputStream().readAllBytes();
		
		//approval document
		byte[] approvalDocument = request.getPart("approvalDocument").getInputStream().readAllBytes();		
		
		int pk = reimbursementDAO.create(employee, request.getParameter("description"), cost, request.getParameter("gradeFormat"), request.getParameter("eventType"), request.getParameter("workJustification"), attachment, approvalDocument, timeMissed, startDate, request.getParameter("address"), request.getParameter("city"), request.getParameter("zip"), request.getParameter("country"));
		
	}

}
