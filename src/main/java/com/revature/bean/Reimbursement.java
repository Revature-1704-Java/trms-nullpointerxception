package com.revature.bean;

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
	private int approvalprocessId;
	private int reimbursementLocationId;
	private String description;
	private double cost;
	private double adjustedCost;
	private int gradeFormatId;
	private int eventTypeId;
	private String workJustification;
	private Blob attachment;
	private Blob approvalDocument;
	private int approvalId;
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

	public int getApprovalprocessId() {
		return approvalprocessId;
	}

	public void setApprovalprocessId(int approvalprocessId) {
		this.approvalprocessId = approvalprocessId;
	}

	public int getReimbursementLocationId() {
		return reimbursementLocationId;
	}

	public void setReimbursementLocationId(int reimbursementLocationId) {
		this.reimbursementLocationId = reimbursementLocationId;
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

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
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
