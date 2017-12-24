package com.revature.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		
		Statement s = null;
		try (Connection conn = connectionUtil.getConnection()){
			s = conn.createStatement();
			s.execute(rolesSQL);
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
		assertTrue(employeeDAO.create("Test", "TestPassword", "Steven", "Sagun", null));
	}
	
	@Test
	public void getEmployee() {
		employeeDAO.create("Test", "TestPassword", "Steven", "Sagun", null);
		Employee e = employeeDAO.getEmployee("Test", "TestPassword");
		assertNotNull(e);
	}
	
	@Test
	public void getNonExistentEmployee() {
		Employee e = employeeDAO.getEmployee("none", "none");
		assertNotNull(e);
	}
	
	@Test 
	public void insertRoles() {
		employeeDAO.create("Test", "TestPassword", "Steven", "Sagun", null);
		Employee e = employeeDAO.getEmployee("Test", "TestPassword");
		assertEquals(2, employeeDAO.setRoles(e, "Employee", "Supervisor"));
	}

}
