package com.revature.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.bean.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOTest {
	
	private EmployeeDAO employeeDAO;
	
	@Before
	public void setUp() {
		employeeDAO = EmployeeDAO.getInstance();
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
	public void insertEmployee() {
		assertTrue(employeeDAO.create("Test", "TestPassword", "Steven", "Sagun", null, 1));
	}
	
	@Test
	public void getEmployee() {
		employeeDAO.create("Test1", "TestPassword", "Steven", "Sagun", null, 1);
		Employee e = employeeDAO.getEmployee("Test1", "TestPassword");
		assertNotEquals(0, e.getEmployeeId());
	}
	
	@Test
	public void getNonExistentEmployee() {
		Employee e = employeeDAO.getEmployee("none", "none");
		assertEquals(0,e.getEmployeeId());
	}
	
	@Test 
	public void insertRoles() {
		employeeDAO.create("Test2", "TestPassword", "Steven", "Sagun", null, 1);
		Employee e = employeeDAO.getEmployee("Test2", "TestPassword");
		assertEquals(2, employeeDAO.setRoles(e, "Employee", "Supervisor"));
	}
	
	@Test
	public void getRolesFromEmployee() {
		employeeDAO.create("Test3", "TestPassword", "Steven", "Sagun", null, 1);
		Employee e = employeeDAO.getEmployee("Test3", "TestPassword");
		employeeDAO.setRoles(e, "Employee", "Supervisor");
		String[] arr = {"Employee", "Supervisor"};
		ArrayList<String> a = (ArrayList<String>)employeeDAO.getAllRolesForEmployee(e);
		for(String s : arr) {
			assertTrue(a.contains(s));
		}
	}

}
