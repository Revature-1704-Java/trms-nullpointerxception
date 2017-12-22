package com.revature.bean;

import java.io.Serializable;

public class Employee implements Serializable {

	private int employeeId;
	private int departmentId;
	private String username;
	private String firstName;
	private String lastName;
	private int reportsTo;
	private boolean isBenCo;
	private double availablereimbursement;

	public Employee() {
		super();
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	public boolean isBenCo() {
		return isBenCo;
	}

	public void setBenCo(boolean isBenCo) {
		this.isBenCo = isBenCo;
	}

	public double getAvailablereimbursement() {
		return availablereimbursement;
	}

	public void setAvailablereimbursement(double availablereimbursement) {
		this.availablereimbursement = availablereimbursement;
	}

}
