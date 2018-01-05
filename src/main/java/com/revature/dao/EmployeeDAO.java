package com.revature.dao;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.ConnectionUtil;

import oracle.jdbc.OracleTypes;

/**
 * Handles the operations between the server and the database for Employee-related functions.
 * @author Steven Sagun
 *
 */
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
	 * @param departmentId THe id of the department the employee belongs to.
	 * @return The employeeid of the employe that was created. 0 if the employee could not be created
	 */
	public int create(String email, String password, String firstName, String lastName, Integer reportsTo, Integer departmentId) {

		
		int pk = 0;
		
		String sql = "{call app_user_security.add_user (?, ?, ?, ?, ?, ?, ?, ?)}";
		
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
			
			callableStatement.setBytes(7, new SecureRandom().generateSeed(10));
			
			callableStatement.registerOutParameter(8, Types.INTEGER);
			
			callableStatement.execute();
			
			pk = callableStatement.getInt(8);
			
			
			callableStatement.close();
			
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
		
		
		
		return pk;
		
	}

	/**
	 * Finds an employee based on an email and password combination.
	 * @param email The email of the employee to find.
	 * @param password The pass word of the employee to find
	 * @return An instance of the employe that was retrieved.
	 */
	public Employee getEmployee(String email, String password) {
		String sql = "{? = call app_user_security.valid_user (?, ?)}";
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		CallableStatement cs = null;
		ResultSet rs = null;
		Employee em = null;
		try (Connection conn = connectionUtil.getConnection()){
		
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, email);
			cs.setString(3, password);
			
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			if (!rs.isBeforeFirst() ) {    
			    return null;
			} else {
				em = new Employee();
				while(rs.next()) {
					em.setEmployeeId(rs.getInt(1));
					em.setEmail(rs.getString(2));
					em.setFirstName(rs.getString(3));
					em.setLastName(rs.getString(4));
					em.setReportsTo(rs.getInt(5));
					em.setDepartmentId(rs.getInt(6));
				}
			}
			
			
			rs.close();
			cs.close();
			
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
		
		employeeDAO.getAllRolesForEmployee(em);
		
		return em;
	}
	
	/**
	 * Sets the roles for a specific employee. Also updates the employee to have those roles.
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
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		return rolesAdded;
	}
	
	/**
	 * Get all the roles for a particular employee.
	 * @param e The employee for which to get all the roles from.
	 * @return All roles held by the employee in the form of a list of strings.
	 */
	public List<String> getAllRolesForEmployee(Employee e){
		if(e == null) {
			return null;
		}
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
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		e.setRoles(roles);
		
		
		return roles;
		
	}
	
}
