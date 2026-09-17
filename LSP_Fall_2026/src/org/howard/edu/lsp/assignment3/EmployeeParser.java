package org.howard.edu.lsp.assignment3;

public class EmployeeParser {
	/** Parse through each line, determine if valid employee, hand 
	 * back to Employee */
	// Must be 5 seperate fields
	public Employee parseLine(String line) {
		String[] fields = line.split(",");
		
		if (fields.length != 5) {
			return null;
		}
		// Assign fields
		String employeeIdStr = fields[0].trim();
		String name = fields[1].trim();
		String department = fields[2].trim();
		String hoursWorkedStr = fields[3].trim();
		String hourlyRateStr = fields[4].trim();
		
		int employeeId;
		try {
			employeeId = Integer.parseInt(employeeIdStr);
		} catch (NumberFormatException e) {
			return null;
		}
		double hoursWorked;
		try {
			hoursWorked = Double.parseDouble(hoursWorkedStr);
		} catch (NumberFormatException e) {
			return null;
		}
		double hourlyRate;
		try {
			hourlyRate = Double.parseDouble(hourlyRateStr);
		} catch (NumberFormatException e) {
			return null;
		}
		// No negative hours worked
		if (hoursWorked < 0 || hourlyRate < 0) {
			return null;
		}
		return new Employee(employeeId, name, department, hoursWorked, hourlyRate);
			
	}

}
