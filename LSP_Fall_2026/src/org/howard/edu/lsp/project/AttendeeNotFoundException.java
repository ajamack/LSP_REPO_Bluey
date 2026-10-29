package org.howard.edu.lsp.project;

/**
 * Throws an exception when an attendee does not exist.
 */

public class AttendeeNotFoundException extends EventSystemException {
	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public AttendeeNotFoundException(String message) {
		super(message);
	}

}
