package com.sky.lr.utility.logger;

import com.sky.lr.utility.Checker;


public class AgentLoggerFactory implements BasicLoggerFactory{
	@Override
	public AgentLogger getLogger(Class<?> clazz) {
		Checker.ensureNotNull(clazz, "class");

		return new AgentLogger(clazz);
	}

	@Override
	public AgentLogger getLogger(Class<?> clazz, String instance) {
		Checker.ensureNotNull(clazz, "class");
		Checker.ensureNotNull(instance, "instance");

		return new AgentLogger(clazz, instance);
	}

	@Override
	public AgentLogger getLogger(String name) {
		Checker.ensureNotNull(name, "name");

		return new AgentLogger(name);
	}
}
