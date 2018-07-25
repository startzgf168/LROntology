package com.sky.lr.agent.fsm.interf;

public abstract class TypeEvent implements Event {
	@Override
	public int hashCode() {
		return 89658520 + getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		return getClass() == obj.getClass();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
	/**
	 * The type of the Event.
	 * 
	 * Note these are based on SCOR action
	 * we only use 4 type Event, including Source, Make, Deliver and Return
	 * 
	 */
	public static enum Type {
		DEFAULT,
		SOURCE,
		MAKE,
		DELIVER,
		RETURN,
		TIMEOUT,
		EXIT,
		TEST,
		COMPLETE,
		REPAIR,
		INVALID,
	}
}
