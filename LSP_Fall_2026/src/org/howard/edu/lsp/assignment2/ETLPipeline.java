package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
			writer.println("EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus");

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
							double hoursWorked = 0;
							boolean hoursWorked0k = true;

							try {
								hoursWorked = Double.parseDouble(hoursWorkedstr);
							} catch (NumberFormatException e) {
								hoursWorked0k = false;
							}

							if (!hoursWorked0k) {
								rowsSkipped++;

							} else {
								double hourlyRate = 0; // Must be decimal 
								boolean hourlyRate0k = true;
								
								try {
									hourlyRate = Double.parseDouble(hourlyRatestr);
								} catch (NumberFormatException e) {
									hourlyRate0k = false;
								}
								
								if (!hourlyRate0k) {
									rowsSkipped++;
									
								} else {
									if (hoursWorked < 0 || hourlyRate < 0) { // Values cannot be negative
										rowsSkipped++;
										
									} else {
										name = name.toUpperCase(); // Capitalize name
										
										double pay;
										
										if (hoursWorked <= 40) {
											pay = hoursWorked * hourlyRate;
										} else {
											pay = 40 * hourlyRate + (hoursWorked - 40) * hourlyRate * 1.5; // Calculate overtime
										}
										
										if (department.equals("IT") ) {
											pay = pay * 1.05; // Account for IT bonus
										}
										
										BigDecimal payBigDecimal = new BigDecimal (Double.toString(pay));
										payBigDecimal = payBigDecimal.setScale(2, java.math.RoundingMode.HALF_UP);
										double grossPay = payBigDecimal.doubleValue();
									
										String payLevel; // Determine employee pay level
										
										if (grossPay < 500.00) {
											payLevel = "Low";
										} else if (grossPay < 1000.00) {
											payLevel = "Standard";
										} else if (grossPay < 2000.00) {
											payLevel = "High";
											
										} else {
											payLevel = "Executive";
											
										}
										
										String employmentStatus;
										if (hoursWorked < 30.00) {
											employmentStatus = "Part-Time";
										} else {
											employmentStatus = "Full-Time";
										}
										
										String outputRow = employeeId + "," + name + "," + department + ","
												+ String.format("%.2f", hoursWorked) + ","
												+ String.format("%.2f", hourlyRate) + ","
												+ String.format("%.2f", grossPay) + ","
												+ payLevel + "," + employmentStatus;
										writer.println(outputRow);
										rowsTransformed++;

										
										
									}
									
								}

							}
						}

					}
				}
			}



			System.out.println("Rows read: " + rowsRead);
			System.out.println("Rows transformed: " + rowsTransformed);
			System.out.println("Rows skipped: " + rowsSkipped);
			System.out.println("Output file: " + outputPath);

			

			reader.close();
			writer.close();


			


		} catch (IOException e) {
			System.out.println("Error opening input file: " + e.getMessage());
		}



		
	}

}