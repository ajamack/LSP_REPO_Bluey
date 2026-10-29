package org.howard.edu.lsp.project;

/**
 * Throws an exception when an event is not open for registration.
 */

public class EventNotOpenException extends EventSystemException {
	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public EventNotOpenException(String message) {
		super(message);
	}

}
