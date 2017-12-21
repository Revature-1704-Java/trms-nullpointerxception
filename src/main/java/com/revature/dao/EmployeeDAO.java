package com.revature.dao;

public class EmployeeDAO {
	
	private EmployeeDAO employeeDAO;
	
	
	private EmployeeDAO() {
		
	}
	
	public EmployeeDAO getInstance() {
		if(employeeDAO == null) {
			employeeDAO = new EmployeeDAO();
			return employeeDAO;
		}else {
			return employeeDAO;
		}
	}

}
