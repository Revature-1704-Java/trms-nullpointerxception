package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAO {

	private static EmployeeDAO employeeDAO;

	private EmployeeDAO() {

	}

	/**
	 * Returns the singleton instance of the EmployeeDAO used for communicating with
	 * the database related to the Employee model.
	 * 
	 * @return The singleton instance of the EmployeeDAO
	 */
	public static EmployeeDAO getInstance() {
		if (employeeDAO == null) {
			employeeDAO = new EmployeeDAO();
			return employeeDAO;
		} else {
			return employeeDAO;
		}
	}
	
	/**
	 * Creates a new Employee record in the employee table.
	 * @param email The email for the new employee.
	 * @param password The password for the new employee.
	 * @param firstName The first name of the new employee.
	 * @param lastName The last name of the new employee.
	 * @param reportsTo The id of the employee whom the new employee would directly work under.
	 * @return true if the stored procedure ran successfully. false if it didn't.
	 */
	public boolean create(String email, String password, String firstName, String lastName, Integer reportsTo, Integer departmentId) {

		
		boolean isSuccessful = false;
		
		String sql = "{call sp_insert_employee (?, ?, ?, ?, ?, ?, ?)}";
		
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		
		CallableStatement callableStatement = null;
		try (Connection conn = connectionUtil.getConnection()){
			callableStatement = conn.prepareCall(sql);
			callableStatement.setString(1, email);
			callableStatement.setString(2, password);
			callableStatement.setString(3, firstName);
			callableStatement.setString(4, lastName);
			
			if(reportsTo == null) {
				callableStatement.setNull(5, Types.INTEGER);
			}else {
				callableStatement.setInt(5, reportsTo);
			}
			callableStatement.setInt(6, departmentId);
			
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			
			callableStatement.execute();
			
			isSuccessful = callableStatement.getBoolean(7);
			
			callableStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return isSuccessful;
		
	}

	/**
	 * Find an employee based on an email and password combination.
	 * @param email The email of the employee to find.
	 * @param password The pass word of the employee to find
	 * @return The employee
	 */
	public Employee getEmployee(String email, String password) {
		String sql = "SELECT * FROM employee WHERE email=? AND password=?";
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee em = null;
		try (Connection conn = connectionUtil.getConnection()){
		
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			em = new Employee();
			while(rs.next()) {
				em.setEmployeeId(rs.getInt(1));
				em.setEmail(rs.getString(2));
				em.setFirstName(rs.getString(4));
				em.setLastName(rs.getString(5));
				em.setReportsTo(rs.getInt(6));
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		employeeDAO.getAllRolesForEmployee(em);
		
		return em;
	}
	
	/**
	 * Set the roles for a specific employee. Also updates the employee to have those roles.
	 * @param e The Employee for which the roles will be set for
	 * @param roles Vararg of roles to add to the employee
	 * @return The number of roles added.
	 */
	public int setRoles(Employee e, String ...roles) {
		String sql = "INSERT INTO employeerole (employeeid, employeetypeid) VALUES (?, (SELECT employeetypeid FROM employeetype WHERE employeetype.employeetype=?))";
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		PreparedStatement ps = null;
		int rolesAdded = 0;
		try (Connection conn = connectionUtil.getConnection()){
			
			
			for(String role: roles) {
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, e.getEmployeeId());
				ps.setString(2, role);
				rolesAdded += ps.executeUpdate();
				e.getRoles().add(role);
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		return rolesAdded;
	}
	
	/**
	 * Get all the roles for a particular employee.
	 * @param e The employee for which to get all the roles from.
	 * @return All roles held by the employee in the form of a list of strings.
	 */
	public List<String> getAllRolesForEmployee(Employee e){
		ArrayList<String> roles = new ArrayList<String>();
		String sql  = "SELECT employeetype.employeetype FROM (SELECT * FROM employeerole WHERE employeeid=?) result INNER JOIN employeetype ON result.employeetypeid=employeetype.employeetypeid";
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try (Connection conn = connectionUtil.getConnection()){
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEmployeeId());
			rs = ps.executeQuery();
			while(rs.next()) {
				roles.add(rs.getString(1));
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		e.setRoles(roles);
		
		return roles;
		
	}
	
}
