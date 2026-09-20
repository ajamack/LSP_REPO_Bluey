package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ETLPipeline {

	public static void main(String[] args) throws IOException {
		String inputPath = "data/employees.csv";
		String outputPath = "data/transformed_employees.csv";
		
		EmployeeReader reader = new EmployeeReader();
	    EmployeeParser parser = new EmployeeParser();
	    PayCalculator calculator = new PayCalculator();
	    EmployeeWriter writer = new EmployeeWriter();
	    
	    int rowsRead = 0;
	    int rowsTransformed = 0;
	    int rowsSkipped = 0;

	    List<String> lines = reader.readLines(inputPath);
	    List<Employee> processedEmployees = new ArrayList<>();
	    
	    for (String line : lines) {
	        rowsRead++;

	        Employee emp = parser.parseLine(line);

	        if (emp == null) {
	            rowsSkipped++;
	        } else {
	            double grossPay = calculator.calculateGrossPay(emp.getHoursWorked(), emp.getHourlyRate(), emp.getDepartment());
	            emp.setGrossPay(grossPay);

	            String payLevel = calculator.payClassification(emp.getGrossPay());
	            emp.setPayLevel(payLevel);

	            String employmentStatus = calculator.determineEmploymentStatus(emp.getHoursWorked());
	            emp.setEmploymentStatus(employmentStatus);

	            processedEmployees.add(emp);
	            rowsTransformed++;
	            
	            
	        }
	        
	        
	    }
	    
	    writer.writeEmployees(outputPath, processedEmployees);

	    System.out.println("Rows read: " + rowsRead);
	    System.out.println("Rows transformed: " + rowsTransformed);
	    System.out.println("Rows skipped: " + rowsSkipped);
	    System.out.println("Output file: " + outputPath);
		

	}

}
