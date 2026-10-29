package org.howard.edu.lsp.project;
/**
 * Base exception for all invalid operations in the event registration system.
 */

public class EventSystemException extends Exception {
	
	/**
	 * Creates new exception with a message describing the problem
	 * @param message the detail message
	 */
	
	public EventSystemException(String message) {
		super(message);
	}

}
