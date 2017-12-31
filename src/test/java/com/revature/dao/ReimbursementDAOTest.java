package com.revature.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.beans.Employee;
import com.revature.beans.EmployeeReimbursement;
import com.revature.beans.Reimbursement;
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
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Test
	public void createReimbursement() {
		employeeDAO.create("email@email.com", "password", "f", "s", null, 1);
		Employee e = employeeDAO.getEmployee("email@email.com", "password");
		assertNotEquals(0, reimbursementDAO.create(e, "test", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa"));
	}
	
	@Test
	public void viewAllReimbursements() {
		employeeDAO.create("test@email.com", "password", "Steven", "Sagun", null, 1);
		Employee e = employeeDAO.getEmployee("test@email.com", "password");
		reimbursementDAO.create(e, "reimbursement1", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address1", "city", "23", "usa");
		reimbursementDAO.create(e, "reimbursement2", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address2", "city", "23", "usa");
		ArrayList<Reimbursement> list = (ArrayList<Reimbursement>) reimbursementDAO.getAllByEmployee(e);
		assertTrue(list.get(0).getDescription().equals("reimbursement1"));
		assertTrue(list.get(1).getDescription().equals("reimbursement2"));
		assertTrue(list.get(0).getAddress().equals("address1"));
		assertTrue(list.get(1).getAddress().equals("address2"));

	}
	
	@Test
	public void viewOneReimbursement() {
		employeeDAO.create("email2@email.com", "password", "Test", "test", null, 1);
		Employee e = employeeDAO.getEmployee("email2@email.com", "password");
		int i = reimbursementDAO.create(e, "Apples", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
		Reimbursement reimbursement = reimbursementDAO.getById(i);
		assertEquals("Apples", reimbursement.getDescription());
		assertNull(reimbursementDAO.getById(0));
	}
	
	@Test
	public void viewUnderlingReimbursement() {
		employeeDAO.create("email3@email.com", "password", "Test", "test", null, 1);
		Employee e = employeeDAO.getEmployee("email3@email.com", "password");
		employeeDAO.create("email4@email.com", "password", "Test", "test", e.getEmployeeId(), 1);
		Employee e2 = employeeDAO.getEmployee("email4@email.com", "password");
		reimbursementDAO.create(e2, "Apples", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
		reimbursementDAO.create(e2, "Banana", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
		List<EmployeeReimbursement> list = reimbursementDAO.getAllReimbursementsFromUnderlings(e.getEmployeeId());
		assertEquals(e2.getEmployeeId(), list.get(0).getEmployee().getEmployeeId());
		assertEquals("Apples", list.get(0).getReimbursement().getDescription());
		assertEquals(e2.getEmployeeId(), list.get(1).getEmployee().getEmployeeId());
		assertEquals("Banana", list.get(1).getReimbursement().getDescription());
	}

}
