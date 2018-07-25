package com.sky.lr.agent.fsm;

import java.util.LinkedList;
import java.util.List;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.StateListener;
import com.sky.lr.utility.Checker;

public class State {
	/** The name of the state. */
	private final String name;

	/** The type of the state. */
	private final State.Type type;

	/** The listeners. */
	private final List<StateListener> listeners = new LinkedList<StateListener>();

	/**
	 * Create the object.
	 * 
	 * @param name
	 *            the name of the state
	 */
	public State(String name) {
		this(name, State.Type.DEFAULT);
	}

	/**
	 * Create the object.
	 * 
	 * @param name
	 *            the name of the state
	 * @param type
	 *            the type of the state
	 */
	public State(String name, State.Type type) {
		Checker.ensureNotNull(name, "name");

		this.name = name;
		this.type = type;
	}

	/**
	 * Add a new listener. The method is not thread safe.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addListener(StateListener listener) {
		Checker.ensureNotNull(listener, "listener");

		listeners.add(listener);
	}

	/**
	 * The state was entered, notify listeners. Internal use only.
	 * 
	 * @param loopTransition
	 *            this transition is a loop transition
	 * @param previous
	 *            the previous state
	 * @param event
	 *            the event
	 * @param current
	 *            the current state
	 */
	void notifyEnter(boolean loopTransition, State previous, Event event,
			State current) {
		for (StateListener listener : listeners) {
			if (loopTransition
					&& listener.getType() == StateListener.Type.LOOP_NO_PROCESS) {
				continue;
			}

			listener.onStateEnter(previous, event, current);
		}
	}

	/**
	 * The state was exited, notify listeners. Internal use only.
	 * 
	 * @param loopTransition
	 *            this transition is a loop transition
	 * @param current
	 *            the current state
	 * @param event
	 *            the event
	 * @param next
	 *            the next state
	 */
	void notifyExit(boolean loopTransition, State current, Event event,
			State next) {
		for (StateListener listener : listeners) {
			if (loopTransition
					&& listener.getType() == StateListener.Type.LOOP_NO_PROCESS) {
				continue;
			}

			listener.onStateExit(current, event, next);
		}
	}

	/**
	 * Get name of the state.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get type of the state.
	 * 
	 * @return the type
	 */
	public State.Type getType() {
		return type;
	}

	/**
	 * Check the state is final.
	 * 
	 * @return the flag
	 */
	public boolean isFinalState() {
		return type == State.Type.FINAL;
	}

	/**
	 * Get the hash code. Only name of the state is used for creating hash code.
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		return 3452542 + name.hashCode();
	}

	/**
	 * Compare the objects using internal fields. FSM uses this method while
	 * building the state machine to ensure all states are unique.
	 * 
	 * Only the name of the states is used during the comparison.
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

		if (!(object instanceof State))
			return false;

		State other = (State) object;
		return name.equals(other.name);
	}

	/**
	 * The string representation of the object. It is expected the name of the
	 * state is returned.
	 * 
	 * @return the string representation
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * The type of the states.
	 * 
	 * Note start state has meaning only for FSM, it's the default state during
	 * the processing. Both default and final state can be the start state.
	 * 
	 * @see StateMachine#setStartState(State)
	 */
	public static enum Type {
		/** The default state (non-final). */
		DEFAULT,

		/** The final state. */
		FINAL
	}
}
