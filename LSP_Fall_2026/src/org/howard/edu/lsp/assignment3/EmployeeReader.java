package org.howard.edu.lsp.assignment3;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeReader {
	public List<String> readLines(String filePath) throws IOException {
		List<String> lines = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String headerLine = reader.readLine();

		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}

		reader.close();
		return lines;
	}

}