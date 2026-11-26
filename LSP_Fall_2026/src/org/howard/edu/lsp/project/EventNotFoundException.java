package org.howard.edu.lsp.project;

/**
 * Throws an exception when an event does not exist.
 */

public class EventNotFoundException extends EventSystemException {
	private static final long serialVersionUID = 1L;

	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public EventNotFoundException(String message) {
		super(message);
	}

}
