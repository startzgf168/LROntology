package com.sky.lr.agent.fsm;


import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.Processor;
import com.sky.lr.utility.Checker;
import com.sky.lr.utility.logger.BasicLogger;

abstract class ProcessorAdapter implements Processor {

	/** The logger. */
	protected final BasicLogger logger;

	/** The name of the preprocessor. */
	private final String name;

	/**
	 * Create the object.
	 * 
	 * @param name
	 *            the name of the event processor
	 */
	public ProcessorAdapter(String name) {
		Checker.ensureNotNull(name, "name");

		this.name = name;
		logger = ConfigurationFSM.getLogger(getClass(), name);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void start() throws FSMException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public Event process(Event event) throws FSMException {
		// TODO Auto-generated method stub
		return null;
	}

}
