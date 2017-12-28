package com.revature.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class Reimbursement implements Serializable {

	private int reimbursementId;
	private int employeeId;
	private int supervisorId;
	private int departmentHeadId;
	private int benCoId;
	private int approvalProcessId;
	private Date employeeCreationDate;
	private Timestamp employeeCreationTime;
	private Date supervisorApproveDate;
	private Date departmentHeadApproveDate;
	private int reimbursementLocationId;
	private Date startDate;
	private String address;
	private String city;
	private String zip;
	private String country;
	private String description;
	private double cost;
	private double adjustedCost;
	private int gradeFormatId;
	private String format;
	private String defaultPassingGrade;
	private int eventTypeId;
	private String eventType;
	private Double coverage;
	private String workJustification;
	private Blob attachment;
	private Blob approvalDocument;
	private int approvalId;
	private String status;
	private int timeMissed;
	private String denyReason;
	private String inflatedReimbursementReason;

	public Reimbursement() {
		super();
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public int getDepartmentHeadId() {
		return departmentHeadId;
	}

	public void setDepartmentHeadId(int departmentHeadId) {
		this.departmentHeadId = departmentHeadId;
	}

	public int getBenCoId() {
		return benCoId;
	}

	public void setBenCoId(int benCoId) {
		this.benCoId = benCoId;
	}

	public int getApprovalProcessId() {
		return approvalProcessId;
	}

	public void setApprovalProcessId(int approvalProcessId) {
		this.approvalProcessId = approvalProcessId;
	}

	public Date getEmployeeCreationDate() {
		return employeeCreationDate;
	}

	public void setEmployeeCreationDate(Date employeeCreationDate) {
		this.employeeCreationDate = employeeCreationDate;
	}

	public Timestamp getEmployeeCreationTime() {
		return employeeCreationTime;
	}

	public void setEmployeeCreationTime(Timestamp employeeCreationTime) {
		this.employeeCreationTime = employeeCreationTime;
	}

	public Date getSupervisorApproveDate() {
		return supervisorApproveDate;
	}

	public void setSupervisorApproveDate(Date supervisorApproveDate) {
		this.supervisorApproveDate = supervisorApproveDate;
	}

	public Date getDepartmentHeadApproveDate() {
		return departmentHeadApproveDate;
	}

	public void setDepartmentHeadApproveDate(Date departmentHeadApproveDate) {
		this.departmentHeadApproveDate = departmentHeadApproveDate;
	}

	public int getReimbursementLocationId() {
		return reimbursementLocationId;
	}

	public void setReimbursementLocationId(int reimbursementLocationId) {
		this.reimbursementLocationId = reimbursementLocationId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getAdjustedCost() {
		return adjustedCost;
	}

	public void setAdjustedCost(double adjustedCost) {
		this.adjustedCost = adjustedCost;
	}

	public int getGradeFormatId() {
		return gradeFormatId;
	}

	public void setGradeFormatId(int gradeFormatId) {
		this.gradeFormatId = gradeFormatId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDefaultPassingGrade() {
		return defaultPassingGrade;
	}

	public void setDefaultPassingGrade(String defaultPassingGrade) {
		this.defaultPassingGrade = defaultPassingGrade;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Double getCoverage() {
		return coverage;
	}

	public void setCoverage(Double coverage) {
		this.coverage = coverage;
	}

	public String getWorkJustification() {
		return workJustification;
	}

	public void setWorkJustification(String workJustification) {
		this.workJustification = workJustification;
	}

	public Blob getAttachment() {
		return attachment;
	}

	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}

	public Blob getApprovalDocument() {
		return approvalDocument;
	}

	public void setApprovalDocument(Blob approvalDocument) {
		this.approvalDocument = approvalDocument;
	}

	public int getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTimeMissed() {
		return timeMissed;
	}

	public void setTimeMissed(int timeMissed) {
		this.timeMissed = timeMissed;
	}

	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	public String getInflatedReimbursementReason() {
		return inflatedReimbursementReason;
	}

	public void setInflatedReimbursementReason(String inflatedReimbursementReason) {
		this.inflatedReimbursementReason = inflatedReimbursementReason;
	}

}
