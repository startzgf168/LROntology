package com.sky.lr.agent.fsm.interf;

import com.sky.lr.agent.fsm.FSMException;


public interface Processor {
	/**
	 * Get the name of the processor.
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Building of the processor is finished, prepare it to the events
	 * processing. It is expected this method is called just once.
	 * 
	 * @see #close()
	 */
	public void start() throws FSMException;

	/**
	 * Finish processing of the events and free all allocated resources. This
	 * method can be called multiple times.
	 * 
	 * Calling methods of the object after its {@link #close()} should be
	 * avoided. The behavior is not defined and may cause unpredictable
	 * behavior, e.g. {@link NullPointerException}.
	 * 
	 * @see #start()
	 */
	public void close();

	/**
	 * Process the event.
	 * 
	 * @param event
	 *            the input event
	 * @return the processed event
	 * @throws FsmException
	 *             if something fails
	 */
	public Event process(Event event) throws FSMException;
}
