package com.sky.lr.agent.fsm;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.TypeEvent.Type;


public class NullEvent implements Event {
	/** The instance of the class. */
	public static final NullEvent instance = new NullEvent();

	/**
	 * Create the object.
	 */
	private NullEvent() {
		// Empty
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getEventType() {
		// TODO Auto-generated method stub
		return null;
	}
}
