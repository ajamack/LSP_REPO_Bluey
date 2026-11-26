package org.howard.edu.lsp.project;

/**
 * Throws an exception when an event is not open for registration.
 */

public class EventNotOpenException extends EventSystemException {
	private static final long serialVersionUID = 1L;

	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public EventNotOpenException(String message) {
		super(message);
	}

}
