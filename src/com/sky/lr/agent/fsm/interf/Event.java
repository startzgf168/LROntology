package com.sky.lr.agent.fsm.interf;

import com.sky.lr.agent.fsm.interf.TypeEvent.Type;

public interface Event {
	/**
	 * Get the hash code of the object.
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode();

	/**
	 * Compare the objects using internal fields. FSM uses this method while
	 * determining which transition process.
	 * 
	 * @param object
	 *            the object
	 * @return true if the objects are same, otherwise false
	 */
	public boolean equals(Object object);

	/**
	 * The string representation of the object. It is expected the class name
	 * and all fields used in equals() are listed.
	 * 
	 * @return the string representation
	 */
	public String toString();
	
	public String getEventName();
	
	public Type getEventType();
	
}
