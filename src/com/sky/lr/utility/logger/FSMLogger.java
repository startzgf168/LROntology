package com.sky.lr.utility.logger;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sky.lr.utility.Checker;


public class FSMLogger implements BasicLogger{
	/** The logger name. */
	private final String name;

	/**
	 * Create the object.
	 * 
	 * @param name
	 *            the logger name
	 */
	public FSMLogger(String name) {
		Checker.ensureNotNull(name, "name");

		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void fatal(String message) {
		log(System.err, "FATAL", message, null);
	}

	@Override
	public void fatal(String message, Throwable throwable) {
		log(System.err, "FATAL", message, throwable);
	}

	@Override
	public void error(String message) {
		log(System.err, "ERROR", message, null);
	}

	@Override
	public void error(String message, Throwable throwable) {
		log(System.err, "ERROR", message, throwable);
	}

	@Override
	public void warn(String message) {
		log(System.err, " WARN", message, null);
	}

	@Override
	public void warn(String message, Throwable throwable) {
		log(System.err, " WARN", message, throwable);
	}

	@Override
	public void info(String message) {
		log(System.out, " INFO", message, null);
	}

	@Override
	public void info(String message, Throwable throwable) {
		log(System.out, " INFO", message, throwable);
	}

	@Override
	public void debug(String message) {
		log(System.out, "DEBUG", message, null);
	}

	@Override
	public void debug(String message, Throwable throwable) {
		log(System.out, "DEBUG", message, throwable);
	}

	@Override
	public void trace(String message) {
		log(System.out, "TRACE", message, null);
	}

	@Override
	public void trace(String message, Throwable throwable) {
		log(System.out, "TRACE", message, throwable);
	}

	@Override
	public boolean isInfoEnabled() {
		return true;
	}

	@Override
	public boolean isDebugEnabled() {
		return true;
	}

	@Override
	public boolean isTraceEnabled() {
		return true;
	}

	/**
	 * Log the message.
	 * 
	 * @param stream
	 *            the output stream
	 * @param severity
	 *            the severity of the message
	 * @param message
	 *            the message
	 * @param throwable
	 *            the exception or null
	 */
	private void log(PrintStream stream, String severity, String message,
			Throwable throwable) {
		// Formatter must be local, format() modifies member variables
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

		String date = dateFormat.format(new Date());
		String thread = Thread.currentThread().getName();

		stream.println(date + " [" + thread + "] " + severity + " " + name
				+ " - " + message);

		if (throwable != null)
			throwable.printStackTrace(stream);
	}
}
