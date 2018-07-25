package com.sky.lr.utility.logger;

import com.sky.lr.utility.Checker;


public class FSMLoggerFactory implements BasicLoggerFactory {
	@Override
	public BasicLogger getLogger(Class<?> clazz) {
		Checker.ensureNotNull(clazz, "class");

		return getLogger(clazz.getSimpleName());
	}

	@Override
	public BasicLogger getLogger(Class<?> clazz, String instance) {
		Checker.ensureNotNull(clazz, "class");
		Checker.ensureNotNull(instance, "instance");

		return getLogger(clazz.getSimpleName()
				+ Checker.CLASS_INSTANCE_DELIMITER + instance);
	}

	@Override
	public BasicLogger getLogger(String name) {
		Checker.ensureNotNull(name, "name");

		return new FSMLogger(name);
	}
}
