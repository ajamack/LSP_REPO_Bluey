package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PayCalculator {
	public double calculateGrossPay(double hoursWorked, double hourlyRate, String department) {
		double pay;
		// Calculate hourly pay, overtime, IT pay
		if (hoursWorked <= 40) {
			pay = hoursWorked * hourlyRate;
		} else {
			pay = 40 * hourlyRate + (hoursWorked - 40) * hourlyRate * 1.5;
		}
		if (department.equals ("IT")) {
			pay = pay * 1.05;
		}
		BigDecimal payBigDecimal = new BigDecimal(Double.toString(pay));
		payBigDecimal = payBigDecimal.setScale(2, RoundingMode.HALF_UP);
		double grossPay = payBigDecimal.doubleValue();
		
		return grossPay;
	}
	public String payClassification(double grossPay) {
		String payLevel;
		
		if (grossPay < 500.00) {
			payLevel = "Low";
		} else if (grossPay < 1000.00) {
			payLevel = "Standard";
		} else if (grossPay < 2000.00) {
			payLevel = "High";
		} else {
			payLevel = "Executive";
		}
		return payLevel;
		
		
	}
	public String determineEmploymentStatus(double hoursWorked) {
		String employmentStatus;
		
		if (hoursWorked < 30.00) {
			employmentStatus = "Part-Time";
		} else {
			employmentStatus = "Full-Time";
		}
		return employmentStatus;
	}

}
