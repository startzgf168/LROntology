package com.sky.lr.agent.fsm;


import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.PreProcessor;
import com.sky.lr.utility.Checker;


abstract class PreProcessorAdapter extends ProcessorAdapter implements PreProcessor{

	public PreProcessorAdapter(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	// The input event can be whatever event, the processor is searched based on
	// information from it. It should always work since findProcessor() does
	// checks.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Event process(Event event) throws FSMException {
		Checker.ensureNotNull(event, "event");

		Processor processor = findProcessor(event);
		if (processor == null)
			return event;

		Event resultEvent = null;

		try {
			resultEvent = processor.process(event);
		} catch (RuntimeException e) {
			Checker.logExceptionInClientCallback(logger,
					"Preprocess callback failed: " + event, e);
			throw e;
		}

		if (!event.equals(resultEvent) && logger.isInfoEnabled()) {
			logger.info("Event preprocessed: "
					+ Transition.format(event, resultEvent));
		}

		return resultEvent;
	}

	/**
	 * Find appropriate processor for an event.
	 * 
	 * @param event
	 *            the event
	 * @return the processor or null if the processor was not found
	 */
	// The input event can have whatever type
	@SuppressWarnings("rawtypes")
	abstract protected PreProcessor.Processor findProcessor(Event event);
}
