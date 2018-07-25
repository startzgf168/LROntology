package com.sky.lr.agent.fsm.interf;

import com.sky.lr.agent.fsm.FSMException;


public interface PreProcessor extends Processor{
	/**
	 * Process or preprocess the event.
	 * 
	 * @param event
	 *            the input event, a newly generated event or NullEvent to
	 *            ignore the event
	 * @throws FSMException
	 *             if something fails
	 * @see NullEvent
	 */
	@Override
	public Event process(Event event) throws FSMException;

	/**
	 * The real preprocessor of typed events.
	 * 
	 * It is a responsibility of the client code to throw no runtime exceptions
	 * in callbacks. Any unhandled exception can stop an internal thread and
	 * break whole processing of events. It is generally bad to handle all
	 * possible exceptions to hide errors so it is not implemented in the
	 * library at all.
	 * 
	 */
	public static interface Processor<T extends Event> {
		/**
		 * Preprocess the event before passing it to the state machine.
		 * 
		 * @param event
		 *            the input event
		 * @return the input event, a newly generated event or null to ignore
		 *         the event
		 * @see NullEvent
		 */
		public Event process(T event);
	}
}
