package com.sky.lr.agent.fsm.interf;

import com.sky.lr.agent.fsm.State;


public interface TransitionListener {
	/**
	 * The transition was processed.
	 * 
	 * @param source
	 *            the source state
	 * @param event
	 *            the event
	 * @param destination
	 *            the destination state
	 */
	public void onTransition(State source, Event event, State destination);
}
