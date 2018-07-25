package com.sky.lr.agent.fsm;

import java.util.HashMap;
import java.util.Map;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.PreProcessor;
import com.sky.lr.utility.Checker;


public class EqualsPreProcessor extends PreProcessorAdapter {

	/** The processors. */
	private final Map<Event, Processor<? extends Event>> processors = new HashMap<Event, Processor<? extends Event>>();

	public EqualsPreProcessor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Add a new processor. The method is not thread safe.
	 * 
	 * @param event
	 *            the event
	 * @param processor
	 *            the processor
	 * @throws FSMException
	 *             if the processor is already defined
	 */
	public <T extends Event> void addProcessor(T event,
			PreProcessor.Processor<T> processor) throws FSMException {
		Checker.ensureNotNull(event, "event");
		Checker.ensureNotNull(processor, "processor");

		if (processors.containsKey(event))
			throw new FSMException("PreProcessor already defined: " + event);

		processors.put(event, processor);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Processor findProcessor(Event event) {
		Checker.ensureNotNull(event, "event");
		// TODO Auto-generated method stub
		return processors.get(event);
	}

}
