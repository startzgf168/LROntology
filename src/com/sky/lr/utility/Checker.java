package com.sky.lr.utility;

import com.sky.lr.utility.logger.BasicLogger;

public class Checker {
	/** The delimiter of class names and instances. */
	public static final String CLASS_INSTANCE_DELIMITER = ".";

	/**
	 * Verify the passed object is not null.
	 * 
	 * @param object
	 *            the object
	 * @param objectName
	 *            the object name
	 * @throws NullPointerException
	 *             if the object is null
	 */
	public static void ensureNotNull(Object object, String objectName)
			throws NullPointerException {
		if (object == null) {
			throw new NullPointerException("Object must not be null: "
					+ objectName);
		}
	}

	/**
	 * Log runtime exception that occurred in client callback.
	 * 
	 * Log everything what is possible for future analysis and re-throw the
	 * exception. Current thread may stop but it is better than hide a more
	 * serious error.
	 * 
	 * @param logger
	 *            the logger
	 * @param message
	 *            the message to log
	 * @param exception
	 *            the exception that occurred
	 * 
	 * @see TimeoutStateMachine
	 */
	public static void logExceptionInClientCallback(BasicLogger logger,
			String message, RuntimeException exception) {
		logger.fatal(
				"Unexpected exception occurred probably in client callback code: "
						+ message + ", thread "
						+ Thread.currentThread().getName() + ", exception "
						+ exception.getClass() + ", exception message "
						+ exception.getMessage() + ", exception " + exception,
				exception);
	}

	/**
	 * Log that a serious error occurred and the currently running thread is
	 * being unexpectedly terminated.
	 * 
	 * @param logger
	 *            the logger for logging
	 * @param message
	 *            the message to log
	 * @param exception
	 *            the exception that occurred
	 */
	public static void logThreadUnexpectedlyFinished(BasicLogger logger,
			String message, Throwable exception) {
		logger.fatal(
				"Thread unexpectedly finished: " + message + ", thread "
						+ Thread.currentThread().getName() + ", exception "
						+ exception.getClass() + ", exception message "
						+ exception.getMessage() + ", exception " + exception,
				exception);
	}
}
