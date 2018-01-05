package com.revature.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.EmployeeReimbursement;
import com.revature.beans.EventType;
import com.revature.beans.GradeFormat;
import com.revature.beans.Reimbursement;
import com.revature.util.ConnectionUtil;

import oracle.jdbc.OracleTypes;

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
	
	public int create(Employee em, String description, double cost, String gradeFormat, String eventType, String workJustification, byte[] attachment, byte[] approvalDocument, int timeMissed, java.util.Date startDate, String address, String city, String zip, String country, String passingGrade) {
		
		String sql = "{call sp_insert_reimbursement (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
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
			if(attachment == null || attachment.length == 0) {
				cs.setNull(9, Types.BLOB);
			}else {
				Blob bAttachment = conn.createBlob();
				bAttachment.setBytes(attachment.length, attachment);
				cs.setBlob(9, bAttachment);
			}
			
			if(approvalDocument == null || approvalDocument.length == 0) {
				cs.setNull(10, Types.BLOB);
			}else {
				Blob bApprovalDocument = conn.createBlob();
				bApprovalDocument.setBytes(approvalDocument.length, approvalDocument);
				cs.setBlob(10, bApprovalDocument);
			}
			
			cs.setInt(11, timeMissed);
			
			cs.setString(12, passingGrade);
			
			cs.registerOutParameter(13, Types.INTEGER);
			
			cs.execute();
			
			reimbursementId = cs.getInt(13);
			
			
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
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return reimbursementLocationId;
	}
	
	public List<Reimbursement> getAllByEmployee(Employee em){
		
		String getAllSQL = "{call sp_select_reimbursement (?, ?)}";
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		Reimbursement reimbursement = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		CallableStatement cs = null;
		ResultSet rs = null;
		try (Connection conn = connectionUtil.getConnection()){
			
			cs = conn.prepareCall(getAllSQL);
			cs.setInt(1, em.getEmployeeId());
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet) cs.getObject(2);
			
			while(rs.next()) {
				
				reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("reimbursementid"));
				reimbursement.setEmployeeId(rs.getInt("employeeid"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setBenCoId(rs.getInt("bencoid"));
				reimbursement.setApprovalProcessId(rs.getInt("approvalprocessid"));
				reimbursement.setEmployeeCreationDate(rs.getDate("employeecreationdate"));
				reimbursement.setEmployeeCreationTime(rs.getTimestamp("employeecreationtime"));
				reimbursement.setSupervisorApproveDate(rs.getDate("supervisorapprovedate"));
				reimbursement.setDepartmentHeadApproveDate(rs.getDate("departmentheadapprovedate"));
				reimbursement.setReimbursementLocationId(rs.getInt("reimbursementlocationid"));
				reimbursement.setStartDate(rs.getDate("startdate"));
				reimbursement.setAddress(rs.getString("address"));
				reimbursement.setCity(rs.getString("city"));
				reimbursement.setZip(rs.getString("zip"));
				reimbursement.setCountry(rs.getString("country"));
				reimbursement.setDescription(rs.getString("description"));
				reimbursement.setCost(rs.getDouble("cost"));
				reimbursement.setAdjustedCost(rs.getDouble("adjustedcost"));
				reimbursement.setGradeFormatId(rs.getInt("gradeformatid"));
				reimbursement.setFormat(rs.getString("format"));
				reimbursement.setCustomPassingGrade(rs.getString("custompassinggrade"));
				reimbursement.setDefaultPassingGrade(rs.getString("defaultpassinggrade"));
				reimbursement.setEventTypeId(rs.getInt("eventtypeid"));
				reimbursement.setEventType(rs.getString("eventtype"));
				reimbursement.setCoverage(rs.getDouble("coverage"));
				reimbursement.setWorkJustification(rs.getString("workjustification"));
				reimbursement.setAttachment(rs.getBlob("attachment"));
				reimbursement.setApprovalDocument(rs.getBlob("approvaldocument"));
				reimbursement.setApprovalId(rs.getInt("approvalid"));
				reimbursement.setStatus(rs.getString("status"));
				reimbursement.setTimeMissed(rs.getInt("timemissed"));
				reimbursement.setDenyReason(rs.getString("denyreason"));
				reimbursement.setInflatedReimbursementReason(rs.getString("inflatedreimbursementreason"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setSupervisorEmail(rs.getString("supervisoremail"));
				reimbursement.setSupervisorFirstName(rs.getString("supervisorfirstname"));
				reimbursement.setSupervisorLastName(rs.getString("supervisorlastname"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setDepartmentHeadEmail(rs.getString("departmentheademail"));
				reimbursement.setDepartmentHeadFirstName(rs.getString("departmentheadfirstname"));
				reimbursement.setDepartmentHeadLastName(rs.getString("departmentheadlastname"));
				reimbursement.setGrade(rs.getBlob("grade"));
				list.add(reimbursement);
			}
			
			
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
		
		return list;
	}
	
	public List<EventType> getEventTypes(){
		List<EventType> events = new ArrayList<EventType>();
		String sql = "SELECT * FROM eventtype ORDER BY eventtypeid";
		EventType eventType = null;
		Statement statement = null;
		ResultSet rs = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try (Connection conn = connectionUtil.getConnection()){
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				eventType = new EventType();
				eventType.setEventTypeId(rs.getInt("eventtypeid"));
				eventType.setEventType(rs.getString("eventtype"));
				eventType.setCoverage(rs.getDouble("coverage"));
				events.add(eventType);
			}
			
			statement.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}

	public List<GradeFormat> getGradeFormats(){
		List<GradeFormat> gradeFormats = new ArrayList<GradeFormat>();
		String sql = "SELECT * FROM gradeformat ORDER BY gradeformatid";
		GradeFormat gradeFormat = null;
		Statement statement = null;
		ResultSet rs = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try (Connection conn = connectionUtil.getConnection()){
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				gradeFormat = new GradeFormat();
				gradeFormat.setGradeFormatId(rs.getInt("gradeformatid"));
				gradeFormat.setFormat(rs.getString("format"));
				gradeFormat.setDefaultPassingGrade(rs.getString("defaultpassinggrade"));
				gradeFormats.add(gradeFormat);
			}
			
			statement.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gradeFormats;
	}
	
	public Reimbursement getById(int id) {
		Reimbursement reimbursement = new Reimbursement();
		String sql = "{call sp_select_one_reimbursement (?, ?)}";
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		CallableStatement cs = null;
		ResultSet rs = null;
		try (Connection conn = connectionUtil.getConnection()){
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(2);
			
			while(rs.next()) {
				reimbursement.setReimbursementId(rs.getInt("reimbursementid"));
				reimbursement.setEmployeeId(rs.getInt("employeeid"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setBenCoId(rs.getInt("bencoid"));
				reimbursement.setApprovalProcessId(rs.getInt("approvalprocessid"));
				reimbursement.setEmployeeCreationDate(rs.getDate("employeecreationdate"));
				reimbursement.setEmployeeCreationTime(rs.getTimestamp("employeecreationtime"));
				reimbursement.setSupervisorApproveDate(rs.getDate("supervisorapprovedate"));
				reimbursement.setDepartmentHeadApproveDate(rs.getDate("departmentheadapprovedate"));
				reimbursement.setReimbursementLocationId(rs.getInt("reimbursementlocationid"));
				reimbursement.setStartDate(rs.getDate("startdate"));
				reimbursement.setAddress(rs.getString("address"));
				reimbursement.setCity(rs.getString("city"));
				reimbursement.setZip(rs.getString("zip"));
				reimbursement.setCountry(rs.getString("country"));
				reimbursement.setDescription(rs.getString("description"));
				reimbursement.setCost(rs.getDouble("cost"));
				reimbursement.setAdjustedCost(rs.getDouble("adjustedcost"));
				reimbursement.setGradeFormatId(rs.getInt("gradeformatid"));
				reimbursement.setFormat(rs.getString("format"));
				reimbursement.setCustomPassingGrade(rs.getString("custompassinggrade"));
				reimbursement.setDefaultPassingGrade(rs.getString("defaultpassinggrade"));
				reimbursement.setEventTypeId(rs.getInt("eventtypeid"));
				reimbursement.setEventType(rs.getString("eventtype"));
				reimbursement.setCoverage(rs.getDouble("coverage"));
				reimbursement.setWorkJustification(rs.getString("workjustification"));
				reimbursement.setAttachment(rs.getBlob("attachment"));
				reimbursement.setApprovalDocument(rs.getBlob("approvaldocument"));
				reimbursement.setApprovalId(rs.getInt("approvalid"));
				reimbursement.setStatus(rs.getString("status"));
				reimbursement.setTimeMissed(rs.getInt("timemissed"));
				reimbursement.setDenyReason(rs.getString("denyreason"));
				reimbursement.setInflatedReimbursementReason(rs.getString("inflatedreimbursementreason"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setSupervisorEmail(rs.getString("supervisoremail"));
				reimbursement.setSupervisorFirstName(rs.getString("supervisorfirstname"));
				reimbursement.setSupervisorLastName(rs.getString("supervisorlastname"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setDepartmentHeadEmail(rs.getString("departmentheademail"));
				reimbursement.setDepartmentHeadFirstName(rs.getString("departmentheadfirstname"));
				reimbursement.setDepartmentHeadLastName(rs.getString("departmentheadlastname"));
				reimbursement.setGrade(rs.getBlob("grade"));
			}
			
			rs.close();
			cs.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(reimbursement.getReimbursementId() == 0) {
			return null;
		}else {
			return reimbursement;
		}
	}

	public List<EmployeeReimbursement> getAllReimbursementsFromUnderlings(int id){
		List<EmployeeReimbursement> list = new ArrayList<EmployeeReimbursement>();
		String sql = "{call sp_select_all_underling_reim (?, ?)}";
		CallableStatement cs = null;
		ResultSet rs = null;
		Employee employee = null;
		Reimbursement reimbursement = null;
		EmployeeReimbursement employeeReimbursement = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try (Connection conn = connectionUtil.getConnection()){
			
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet) cs.getObject(2);
			while(rs.next()) {
				employee = new Employee();
				reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("reimbursementid"));
				reimbursement.setEmployeeId(rs.getInt("employeeid"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setBenCoId(rs.getInt("bencoid"));
				reimbursement.setApprovalProcessId(rs.getInt("approvalprocessid"));
				reimbursement.setEmployeeCreationDate(rs.getDate("employeecreationdate"));
				reimbursement.setEmployeeCreationTime(rs.getTimestamp("employeecreationtime"));
				reimbursement.setSupervisorApproveDate(rs.getDate("supervisorapprovedate"));
				reimbursement.setDepartmentHeadApproveDate(rs.getDate("departmentheadapprovedate"));
				reimbursement.setReimbursementLocationId(rs.getInt("reimbursementlocationid"));
				reimbursement.setStartDate(rs.getDate("startdate"));
				reimbursement.setAddress(rs.getString("address"));
				reimbursement.setCity(rs.getString("city"));
				reimbursement.setZip(rs.getString("zip"));
				reimbursement.setCountry(rs.getString("country"));
				reimbursement.setDescription(rs.getString("description"));
				reimbursement.setCost(rs.getDouble("cost"));
				reimbursement.setAdjustedCost(rs.getDouble("adjustedcost"));
				reimbursement.setGradeFormatId(rs.getInt("gradeformatid"));
				reimbursement.setFormat(rs.getString("format"));
				reimbursement.setCustomPassingGrade(rs.getString("custompassinggrade"));
				reimbursement.setDefaultPassingGrade(rs.getString("defaultpassinggrade"));
				reimbursement.setEventTypeId(rs.getInt("eventtypeid"));
				reimbursement.setEventType(rs.getString("eventtype"));
				reimbursement.setCoverage(rs.getDouble("coverage"));
				reimbursement.setWorkJustification(rs.getString("workjustification"));
				reimbursement.setAttachment(rs.getBlob("attachment"));
				reimbursement.setApprovalDocument(rs.getBlob("approvaldocument"));
				reimbursement.setApprovalId(rs.getInt("approvalid"));
				reimbursement.setStatus(rs.getString("status"));
				reimbursement.setTimeMissed(rs.getInt("timemissed"));
				reimbursement.setDenyReason(rs.getString("denyreason"));
				reimbursement.setInflatedReimbursementReason(rs.getString("inflatedreimbursementreason"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setSupervisorEmail(rs.getString("supervisoremail"));
				reimbursement.setSupervisorFirstName(rs.getString("supervisorfirstname"));
				reimbursement.setSupervisorLastName(rs.getString("supervisorlastname"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setDepartmentHeadEmail(rs.getString("departmentheademail"));
				reimbursement.setDepartmentHeadFirstName(rs.getString("departmentheadfirstname"));
				reimbursement.setDepartmentHeadLastName(rs.getString("departmentheadlastname"));
				reimbursement.setGrade(rs.getBlob("grade"));
				employee.setEmployeeId(rs.getInt("employeeid"));
				employee.setEmail(rs.getString("email"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setReportsTo(rs.getInt("reportsto"));
				employee.setDepartmentId(rs.getInt("departmentid"));
				
				employeeReimbursement = new EmployeeReimbursement();
				employeeReimbursement.setReimbursement(reimbursement);
				employeeReimbursement.setEmployee(employee);
				list.add(employeeReimbursement);
				
			}
			
			
			rs.close();
			cs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return list;

		
	}
	
	public List<EmployeeReimbursement> getAllReimbursementsFromDepartment(int departmentId) {
		List<EmployeeReimbursement> list = new ArrayList<EmployeeReimbursement>();
		String sql = "{call sp_select_all_department (?, ?)}";
		CallableStatement cs = null;
		ResultSet rs = null;
		Employee employee = null;
		Reimbursement reimbursement = null;
		EmployeeReimbursement employeeReimbursement = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try (Connection conn = connectionUtil.getConnection()){
			
			cs = conn.prepareCall(sql);
			cs.setInt(1, departmentId);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet) cs.getObject(2);
			while(rs.next()) {
				employee = new Employee();
				reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("reimbursementid"));
				reimbursement.setEmployeeId(rs.getInt("employeeid"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setBenCoId(rs.getInt("bencoid"));
				reimbursement.setApprovalProcessId(rs.getInt("approvalprocessid"));
				reimbursement.setEmployeeCreationDate(rs.getDate("employeecreationdate"));
				reimbursement.setEmployeeCreationTime(rs.getTimestamp("employeecreationtime"));
				reimbursement.setSupervisorApproveDate(rs.getDate("supervisorapprovedate"));
				reimbursement.setDepartmentHeadApproveDate(rs.getDate("departmentheadapprovedate"));
				reimbursement.setReimbursementLocationId(rs.getInt("reimbursementlocationid"));
				reimbursement.setStartDate(rs.getDate("startdate"));
				reimbursement.setAddress(rs.getString("address"));
				reimbursement.setCity(rs.getString("city"));
				reimbursement.setZip(rs.getString("zip"));
				reimbursement.setCountry(rs.getString("country"));
				reimbursement.setDescription(rs.getString("description"));
				reimbursement.setCost(rs.getDouble("cost"));
				reimbursement.setAdjustedCost(rs.getDouble("adjustedcost"));
				reimbursement.setGradeFormatId(rs.getInt("gradeformatid"));
				reimbursement.setFormat(rs.getString("format"));
				reimbursement.setCustomPassingGrade(rs.getString("custompassinggrade"));
				reimbursement.setDefaultPassingGrade(rs.getString("defaultpassinggrade"));
				reimbursement.setEventTypeId(rs.getInt("eventtypeid"));
				reimbursement.setEventType(rs.getString("eventtype"));
				reimbursement.setCoverage(rs.getDouble("coverage"));
				reimbursement.setWorkJustification(rs.getString("workjustification"));
				reimbursement.setAttachment(rs.getBlob("attachment"));
				reimbursement.setApprovalDocument(rs.getBlob("approvaldocument"));
				reimbursement.setApprovalId(rs.getInt("approvalid"));
				reimbursement.setStatus(rs.getString("status"));
				reimbursement.setTimeMissed(rs.getInt("timemissed"));
				reimbursement.setDenyReason(rs.getString("denyreason"));
				reimbursement.setInflatedReimbursementReason(rs.getString("inflatedreimbursementreason"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setSupervisorEmail(rs.getString("supervisoremail"));
				reimbursement.setSupervisorFirstName(rs.getString("supervisorfirstname"));
				reimbursement.setSupervisorLastName(rs.getString("supervisorlastname"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setDepartmentHeadEmail(rs.getString("departmentheademail"));
				reimbursement.setDepartmentHeadFirstName(rs.getString("departmentheadfirstname"));
				reimbursement.setDepartmentHeadLastName(rs.getString("departmentheadlastname"));
				reimbursement.setGrade(rs.getBlob("grade"));
				employee.setEmployeeId(rs.getInt("employeeid"));
				employee.setEmail(rs.getString("email"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setReportsTo(rs.getInt("reportsto"));
				employee.setDepartmentId(rs.getInt("departmentid"));
				
				employeeReimbursement = new EmployeeReimbursement();
				employeeReimbursement.setReimbursement(reimbursement);
				employeeReimbursement.setEmployee(employee);
				list.add(employeeReimbursement);
				
			}
			
			
			rs.close();
			cs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<EmployeeReimbursement> getAllReimbursementsForBenCo() {
		List<EmployeeReimbursement> list = new ArrayList<EmployeeReimbursement>();
		String sql = "{call sp_select_all_benco (?)}";
		CallableStatement cs = null;
		ResultSet rs = null;
		Employee employee = null;
		Reimbursement reimbursement = null;
		EmployeeReimbursement employeeReimbursement = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try (Connection conn = connectionUtil.getConnection()){
			
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			
			cs.execute();
			
			rs = (ResultSet) cs.getObject(1);
			while(rs.next()) {
				employee = new Employee();
				reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("reimbursementid"));
				reimbursement.setEmployeeId(rs.getInt("employeeid"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setBenCoId(rs.getInt("bencoid"));
				reimbursement.setApprovalProcessId(rs.getInt("approvalprocessid"));
				reimbursement.setEmployeeCreationDate(rs.getDate("employeecreationdate"));
				reimbursement.setEmployeeCreationTime(rs.getTimestamp("employeecreationtime"));
				reimbursement.setSupervisorApproveDate(rs.getDate("supervisorapprovedate"));
				reimbursement.setDepartmentHeadApproveDate(rs.getDate("departmentheadapprovedate"));
				reimbursement.setReimbursementLocationId(rs.getInt("reimbursementlocationid"));
				reimbursement.setStartDate(rs.getDate("startdate"));
				reimbursement.setAddress(rs.getString("address"));
				reimbursement.setCity(rs.getString("city"));
				reimbursement.setZip(rs.getString("zip"));
				reimbursement.setCountry(rs.getString("country"));
				reimbursement.setDescription(rs.getString("description"));
				reimbursement.setCost(rs.getDouble("cost"));
				reimbursement.setAdjustedCost(rs.getDouble("adjustedcost"));
				reimbursement.setGradeFormatId(rs.getInt("gradeformatid"));
				reimbursement.setFormat(rs.getString("format"));
				reimbursement.setCustomPassingGrade(rs.getString("custompassinggrade"));
				reimbursement.setDefaultPassingGrade(rs.getString("defaultpassinggrade"));
				reimbursement.setEventTypeId(rs.getInt("eventtypeid"));
				reimbursement.setEventType(rs.getString("eventtype"));
				reimbursement.setCoverage(rs.getDouble("coverage"));
				reimbursement.setWorkJustification(rs.getString("workjustification"));
				reimbursement.setAttachment(rs.getBlob("attachment"));
				reimbursement.setApprovalDocument(rs.getBlob("approvaldocument"));
				reimbursement.setApprovalId(rs.getInt("approvalid"));
				reimbursement.setStatus(rs.getString("status"));
				reimbursement.setTimeMissed(rs.getInt("timemissed"));
				reimbursement.setDenyReason(rs.getString("denyreason"));
				reimbursement.setInflatedReimbursementReason(rs.getString("inflatedreimbursementreason"));
				reimbursement.setSupervisorId(rs.getInt("supervisorid"));
				reimbursement.setSupervisorEmail(rs.getString("supervisoremail"));
				reimbursement.setSupervisorFirstName(rs.getString("supervisorfirstname"));
				reimbursement.setSupervisorLastName(rs.getString("supervisorlastname"));
				reimbursement.setDepartmentHeadId(rs.getInt("departmentheadid"));
				reimbursement.setDepartmentHeadEmail(rs.getString("departmentheademail"));
				reimbursement.setDepartmentHeadFirstName(rs.getString("departmentheadfirstname"));
				reimbursement.setDepartmentHeadLastName(rs.getString("departmentheadlastname"));
				reimbursement.setGrade(rs.getBlob("grade"));
				employee.setEmployeeId(rs.getInt("employeeid"));
				employee.setEmail(rs.getString("email"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setReportsTo(rs.getInt("reportsto"));
				employee.setDepartmentId(rs.getInt("departmentid"));
				
				employeeReimbursement = new EmployeeReimbursement();
				employeeReimbursement.setReimbursement(reimbursement);
				employeeReimbursement.setEmployee(employee);
				list.add(employeeReimbursement);
				
			}
			
			
			rs.close();
			cs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	

	public void updateStatus(int id, Employee user , String approval, String reason, String role) {
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		CallableStatement cs = null;
		if(role.equals("supervisor")) {
			if(approval.equals("APPROVED")) {
				String sql = "{call sp_update_approve_superv_reim (?,?)}";
				try(Connection conn = connectionUtil.getConnection()){
					cs = conn.prepareCall(sql);
					cs.setInt(1, id);
					cs.setInt(2, user.getEmployeeId());
					cs.execute();
					cs.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(user.getRoles().contains("Department Head")) {
					updateStatus(id, user, approval, reason, "departmentHead");
					return;
				}
			}else {
				
				String sql = "{call sp_update_deny_supervisor_reim (?,?,?,?)}";
				try (Connection conn = connectionUtil.getConnection()){
					cs = conn.prepareCall(sql);
					cs.setInt(1, id);
					cs.setInt(2, user.getEmployeeId());
					cs.setString(3, approval);
					cs.setString(4, reason);
					
					cs.execute();
					cs.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(role.equals("departmentHead")) {
			if(approval.equals("APPROVED")) {
				String sql = "{call sp_update_approve_depart_reim (?,?)}";
				try(Connection conn = connectionUtil.getConnection()){
					cs = conn.prepareCall(sql);
					cs.setInt(1, id);
					cs.setInt(2, user.getEmployeeId());
					cs.execute();
					cs.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				String sql = "{call sp_update_deny_department_reim (?,?,?,?)}";
				try (Connection conn = connectionUtil.getConnection()){
					cs = conn.prepareCall(sql);
					cs.setInt(1, id);
					cs.setInt(2, user.getEmployeeId());
					cs.setString(3, approval);
					cs.setString(4, reason);
					
					cs.execute();
					cs.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(role.equals("benefitsCoordinator")) {
			if(approval.equals("APPROVED")) {
				String sql = "{call sp_update_approve_benco_reim (?)}";
				try(Connection conn = connectionUtil.getConnection()){
					cs = conn.prepareCall(sql);
					cs.setInt(1, id);
					cs.execute();
					cs.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				String sql = "{call sp_update_deny_benco_reim (?,?,?,?)}";
				try (Connection conn = connectionUtil.getConnection()){
					cs = conn.prepareCall(sql);
					cs.setInt(1, id);
					cs.setInt(2, user.getEmployeeId());
					cs.setString(3, approval);
					cs.setString(4, reason);
					
					cs.execute();
					cs.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(role.equals("employee")) {
			if(approval.equals("APPROVED")) {
				String sql = "{call sp_approve_reimb_employee (?)}";
				try (Connection conn = connectionUtil.getConnection()){
					
					cs = conn.prepareCall(sql);
					cs.setInt(1, id);
					
					cs.execute();
					cs.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				String sql = "{call sp_cancel_reim (?)}";
				try (Connection conn = connectionUtil.getConnection()){
					
					cs = conn.prepareCall(sql);
					cs.setInt(1, id);
					
					cs.execute();
					cs.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public void alterReimbursementAmount(int id, double alterAmount, String reason) {
		String sql = "{call sp_update_alter_reim (?,?,?)}";
		CallableStatement cs = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try (Connection conn = connectionUtil.getConnection()){
			
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.setDouble(2, alterAmount);
			cs.setString(3, reason);
			cs.execute();
			cs.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void uploadGrade(int id, byte[] file) {
		String sql = "UPDATE reimbursement SET grade=? WHERE reimbursementid=?";
		PreparedStatement ps = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try (Connection conn = connectionUtil.getConnection()){
			
			ps = conn.prepareStatement(sql);
			Blob grade = conn.createBlob();
			grade.setBytes(file.length, file);
			ps.setBlob(1, grade);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
			ps.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
