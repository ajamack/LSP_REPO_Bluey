package org.howard.edu.lsp.project;

/**
 * Throws an exception when an operation is not allowed in the current state.
 */

public class InvalidOperationException extends EventSystemException {
	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public InvalidOperationException(String message) {
		super(message);
	}

}
