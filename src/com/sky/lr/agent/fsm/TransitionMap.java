package com.sky.lr.agent.fsm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.utility.Checker;

public class TransitionMap {
	/** The transitions. */
	private final Map<Event, Transition> transitions = new HashMap<Event, Transition>();

	/**
	 * Create the object.
	 */
	public TransitionMap() {
		// Empty
	}

	/**
	 * Add a new transition.
	 * 
	 * @param transition
	 *            the transition
	 * @throws FSMException
	 *             if something fails
	 */
	public void addTransition(Transition transition) throws FSMException {
		Checker.ensureNotNull(transition, "transition");

		if (transitions.containsKey(transition.getEvent()))
			throw new FSMException("Transition already defined: " + transition);

		transitions.put(transition.getEvent(), transition);
	}

	/**
	 * Get a transition for the event.
	 * 
	 * @param event
	 *            the event
	 * @return the transition or null
	 */
	public Transition getTransition(Event event) {
		Checker.ensureNotNull(event, "event");

		Transition transition = transitions.get(event);

		if (transition == null)
			return null;

		return transition;
	}

	/**
	 * Get all defined transitions.
	 * 
	 * @return the transitions
	 */
	public Collection<Transition> getTransitions() {
		return transitions.values();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Transition transition : transitions.values()) {
			builder.append(transition.toString());
			builder.append("\n");
		}

		return builder.toString();
	}
}
