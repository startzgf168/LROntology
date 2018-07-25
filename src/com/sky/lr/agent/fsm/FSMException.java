package com.sky.lr.agent.fsm;

public class FSMException extends Exception {
	/** The version UID. */
	private static final long serialVersionUID = -1234567890128193028L;

	/**
	 * Create the object.
	 */
	public FSMException() {
		// empty
	}

	/**
	 * Create the object.
	 * 
	 * @param message
	 *            the message
	 */
	public FSMException(String message) {
		super(message);
	}

	/**
	 * Create the object.
	 * 
	 * @param cause
	 *            the cause
	 */
	public FSMException(Throwable cause) {
		super(cause);
	}

	/**
	 * Create the object.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public FSMException(String message, Throwable cause) {
		super(message, cause);
	}
}
