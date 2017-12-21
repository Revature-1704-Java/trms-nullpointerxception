package com.revature.dao;

public class EmployeeDAO {
	
	private static EmployeeDAO employeeDAO;
	
	
	private EmployeeDAO() {
		
	}
	
	public static EmployeeDAO getInstance() {
		if(employeeDAO == null) {
			employeeDAO = new EmployeeDAO();
			return employeeDAO;
		}else {
			return employeeDAO;
		}
	}

}
