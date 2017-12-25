package com.revature.dao;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.bean.Employee;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAOTest {
	
	private EmployeeDAO employeeDAO;
	private ReimbursementDAO reimbursementDAO;
	
	@Before
	public void setUp() {
		employeeDAO = EmployeeDAO.getInstance();
		reimbursementDAO = ReimbursementDAO.getInstance();
	}
	
	@After
	public void cleanUp() {
		String employeeSQL = "DELETE FROM employee";
		String rolesSQL = "DELETE FROM employeerole";
		String approvalProcessSQL = "DELETE FROM approvalprocess";
		String reimbursementLocationSQL = "DELETE FROM reimbursementlocation";
		String reimbursementSQL = "DELETE FROM reimbursement";
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		
		Statement s = null;
		try (Connection conn = connectionUtil.getConnection()){
			
			s = conn.createStatement();
			s.execute(rolesSQL);
			s.close();
			s = conn.createStatement();
			s.execute(reimbursementSQL);
			s.close();
			s = conn.createStatement();
			s.execute(approvalProcessSQL);
			s.close();
			s = conn.createStatement();
			s.execute(reimbursementLocationSQL);
			s.close();
			s = conn.createStatement();
			s.execute(employeeSQL);
			s.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void createReimbursement() {
		employeeDAO.create("email", "password", "f", "s", null);
		Employee e = employeeDAO.getEmployee("email", "password");
		assertNotEquals(0, reimbursementDAO.create(e, "test", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa"));
	}

}
