package org.howard.edu.lsp.project;

/**
 * Throws an exception when an attendee registers for an event they are already registered for.
 */

public class DuplicateRegistrationException extends EventSystemException {
	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public DuplicateRegistrationException(String message) {
		super(message);
	}

}
