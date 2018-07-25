package com.sky.lr.agent.fsm;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.TypeEvent.Type;
import com.sky.lr.utility.Checker;


public class OtherEvent implements Event {
	/** The instance of the object for building of the state machine. */
	public static final OtherEvent instance = new OtherEvent(NullEvent.instance);

	/** The source event that caused this transition. */
	private final Event sourceEvent;

	/**
	 * Create the object. Internal use only. For internal use while processing
	 * the transitions.
	 * 
	 * @param sourceEvent
	 *            the source event that caused this transition
	 */
	OtherEvent(Event sourceEvent) {
		Checker.ensureNotNull(sourceEvent, "source event");

		this.sourceEvent = sourceEvent;
	}

	// HashCode() and equals() from super are enough

	/**
	 * Get the event that caused the transition.
	 * 
	 * @return the event
	 */
	public Event getSourceEvent() {
		return sourceEvent;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + sourceEvent + ")";
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
