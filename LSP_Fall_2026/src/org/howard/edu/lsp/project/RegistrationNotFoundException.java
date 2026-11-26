package org.howard.edu.lsp.project;

/**
 * Throws an exception when a registration does not exist.
 */

public class RegistrationNotFoundException extends EventSystemException {
	private static final long serialVersionUID = 1L;

	/** 
	 * Creates a new exception with a message describing the problem.
	 * @param message the detailed message
	 */
	public RegistrationNotFoundException(String message) {
		super(message);
	}

}
