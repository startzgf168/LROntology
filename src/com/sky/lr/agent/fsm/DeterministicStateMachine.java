package com.sky.lr.agent.fsm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.PreProcessor;
import com.sky.lr.agent.fsm.interf.StateListener;
import com.sky.lr.agent.fsm.interf.StateMachine;
import com.sky.lr.agent.fsm.interf.TransitionListener;
import com.sky.lr.utility.Checker;


public class DeterministicStateMachine extends ProcessorAdapter implements StateMachine{
	
	/** The transitions. */
	private final Map<State, TransitionMap> stateTransitions = new HashMap<State, TransitionMap>();

	/** The current state. */
	private State currentState = null;

	/** The listeners. */
	private final List<StateListener> stateListeners = new LinkedList<StateListener>();

	/** The listeners. */
	private final List<TransitionListener> transitionListeners = new LinkedList<TransitionListener>();

	/** The PreProcessors of events. */
	private final List<PreProcessor> PreProcessors = new LinkedList<PreProcessor>();

	/**
	 * Create the object.
	 * 
	 * @param name
	 *            the name of the state machine
	 */
	public DeterministicStateMachine(String name) {
		super(name);
	}

	@Override
	public void setStartState(State state) throws FSMException {
		Checker.ensureNotNull(state, "start state");

		if (currentState != null)
			throw new FSMException("Start state already set: " + currentState);

		currentState = state;
		addStateInternal(state);
	}

	@Override
	public void start() throws FSMException {
		super.start();

		for (PreProcessor preprocessor : PreProcessors)
			preprocessor.start();

		if (currentState == null)
			throw new FSMException("Start state not defined");

		State source = new State(INITIAL_STATE_NAME);
		Event event = new StartEvent();

		String transStr = Transition.format(source, event, currentState);

		logger.info("Transition started:  " + transStr);
		notifyEnter(new State(INITIAL_STATE_NAME), new StartEvent(),
				currentState);
		logger.info("Transition finished: " + transStr);
	}

	@Override
	public void close() {
		for (PreProcessor preprocessor : PreProcessors)
			preprocessor.close();

		super.close();
	}

	@Override
	public void addState(State state) throws FSMException {
		Checker.ensureNotNull(state, "state");

		if (stateTransitions.containsKey(state))
			throw new FSMException("State redefined: " + state);

		addStateInternal(state);
	}

	/**
	 * Add a new state to the state machine. If the state is already defined, no
	 * exception will be thrown.
	 * 
	 * @param state
	 *            the state
	 */
	private void addStateInternal(State state) {
		if (stateTransitions.containsKey(state))
			return;

		stateTransitions.put(state, new TransitionMap());
	}

	@Override
	public void addTransition(Transition transition) throws FSMException {
		Checker.ensureNotNull(transition, "transition");

		addStateInternal(transition.getSource());
		stateTransitions.get(transition.getSource()).addTransition(transition);
		addStateInternal(transition.getDestination());
	}

	@Override
	public void addPreProcessor(PreProcessor preprocessor) {
		Checker.ensureNotNull(preprocessor, "preprocessor");

		PreProcessors.add(preprocessor);
	}

	@Override
	public void addListener(StateListener listener) {
		Checker.ensureNotNull(listener, "listener");

		stateListeners.add(listener);
	}

	@Override
	public void addListener(TransitionListener listener) {
		Checker.ensureNotNull(listener, "listener");

		transitionListeners.add(listener);
	}

	/**
	 * Process all state enter callbacks.
	 * 
	 * @param previous
	 *            the previous state
	 * @param event
	 *            the event
	 * @param current
	 *            the current state
	 * @throws FSMException
	 *             if something fails
	 */
	void notifyEnter(State previous, Event event, State current)
			throws FSMException {
		boolean loopTransition = previous.equals(current);

		try {
			current.notifyEnter(loopTransition, previous, event, current);

			for (StateListener listener : stateListeners) {
				if (loopTransition
						&& listener.getType() == StateListener.Type.LOOP_NO_PROCESS) {
					continue;
				}

				listener.onStateEnter(previous, event, current);
			}
		} catch (RuntimeException e) {
			Checker.logExceptionInClientCallback(
					logger,
					"State enter callback failed: "
							+ Transition.format(previous, event, current), e);
			throw e;
		}
	}

	/**
	 * Process all state exit callbacks.
	 * 
	 * @param current
	 *            the current state
	 * @param event
	 *            the event
	 * @param next
	 *            the next state
	 * @throws FSMException
	 *             if something fails
	 */
	void notifyExit(State current, Event event, State next) throws FSMException {
		boolean loopTransition = current.equals(next);

		try {
			current.notifyExit(loopTransition, current, event, next);

			for (StateListener listener : stateListeners) {
				if (loopTransition
						&& listener.getType() == StateListener.Type.LOOP_NO_PROCESS) {
					continue;
				}

				listener.onStateExit(current, event, next);
			}
		} catch (RuntimeException e) {
			Checker.logExceptionInClientCallback(
					logger,
					"State exit callback failed: "
							+ Transition.format(current, event, next), e);
			throw e;
		}
	}

