package com.revature.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.revature.bean.Employee;
import com.revature.util.ConnectionUtil;

public class ReimbursementDAO {
	
	private static ReimbursementDAO reimbursementDAO;
	
	private ReimbursementDAO() {
		
	}
	
	public static ReimbursementDAO getInstance() {
		if(reimbursementDAO == null) {
			reimbursementDAO = new ReimbursementDAO();
			return reimbursementDAO;
		}else {
			return reimbursementDAO;
		}
	}
	
	public int create(Employee em, String description, double cost, String gradeFormat, String eventType, String workJustification, Blob attachment, Blob approvalDocument, int timeMissed, java.util.Date startDate, String address, String city, String zip, String country) {
		
		String sql = "{call sp_insert_reimbursement (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		CallableStatement cs = null;
		int reimbursementId = 0;
		try (Connection conn = connectionUtil.getConnection()){
			
			int approvalProcessId = createApprovalProcess();
			int reimbursementLocationId = createReimbursementLocation(startDate, address, city, zip, country);
			
			cs = conn.prepareCall(sql);
			
			cs.setInt(1, em.getEmployeeId());
			cs.setInt(2, approvalProcessId);
			cs.setInt(3, reimbursementLocationId);
			cs.setString(4, description);
			cs.setDouble(5, cost);
			cs.setString(6, gradeFormat);
			cs.setString(7, eventType);
			cs.setString(8, workJustification);
			if(attachment == null) {
				cs.setNull(9, Types.BLOB);
			}else {
				cs.setBlob(9, attachment);
			}
			
			if(approvalDocument == null) {
				cs.setNull(10, Types.BLOB);
			}else {
				cs.setBlob(10, approvalDocument);
			}
			
			cs.setInt(11, timeMissed);
			
			cs.registerOutParameter(12, Types.INTEGER);
			
			cs.execute();
			
			reimbursementId = cs.getInt(12);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reimbursementId;
	}
	
	public int createApprovalProcess() {
		
		String sql = "{call sp_insert_approvalprocess (?, ?, ?)}";
		CallableStatement cs = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		java.util.Date date = new java.util.Date();
		int approvalProcessId = 0;
		try (Connection conn = connectionUtil.getConnection()){
			
			cs = conn.prepareCall(sql);
			cs.setDate(1, new Date(date.getTime()));
			cs.setTimestamp(2, new Timestamp(date.getTime()));
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			
			approvalProcessId = cs.getInt(3);
			
			cs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return approvalProcessId;
		
	}
	
	public int createReimbursementLocation(java.util.Date startDate, String address, String city, String zip, String country) {
		String sql = "call sp_insert_reimlocation (?, ?, ?, ?, ?, ?)";
		
		CallableStatement cs = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		int reimbursementLocationId = 0;
		try (Connection conn = connectionUtil.getConnection()){
			
			cs = conn.prepareCall(sql);
			cs.setDate(1, new Date(startDate.getTime()));
			cs.setString(2, address);
			cs.setString(3, city);
			if(zip == null || zip.equals("")) {
				cs.setNull(4, Types.VARCHAR);
			}else {
				cs.setString(4, zip);
			}
			cs.setString(5, country);
			cs.registerOutParameter(6, Types.INTEGER);
			cs.execute();
			
			reimbursementLocationId = cs.getInt(6);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reimbursementLocationId;
	}
	
}
