package com.sky.lr.agent.fsm.interf;

import java.util.Set;

import com.sky.lr.agent.fsm.FSMException;
import com.sky.lr.agent.fsm.State;
import com.sky.lr.agent.fsm.Transition;


public interface StateMachine extends Processor, AutoCloseable {
	/** The name of temporary generated initial state. */
	public static final String INITIAL_STATE_NAME = "INIT";

	/**
	 * Set the start state.
	 * 
	 * @param state
	 *            the state
	 * @see #start()
	 * @see #process(Event)
	 */
	public void setStartState(State state) throws FSMException;

	/**
	 * Add a new state.
	 * 
	 * @param state
	 *            the state
	 * @throws FSMException
	 *             if something fails
	 */
	public void addState(State state) throws FSMException;

	/**
	 * Add a new transition.
	 * 
	 * @param transition
	 *            the transition
	 * @throws FSMException
	 *             if something fails
	 */
	public void addTransition(Transition transition) throws FSMException;

	/**
	 * Add a new preprocessor. The preprocessors will be called in the
	 * registration order during preprocessing of the events.
	 * 
	 * The state machine is responsible for starting and stopping of the
	 * registered preprocessors.
	 * 
	 * @param preprocessor
	 *            the preprocessor
	 * 
	 * @see #process(Event)
	 * @see #start()
	 * @see #close()
	 */
	public void addPreProcessor(PreProcessor preprocessor);

	/**
	 * Add a new listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addListener(StateListener listener);

	/**
	 * Add a new listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addListener(TransitionListener listener);

	/**
	 * Listeners of start state will be notified with non-loop transition from
	 * temporary generated initial state and {@link StartEvent} object.
	 * 
	 * @throws FSMException
	 *             if something fails
	 * @see StartEvent
	 * @see #INITIAL_STATE_NAME
	 */
	@Override
	public void start() throws FSMException;

	@Override
	public void close();

	/**
	 * Get the currently active state. Intended use is in deterministic state
	 * machines, for nondeterministic state machines only one of the active
	 * states is returned.
	 * 
	 * @return the currently active state
	 * @see #getActiveStates()
	 */
	public State getActiveState();

	/**
	 * Get the currently active states. Intended use is in nondeterministic
	 * state machines.
	 * 
	 * @return the currently active states
	 * @see #getActiveState()
	 */
	public Set<State> getActiveStates();

	/**
	 * Get all defined states.
	 * 
	 * @return the states
	 */
	public Set<State> getStates();

	/**
	 * Get all defined transitions.
	 * 
	 * @return the transitions
	 */
	public Set<Transition> getTransitions();

	/**
	 * Is the active state a final state?
	 * 
	 * @return true if at least one of the active states is final otherwise
	 *         false
	 */
	public boolean isInFinalState();
}