	/**
	 * Process all transition callbacks.
	 * 
	 * @param transition
	 *            the transition
	 * @param source
	 *            the source state
	 * @param event
	 *            the event
	 * @param destination
	 *            the destination state
	 * @throws FSMException
	 *             if something fails
	 */
	void notifyTransition(Transition transition, State source, Event event,
			State destination) throws FSMException {
		try {
			transition.notifyTransition(source, event, destination);

			for (TransitionListener listener : transitionListeners) {
				listener.onTransition(source, event, destination);

			}
		} catch (RuntimeException e) {
			Checker.logExceptionInClientCallback(
					logger,
					"Transition callback failed: "
							+ Transition.format(source, event, destination), e);
			throw e;
		}
	}

	@Override
	public State getActiveState() {
		return currentState;
	}

	@Override
	public Set<State> getActiveStates() {
		Set<State> states = new HashSet<State>();
		if (currentState == null)
			return states;

		states.add(currentState);
		return states;
	}

	@Override
	public Event process(Event event) throws FSMException {
		processCheck(event);

		Event preprocessedEvent = preprocessEvent(event);
		if (preprocessedEvent == null)
			return null;

		Transition transition = stateTransitions.get(currentState)
				.getTransition(preprocessedEvent);

		if (transition == null) {
			preprocessedEvent = new OtherEvent(preprocessedEvent);
			transition = stateTransitions.get(currentState).getTransition(
					preprocessedEvent);

			if (transition == null) {
				String msg = "No such transition: "
						+ Transition.format(currentState, event,
								preprocessedEvent);
				logger.warn(msg);
				throw new FSMException(msg);
			}
		}

		return processInternal(transition, preprocessedEvent, event);
	}

	/**
	 * Preprocess event using all registered PreProcessors (recursive). Helper
	 * method.
	 * 
	 * @param event
	 *            the event
	 * @return the original event, a newly generated event or null to ignore the
	 *         event
	 * @throws FSMException
	 *             if something fails
	 */
	private Event preprocessEvent(Event event) throws FSMException {
		Checker.ensureNotNull(event, "event");

		Event preprocessedEvent = event;

		for (PreProcessor PreProcessor : PreProcessors) {
			preprocessedEvent = PreProcessor.process(preprocessedEvent);

			if (preprocessedEvent == null)
				return null;
		}

		return preprocessedEvent;
	}

	/**
	 * Check the input event and the internal state before processing the event.
	 * 
	 * @param event
	 *            the input event
	 * @throws FSMException
	 *             if the event can't be processed for any reason
	 */
	private void processCheck(Event event) throws FSMException {
		if (event == null) {
			String msg = "Event must not be null: "
					+ Transition.format(currentState, null);
			logger.error(msg);
			throw new NullPointerException(msg);
		}

		if (currentState == null) {
			String msg = "Current/start state is null: "
					+ Transition.format(currentState, event);

			logger.error(msg);
			throw new FSMException(msg);
		}
	}

	/**
	 * Real processing of the event.
	 * 
	 * @param transition
	 *            the transition that should be processed
	 * @param eventToProcess
	 *            the event that should be processed
	 * @param matchedEvent
	 *            the event that matched, for logging purposes
	 * @return the final processed event
	 * @throws FSMException
	 *             if something fails
	 */
	private Event processInternal(Transition transition, Event eventToProcess,
			Event matchedEvent) throws FSMException {
		State source = transition.getSource();
		State destination = transition.getDestination();

		String transStr = "";
		if (logger.isInfoEnabled()) {
			// == is correct, equals may not consider an updated parameter
			if (eventToProcess == matchedEvent) {
				transStr = Transition.format(source, eventToProcess,
						destination);
			} else {
				transStr = Transition.format(source, matchedEvent,
						eventToProcess, destination);
			}

			logger.info("Transition started:  " + transStr);
		}

		notifyExit(source, eventToProcess, destination);
		currentState = destination;
		notifyTransition(transition, source, eventToProcess, destination);
		notifyEnter(source, eventToProcess, destination);

		if (logger.isInfoEnabled()) {
			logger.info("Transition finished: " + transStr);
		}

		return eventToProcess;
	}

	@Override
	public Set<State> getStates() {
		return stateTransitions.keySet();
	}

	@Override
	public Set<Transition> getTransitions() {
		Set<Transition> transitions = new HashSet<Transition>();

		for (Entry<State, TransitionMap> map : stateTransitions.entrySet()) {
			transitions.addAll(map.getValue().getTransitions());
		}

		return transitions;
	}

	/**
	 * Get transition of a specified event type defined for a state.
	 * 
	 * @param state
	 *            the state, it must be defined in the state machine
	 * @param event
	 *            the event
	 * @return the transition or null if not defined
	 */
	protected Transition getTransition(State state, Event event) {
		Checker.ensureNotNull(state, "state");
		Checker.ensureNotNull(event, "event");

		return stateTransitions.get(state).getTransition(event);
	}

	@Override
	public boolean isInFinalState() {
		return currentState.isFinalState();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (TransitionMap transitions : stateTransitions.values()) {
			builder.append(transitions.toString());
		}

		return builder.toString();
	}
	
}
