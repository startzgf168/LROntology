package com.sky.lr.agent.fsm;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.StateListener;
import com.sky.lr.utility.Checker;

public class StateAdapter implements StateListener {

	/** The type of the listener. */
	private final StateListener.Type type;

	/**
	 * Create the object. The listener will be processed even on loop
	 * transitions.
	 * 
	 * @see StateListener.Type
	 */
	public StateAdapter() {
		this(StateListener.Type.LOOP_PROCESS);
	}

	/**
	 * Create the object.
	 * 
	 * @param type
	 *            the type of the listener
	 */
	public StateAdapter(StateListener.Type type) {
		Checker.ensureNotNull(type, "type");

		this.type = type;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public void onStateEnter(State previous, Event event, State current) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStateExit(State current, Event event, State next) {
		// TODO Auto-generated method stub

	}

}
