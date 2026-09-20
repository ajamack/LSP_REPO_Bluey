package org.howard.edu.lsp.assignment3;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class EmployeeWriter {
	public void writeEmployees(String filePath, List<Employee> employees) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter(filePath));
		writer.println("EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus");

		for (Employee emp : employees) {
			String outputRow = emp.getEmployeeId() + "," + emp.getName() + "," + emp.getDepartment() + ","
					+ String.format("%.2f", emp.getHoursWorked()) + ","
					+ String.format("%.2f", emp.getHourlyRate()) + ","
					+ String.format("%.2f", emp.getGrossPay()) + ","
					+ emp.getPayLevel() + "," + emp.getEmploymentStatus();
			writer.println(outputRow);
		}

		writer.close();
	}

}