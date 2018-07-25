package com.sky.lr.utility.logger;

public interface BasicLogger {
	/**
	 * Get name of the logger.
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Log a fatal message.
	 * 
	 * @param message
	 *            the message
	 */
	public void fatal(String message);

	/**
	 * Log a fatal message.
	 * 
	 * @param message
	 *            the message
	 * @param throwable
	 *            a throwable object
	 */
	public void fatal(String message, Throwable throwable);

	/**
	 * Log an error message.
	 * 
	 * @param message
	 *            the message
	 */
	public void error(String message);

	/**
	 * Log an error message.
	 * 
	 * @param message
	 *            the message
	 * @param throwable
	 *            a throwable object
	 */
	public void error(String message, Throwable throwable);

	/**
	 * Log a warning message.
	 * 
	 * @param message
	 *            the message
	 */
	public void warn(String message);

	/**
	 * Log a warning message.
	 * 
	 * @param message
	 *            the message
	 * @param throwable
	 *            a throwable object
	 */
	public void warn(String message, Throwable throwable);

	/**
	 * Log an info message.
	 * 
	 * @param message
	 *            the message
	 */
	public void info(String message);

	/**
	 * Log an info message.
	 * 
	 * @param message
	 *            the message
	 * @param throwable
	 *            a throwable object
	 */
	public void info(String message, Throwable throwable);

	/**
	 * Log a debug message.
	 * 
	 * @param message
	 *            the message
	 */
	public void debug(String message);

	/**
	 * Log a debug message.
	 * 
	 * @param message
	 *            the message
	 * @param throwable
	 *            a throwable object
	 */
	public void debug(String message, Throwable throwable);

	/**
	 * Log a trace message.
	 * 
	 * @param message
	 *            the message
	 */
	public void trace(String message);

	/**
	 * Log a trace message.
	 * 
	 * @param message
	 *            the message
	 * @param throwable
	 *            a throwable object
	 */
	public void trace(String message, Throwable throwable);

	/**
	 * Get status of info messages logging in this logger.
	 * 
	 * @return true if logging of info messages is enabled otherwise false
	 */
	public boolean isInfoEnabled();

	/**
	 * Get status of debug messages logging in this logger.
	 * 
	 * @return true if logging of debug messages is enabled otherwise false
	 */
	public boolean isDebugEnabled();

	/**
	 * Get status of trace messages logging in this logger.
	 * 
	 * @return true if logging of trace messages is enabled otherwise false
	 */
	public boolean isTraceEnabled();
}
