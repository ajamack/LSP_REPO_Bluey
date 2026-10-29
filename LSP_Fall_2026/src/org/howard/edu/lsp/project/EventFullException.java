package org.howard.edu.lsp.project;

/**
 * Throws an exception when an event is full and cannot accommodate more attendees.
 */

public class EventFullException extends EventSystemException {
	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public EventFullException(String message) {
		super(message);
	}

}
