package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class ETLPipeline {

	public static void main(String[] args) {
		// ====== SETUP ========
		// Initialize counters
		int rowsRead = 0;
		int rowsTransformed = 0;
		int rowsSkipped = 0;
		
				
		String inputPath = "data/employees.csv";
		String outputPath = "data/transformed_employees.csv";
		
		
		// ====== EXTRACT ======
		// Open file, receive error message for invalid files
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputPath));
			String headerLine = reader.readLine(); 
			// Test Line System.out.println("Header was: " + headerLine);
			PrintWriter writer = new PrintWriter(new FileWriter(outputPath));
			writer.println("EmployeeID, Name, Department, HoursWorked, HourlyRate, GrossPay, PayLevel, EmploymentStatus");
			
			String line;
			while ((line = reader.readLine()) != null) {
				rowsRead++;
			
			
				// Skip rpws that are blank
				if (line.trim().isEmpty()) {
					rowsSkipped++;
				} else {
					String[] fields = line.split(","); // Ensure rows have exactly 5 field
					if (fields.length != 5) {
						rowsSkipped++;
					} else {
			
			
						// Trim whitespace from 5 fields
						String employeeIdStr = fields[0].trim();
						String name = fields[1].trim();
						String department = fields[2].trim();
						String hoursWorkedstr = fields[3].trim();
						String hourlyRatestr = fields[4].trim();
			
						// Ensure employeeID is Integer
						int employeeId = 0;
						boolean employeeId0k = true;
			
						try {
							employeeId = Integer.parseInt(employeeIdStr);
						} catch (NumberFormatException e) {
							employeeId0k = false;
						}
						if (!employeeId0k) {
							rowsSkipped++;
						} else {
				
						}
		

			
			
			System.out.println("Rows read so far: " + rowsRead);
		
			reader.close();
			writer.close();
			
			
//			read first line, discard
			// TODO: open transformed.csv for writing
			//		write header row to output file
			
			
			
		} catch (IOException e) {
			System.out.println("Error opening input file: " + e.getMessage());
		}
		
	
		
		// ===== MAIN LOOP ===== (Variables: EmployeeID, Name, Department, HoursWorked, HourlyRate, GrossPay, PayLevel, EmploymentStatus)
		// TODO: read line
		//		rowsread += 1
		//		isValid = True
		
		// if line is blank:
		// isValid = false
		// Else:
		// fields = split line by comma
		// if fields.length greater than 5:
		// isValid = False
		// Else:
		// trim whitespace off of 5 fields
		// what fields are which?

	}

}
