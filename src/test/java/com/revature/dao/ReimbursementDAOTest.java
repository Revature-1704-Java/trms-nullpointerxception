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
	
//	@After
//	public void cleanUp() {
//		String employeeSQL = "DELETE FROM employee";
//		String rolesSQL = "DELETE FROM employeerole";
//		String approvalProcessSQL = "DELETE FROM approvalprocess";
//		String reimbursementLocationSQL = "DELETE FROM reimbursementlocation";
//		String reimbursementSQL = "DELETE FROM reimbursement";
//		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
//		
//		Statement s = null;
//		try (Connection conn = connectionUtil.getConnection()){
//			
//			s = conn.createStatement();
//			s.execute(rolesSQL);
//			s.close();
//			s = conn.createStatement();
//			s.execute(reimbursementSQL);
//			s.close();
//			s = conn.createStatement();
//			s.execute(approvalProcessSQL);
//			s.close();
//			s = conn.createStatement();
//			s.execute(reimbursementLocationSQL);
//			s.close();
//			s = conn.createStatement();
//			s.execute(employeeSQL);
//			s.close();
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
	
//	@Test
//	public void createReimbursement() {
//		employeeDAO.create("email@email.com", "password", "f", "s", null, 1);
//		Employee e = employeeDAO.getEmployee("email@email.com", "password");
//		assertNotEquals(0, reimbursementDAO.create(e, "test", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa"));
//	}
//	
//	
//	
//	@Test
//	public void viewAllReimbursements() {
//		employeeDAO.create("test@email.com", "password", "Steven", "Sagun", null, 1);
//		Employee e = employeeDAO.getEmployee("test@email.com", "password");
//		reimbursementDAO.create(e, "reimbursement1", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address1", "city", "23", "usa");
//		reimbursementDAO.create(e, "reimbursement2", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address2", "city", "23", "usa");
//		ArrayList<Reimbursement> list = (ArrayList<Reimbursement>) reimbursementDAO.getAllByEmployee(e);
//		assertTrue(list.get(0).getDescription().equals("reimbursement1"));
//		assertTrue(list.get(1).getDescription().equals("reimbursement2"));
//		assertTrue(list.get(0).getAddress().equals("address1"));
//		assertTrue(list.get(1).getAddress().equals("address2"));
//
//	}
//	
//	@Test
//	public void viewOneReimbursement() {
//		employeeDAO.create("email2@email.com", "password", "Test", "test", null, 1);
//		Employee e = employeeDAO.getEmployee("email2@email.com", "password");
//		int i = reimbursementDAO.create(e, "Apples", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
//		Reimbursement reimbursement = reimbursementDAO.getById(i);
//		assertEquals("Apples", reimbursement.getDescription());
//		assertNull(reimbursementDAO.getById(0));
//	}
	
//	@Test
//	public void viewUnderlingReimbursement() {
//		employeeDAO.create("email3@email.com", "password", "Test", "test", null, 1);
//		Employee e = employeeDAO.getEmployee("email3@email.com", "password");
//		String[] roles = {"Employee", "Supervisor"};
//		employeeDAO.setRoles(e, roles);
//		employeeDAO.create("email4@email.com", "password", "Test", "test", e.getEmployeeId(), 1);
//		Employee e2 = employeeDAO.getEmployee("email4@email.com", "password");
//		String[] e2roles = {"Employee"};
//		employeeDAO.setRoles(e2, e2roles);
//		reimbursementDAO.create(e2, "Apples", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
//		reimbursementDAO.create(e2, "Banana", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
//		List<EmployeeReimbursement> list = reimbursementDAO.getAllReimbursementsFromUnderlings(e.getEmployeeId());
//		assertEquals(e2.getEmployeeId(), list.get(0).getEmployee().getEmployeeId());
//		assertEquals("Apples", list.get(0).getReimbursement().getDescription());
//		assertEquals(e2.getEmployeeId(), list.get(1).getEmployee().getEmployeeId());
//		assertEquals("Banana", list.get(1).getReimbursement().getDescription());
//	}
	
