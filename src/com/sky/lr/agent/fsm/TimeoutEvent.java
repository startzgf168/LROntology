package com.sky.lr.agent.fsm;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.utility.Checker;

public class TimeoutEvent implements Event {
	/** The instance of the object for use in timeout state machine. */
	static TimeoutEvent instance_LOOP_RESTART = new TimeoutEvent(1,
			TimeoutEvent.Type.LOOP_RESTART);

	/** The instance of the object for use in timeout state machine. */
	static TimeoutEvent instance_LOOP_NO_RESTART = new TimeoutEvent(1,
			TimeoutEvent.Type.LOOP_NO_RESTART);
	
	/** The timeout in milliseconds. */
	private final long timeout;

	/** The type of the event. */
	private final TimeoutEvent.Type type;
	
	//The event name 
	private final String eventName ;
	
	/**
	 * Create instance of this class.
	 * 
	 * @param timeout
	 *            the timeout in milliseconds, must be positive
	 * @param type
	 *            the type of the event
	 * @return the instance
	 */
	public static TimeoutEvent instance(long timeout, TimeoutEvent.Type type) {
		return new TimeoutEvent(timeout, type);
	}
	
	/**
	 * Create the object.
	 * 
	 * @param timeout
	 *            the timeout in milliseconds, must be positive
	 * @param type
	 *            the type of the event
	 */
	public TimeoutEvent(long timeout, TimeoutEvent.Type type) {
		// Zero passed to Java timer works too, but let's forbid it
		if (timeout <= 0)
			throw new IllegalArgumentException("Timeout value must be positive");

		Checker.ensureNotNull(type, "type");
        //Default timeout event name is Timeout
		this.eventName = "Timeout";
		this.timeout = timeout;
		this.type = type;
	}
	
	public long getTimeout() {
		return timeout;
	}

	public TimeoutEvent.Type getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return 34893234 + type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof TimeoutEvent))
			return false;

		TimeoutEvent other = (TimeoutEvent) obj;
		return type == other.type;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + type + ", " + timeout + ")";
	}
	/*
	 * For a loop transition, the timeout would be always restarted without any real
	 * change of the state. However, for a non-loop transition, it will still keep the timer to schedule the timeout
	 * */
	public static enum Type {
		/** Restart the timeout on loop transition. */
		LOOP_RESTART,

		/**
		 * Don't restart the timeout on loop transition and let the previous to
		 * remain active.
		 */
		LOOP_NO_RESTART
	}
	
	//The following two methods won't be used within this class
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return this.eventName;
	}

	@Override
	//Not used, so return null
	public com.sky.lr.agent.fsm.interf.TypeEvent.Type getEventType() {
		// TODO Auto-generated method stub
		return null;
	}
}
