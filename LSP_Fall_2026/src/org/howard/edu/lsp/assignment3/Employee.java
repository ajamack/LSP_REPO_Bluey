package org.howard.edu.lsp.assignment3;

/** Represents a single line of employee data 
 * to be manipulated later in the pipeline
 */
public class Employee {
	private int employeeId;
	private String name;
	private String department;
	private double hoursWorked;
	private double hourlyRate;
	private double grossPay;
	private String payLevel;
	private String employmentStatus;
	
	public Employee(int employeeId, String name, String department, double hoursWorked, double hourlyRate) {
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.hoursWorked = hoursWorked;
		this.hourlyRate = hourlyRate;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	public double getGrossPay() {
		return grossPay;
	}
	
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}
	public String getPayLevel() {
		return payLevel;
	}
	public void setPayLevel(String payLevel) {
		this.payLevel = payLevel;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

}


