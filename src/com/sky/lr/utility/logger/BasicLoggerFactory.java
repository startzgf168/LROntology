package com.sky.lr.utility.logger;

public interface BasicLoggerFactory {
	/**
	 * Get logger for a specific class.
	 * 
	 * @param clazz
	 *            the class
	 * @return the logger
	 */
	public BasicLogger getLogger(Class<?> clazz);

	/**
	 * Get logger for a specific class and instance.
	 * 
	 * @param clazz
	 *            the class
	 * @param instance
	 *            the class instance
	 * @return the logger
	 */
	public BasicLogger getLogger(Class<?> clazz, String instance);

	/**
	 * Get logger for a specific logger with name.
	 * 
	 * @param name
	 *            the logger name
	 * @return the logger
	 */
	public BasicLogger getLogger(String name);
}