//	@Test
//	public void viewAllFromDepartment() {
//		employeeDAO.create("email@email.com", "password", "Department", "Head", null, 1);
//		Employee head = employeeDAO.getEmployee("email@email.com", "password");
//		String[] roles = {"Employee", "Department Head"};
//		employeeDAO.setRoles(head, roles);
//		employeeDAO.create("email2@email.com", "password", "Super", "Visor", head.getEmployeeId(), 1);
//		Employee supervisor = employeeDAO.getEmployee("email2@email.com", "password");
//		String [] roles1 = {"Employee", "Supervisor"};
//		employeeDAO.setRoles(supervisor, roles1);
//		employeeDAO.create("email3@email.com", "password", "Employee", "1", supervisor.getEmployeeId(), 1);
//		Employee e = employeeDAO.getEmployee("email3@email.com", "password");
//		String [] roles2 = {"Employee", "Supervisor"};
//		employeeDAO.setRoles(e, roles2);
//		int i = reimbursementDAO.create(e, "Apples", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
//		reimbursementDAO.create(e, "Banana", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
//		employeeDAO.create("email4@email.com", "password", "Employee", "2", supervisor.getEmployeeId(), 1);
//		e = employeeDAO.getEmployee("email4@email.com", "password");
//		String [] roles3 = {"Employee", "Supervisor"};
//		employeeDAO.setRoles(e, roles3);
//		reimbursementDAO.create(e, "Red", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
//		int j = reimbursementDAO.create(e, "Blue", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
//		reimbursementDAO.updateStatus(i, supervisor.getEmployeeId(), "APPROVED", null,"supervisor");
//		reimbursementDAO.updateStatus(j, supervisor.getEmployeeId(), "APPROVED", null,"supervisor");
//		List<EmployeeReimbursement> list = reimbursementDAO.getAllReimbursementsFromDepartment(head.getDepartmentId());
//		assertEquals("Apples",list.get(0).getReimbursement().getDescription());
//		assertEquals("Blue",list.get(1).getReimbursement().getDescription());
//	}
	
	@Test
	public void viewAllForBenco() {
		employeeDAO.create("email@email.com", "password", "Department", "Head", null, 1);
		Employee head = employeeDAO.getEmployee("email@email.com", "password");
		String[] roles = {"Employee", "Department Head"};
		employeeDAO.setRoles(head, roles);
		employeeDAO.create("email2@email.com", "password", "Super", "Visor", head.getEmployeeId(), 1);
		Employee supervisor = employeeDAO.getEmployee("email2@email.com", "password");
		String [] roles1 = {"Employee", "Supervisor"};
		employeeDAO.setRoles(supervisor, roles1);
		employeeDAO.create("email3@email.com", "password", "Employee", "1", supervisor.getEmployeeId(), 1);
		Employee e = employeeDAO.getEmployee("email3@email.com", "password");
		String [] roles2 = {"Employee", "Supervisor"};
		employeeDAO.setRoles(e, roles2);
		int i = reimbursementDAO.create(e, "Apples", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
		reimbursementDAO.create(e, "Banana", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
		employeeDAO.create("email4@email.com", "password", "Employee", "2", supervisor.getEmployeeId(), 1);
		e = employeeDAO.getEmployee("email4@email.com", "password");
		String [] roles3 = {"Employee", "Supervisor"};
		employeeDAO.setRoles(e, roles3);
		employeeDAO.create("email5@email.com", "password", "Ben", "Co", head.getEmployeeId(), 1);
		Employee benCo = employeeDAO.getEmployee("email5@email.com", "password");
		String [] roles4 = {"Employee", "Benefits Coordinator"};
		employeeDAO.setRoles(benCo, roles4);
		reimbursementDAO.create(e, "Red", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
		int j = reimbursementDAO.create(e, "Blue", 15, "PASS/FAIL", "Seminar", "For work", null, null, 24, new Date(new java.util.Date().getTime()), "address", "city", "23", "usa");
		reimbursementDAO.updateStatus(j, supervisor.getEmployeeId(), "APPROVED", null,"supervisor");
		reimbursementDAO.updateStatus(j, head.getEmployeeId(), "APPROVED", null,"departmentHead");
		List<EmployeeReimbursement> list = reimbursementDAO.getAllReimbursementsForBenCo();
		assertEquals("Blue",list.get(0).getReimbursement().getDescription());
	}

}
