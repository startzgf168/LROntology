package com.sky.lr.agent.fsm.interf;

import com.sky.lr.agent.fsm.State;

public interface StateListener {
	/**
	 * Get the listener type.
	 * 
	 * @return the type
	 */
	public StateListener.Type getType();

	/**
	 * The state was entered.
	 * 
	 * @param previous
	 *            the previous state
	 * @param event
	 *            the event
	 * @param current
	 *            the current state
	 */
	public void onStateEnter(State previous, Event event, State current);

	/**
	 * The state was exited.
	 * 
	 * @param current
	 *            the current state
	 * @param event
	 *            the event
	 * @param next
	 *            the next state
	 */
	public void onStateExit(State current, Event event, State next);

	/**
	 * The type of state listener to specify the behavior on loop transition.
	 * The state is not changed during such transition.
	 * 
	 * @author Jason Zhu
	 * 
	 * @see Transition#Transition(State, Event)
	 */
	public static enum Type {
		/** Process the listener on loop transition. */
		LOOP_PROCESS,

		/** Don't process the listener on loop transition. */
		LOOP_NO_PROCESS
	}
}
