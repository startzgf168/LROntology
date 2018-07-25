package com.sky.lr.agent.fsm;

import java.util.LinkedList;
import java.util.List;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.TransitionListener;
import com.sky.lr.utility.Checker;


public class Transition {
	/** The delimiter for string representation. */
	private static final String TR = " -> ";

	/** The source state. */
	private final State source;

	/** The event. */
	private final Event event;

	/** The destination state. */
	private final State destination;

	/** The listeners. */
	private final List<TransitionListener> listeners = new LinkedList<TransitionListener>();

	/**
	 * Create the object.
	 * 
	 * @param source
	 *            the source state
	 * @param event
	 *            the event
	 * @param destination
	 *            the destination state
	 */
	public Transition(State source, Event event, State destination) {
		Checker.ensureNotNull(source, "source state");
		Checker.ensureNotNull(event, "event");
		Checker.ensureNotNull(destination, "destination state");

		this.source = source;
		this.event = event;
		this.destination = destination;
	}

	/**
	 * Create the object. The loop transition, the source and destination states
	 * are the same.
	 * 
	 * 
	 * @param state
	 *            the source and destination state
	 * @param event
	 *            the event
	 */
	public Transition(State state, Event event) {
		this(state, event, state);
	}

	/**
	 * Get the source state.
	 * 
	 * @return the transition source state
	 */
	public State getSource() {
		return source;
	}

	/**
	 * Get the transition event.
	 * 
	 * @return the transition event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Get the transition destination state.
	 * 
	 * @return the transition destination state
	 */
	public State getDestination() {
		return destination;
	}

	/**
	 * Add a new listener. The method is not thread safe.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addListener(TransitionListener listener) {
		Checker.ensureNotNull(listener, "listener");

		listeners.add(listener);
	}

	/**
	 * The transition was processed, notify listeners. Internal use only.
	 * 
	 * @param source
	 *            the source state
	 * @param event
	 *            the event
	 * @param destination
	 *            the destination state
	 */
	void notifyTransition(State source, Event event, State destination) {
		for (TransitionListener listener : listeners) {
			listener.onTransition(source, event, destination);
		}
	}

	/**
	 * Get the hash code.
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + destination.hashCode();
		result = prime * result + event.hashCode();
		result = prime * result + source.hashCode();
		return result;
	}

	/**
	 * Compare the objects using internal fields. FSM uses this method while
	 * building the state machine to ensure all transitions are unique.
	 * 
	 * @param object
	 *            the object
	 * @return true if the objects are same, otherwise false
	 * @see StateMachine#process(Event)
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;

		if (object == null)
			return false;

		if (!(object instanceof Transition))
			return false;

		Transition other = (Transition) object;
		return source.equals(other.getSource())
				&& event.equals(other.getEvent())
				&& destination.equals(other.getDestination());
	}

	/**
	 * The string representation of the object. It is expected the name of the
	 * internal fields are returned.
	 * 
	 * @return the string representation
	 */
	@Override
	public String toString() {
		return format(source, event, destination);
	}

	/**
	 * Helper method to format transition parameters to the string
	 * representation.
	 * 
	 * @param source
	 *            the source state
	 * @param event
	 *            the input event
	 * @param destination
	 *            the destination state
	 * @return the string representation
	 */
	static String format(State source, Event event, State destination) {
		return source + TR + event + "("+event.getEventName() +")"+ TR + destination;
	}

	/**
	 * Helper method to format transition parameters to the string
	 * representation.
	 * 
	 * @param source
	 *            the source state
	 * @param matchedEvent
	 *            the input event that matched
	 * @param eventToProcess
	 *            the event that will be processed
	 * @param destination
	 *            the destination state
	 * @return the string representation
	 */
	static String format(State source, Event matchedEvent,
			Event eventToProcess, State destination) {
		return source + TR + format(matchedEvent, eventToProcess) + TR
				+ destination;
	}

	/**
	 * Helper method to format transition parameters to the string
	 * representation.
	 * 
	 * @param source
	 *            the source state
	 * @param event
	 *            the input event
	 * @return the string representation
	 */
	static String format(State source, Event event) {
		return source + TR + event;
	}

	/**
	 * Helper method to format transition parameters to the string
	 * representation.
	 * 
	 * @param source
	 *            the source state
	 * @param matchedEvent
	 *            the input event that matched
	 * @param eventToProcess
	 *            the event that will be processed
	 * @return the string representation
	 */
	static String format(State source, Event matchedEvent, Event eventToProcess) {
		return source + TR + format(matchedEvent, eventToProcess);
	}

	/**
	 * Helper method to format transition parameters to the string
	 * representation.
	 * 
	 * @param matchedEvent
	 *            the input event that matched
	 * @param eventToProcess
	 *            the event that will be processed
	 * @return the string representation
	 */
	static String format(Event matchedEvent, Event eventToProcess) {
		return matchedEvent + TR + eventToProcess;
	}
}
