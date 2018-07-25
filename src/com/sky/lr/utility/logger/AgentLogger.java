package com.sky.lr.utility.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.sky.lr.utility.Checker;


public class AgentLogger implements BasicLogger {
	/** The Agent logger object. */
	private final Logger logger;

	/**
	 * Create the object.
	 * 
	 * @param clazz
	 *            the class
	 */
	public AgentLogger(Class<?> clazz) {
		Checker.ensureNotNull(clazz, "class");

		logger = Logger.getLogger(clazz.getName());
	}

	/**
	 * Create the object.
	 * 
	 * @param clazz
	 *            the class
	 * @param instance
	 *            the class instance
	 */
	public AgentLogger(Class<?> clazz, String instance) {
		Checker.ensureNotNull(clazz, "class");
		Checker.ensureNotNull(instance, "instance");

		logger = Logger.getLogger(clazz.getName()
				+ Checker.CLASS_INSTANCE_DELIMITER + instance);
	}

	/**
	 * Create the object.
	 * 
	 * @param name
	 *            the logger name
	 */
	public AgentLogger(String name) {
		Checker.ensureNotNull(name, "name");

		logger = Logger.getLogger(name);
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public void fatal(String message) {
		logger.log(Level.SEVERE, message);
	}

	@Override
	public void fatal(String message, Throwable throwable) {
		logger.log(Level.SEVERE, message, throwable);
	}

	@Override
	public void error(String message) {
		logger.log(Level.SEVERE, message);
	}

	@Override
	public void error(String message, Throwable throwable) {
		logger.log(Level.SEVERE, message, throwable);
	}

	@Override
	public void warn(String message) {
		logger.log(Level.WARNING, message);
	}

	@Override
	public void warn(String message, Throwable throwable) {
		logger.log(Level.WARNING, message, throwable);
	}

	@Override
	public void info(String message) {
		logger.log(Level.INFO, message);
	}

	@Override
	public void info(String message, Throwable throwable) {
		logger.log(Level.INFO, message, throwable);
	}

	@Override
	public void debug(String message) {
		logger.log(Level.FINE, message);
	}

	@Override
	public void debug(String message, Throwable throwable) {
		logger.log(Level.FINE, message, throwable);
	}

	@Override
	public void trace(String message) {
		logger.log(Level.FINER, message);
	}

	@Override
	public void trace(String message, Throwable throwable) {
		logger.log(Level.FINER, message, throwable);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isLoggable(Level.INFO);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isLoggable(Level.FINE);
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isLoggable(Level.FINER);
	}
}
