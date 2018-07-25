package com.sky.lr.agent.fsm;

import com.sky.lr.utility.Checker;
import com.sky.lr.utility.logger.BasicLogger;
import com.sky.lr.utility.logger.BasicLoggerFactory;
import com.sky.lr.utility.logger.FSMLoggerFactory;


public class ConfigurationFSM {

	public static final String VERSION = "LR3.0"; // This is the initial version just for demo

	/** The factory of loggers. */
	private static BasicLoggerFactory loggerFactory = new FSMLoggerFactory();

	/**
	 * Define a logger factory that will be used to create loggers. The default
	 * logging subsystem is standard output and error streams.
	 * 
	 * Existing loggers will remain untouched. Prefer to call this method before
	 * all other ones in the library and just once.
	 * 
	 * @param factory
	 *            the logger factory that will be used for creating loggers
	 */
	public static void setLoggerFactory(FSMLoggerFactory factory) {
		Checker.ensureNotNull(factory, "factory");

		loggerFactory = factory;
	}

	/**
	 * Get logger for a specific class.
	 * 
	 * @param clazz
	 *            the class
	 * @return the logger
	 */
	public static BasicLogger getLogger(Class<?> clazz) {
		Checker.ensureNotNull(clazz, "class");

		return loggerFactory.getLogger(clazz);
	}

	/**
	 * Get logger for a specific class and instance.
	 * 
	 * @param clazz
	 *            the class
	 * @param instance
	 *            the class instance
	 * @return the logger
	 */
	public static BasicLogger getLogger(Class<?> clazz, String instance) {
		Checker.ensureNotNull(clazz, "class");
		Checker.ensureNotNull(instance, "instance");

		return loggerFactory.getLogger(clazz, instance);
	}

	/**
	 * Get logger for a specific logger with name.
	 * 
	 * @param name
	 *            the logger name
	 * @return the logger
	 */
	public static BasicLogger getLogger(String name) {
		Checker.ensureNotNull(name, "name");

		return loggerFactory.getLogger(name);
	}
}
