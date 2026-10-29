package org.howard.edu.lsp.project;
/**
 * Throws an exception when two events have the same identifier.
 */

public class DuplicateEventException extends EventSystemException {
	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public DuplicateEventException(String message) {
		super(message);
	}

}
