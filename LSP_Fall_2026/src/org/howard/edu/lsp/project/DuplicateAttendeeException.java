package org.howard.edu.lsp.project;

/**
 * Throws an exception when duplicate attendees are flagged.
 */

public class DuplicateAttendeeException extends Exception {
	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public DuplicateAttendeeException(String message) {
		super(message);
	}

}
