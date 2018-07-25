package com.sky.lr.agent.fsm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;




import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.PreProcessor;
import com.sky.lr.agent.fsm.interf.StateListener;
import com.sky.lr.agent.fsm.interf.StateMachine;
import com.sky.lr.agent.fsm.interf.TransitionListener;
import com.sky.lr.utility.Checker;

public class NonDeterministicStateMachine extends ProcessorAdapter implements StateMachine {
	/** The transitions. */
	private final List<Transition> transitions = new LinkedList<Transition>();
	
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
	 */
	public NonDeterministicStateMachine(String name) {
		super(name);
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

		for (Transition tr : transitions) {
			if (tr.equals(transition)) {
				throw new FSMException("Transition already defined: "
						+ transition);
			}
		}

		transitions.add(transition);
	}

	/**
	 * Get all transitions for the event.
	 * 
	 * @param event
	 *            the event
	 * @return the transitions for the event, empty list if nothing is found
	 */
	public Collection<Transition> getTransitions(Event event) {
		Checker.ensureNotNull(event, "event");

		List<Transition> matchingTransitions = new LinkedList<Transition>();

		for (Transition transition : transitions) {
			//Only get the corresponding transitions, please note: we only compare the class name and event name now.
			//Maybe we need to identify them by unique hash code in the future
			if (event.equals(transition.getEvent())&&event.getEventName().equalsIgnoreCase(transition.getEvent().getEventName())) {
				matchingTransitions.add(transition);
			}
		}

		return matchingTransitions;
	}

	/**
	 * Get all defined transitions.
	 * 
	 * @return the transitions
	 */
	public Collection<Transition> getStateTransitions(State state, Event event) {
		// Don't copy, the class is package private
		Checker.ensureNotNull(state, "state");
		Checker.ensureNotNull(event, "event");

		List<Transition> matchingTransitions = new LinkedList<Transition>();

		for (Transition transition : transitions) {
			//Only get the corresponding transitions, please note: we only compare the class name and event name now.
			//Maybe we need to identify them by unique hash code in the future
			if (event.equals(transition.getEvent())
					&&event.getEventName().equalsIgnoreCase(transition.getEvent().getEventName())
					&&state.equals(transition.getSource())) {
				matchingTransitions.add(transition);
			}
		}

		return matchingTransitions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Transition transition : transitions) {
			builder.append(transition.toString());
			builder.append("\n");
		}

		return builder.toString();
	}
	
	public void start() throws FSMException {
		super.start();

		for (PreProcessor preprocessor : PreProcessors)
			preprocessor.start();

		if (currentState == null)
			throw new FSMException("Start state not defined");

		State target = new State("Idle");
		Event event = new StartEvent();

		String transStr = Transition.format(currentState, event, target);

		logger.info("Transition started:  " + transStr);
		notifyEnter(currentState, new StartEvent(),
				target);
		logger.info("Transition finished: " + transStr);
		
		setCurrentState(target);

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
	public void setStartState(State state) throws FSMException {
		// TODO Auto-generated method stub
		Checker.ensureNotNull(state, "start state");

		if (currentState != null)
			throw new FSMException("Start state already set: " + currentState);

		currentState = state;
	}
	
	public void setCurrentState(State state){
		//Set current state after the transition completed
	    this.currentState = state;	
	}

	/**
	 * Add a new state to the state machine. If the state is already defined, no
	 * exception will be thrown.
	 * 
	 * @param state
	 *            the state
	 */
	private void addStateInternal(State state) {
		//
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

		logger.info("Current State->"+ getActiveState().getName());
		//Get all transitions by event, it's just used for testing
		//Collection<Transition> transitionCollection = getTransitions(event);
		
		//Get the corresponding transtion according to the event and current state
		Collection<Transition> transitionCollection = getStateTransitions(getActiveState(), event);

		if (transitionCollection == null) {
			String msg = "No such transition: "
						+ Transition.format(currentState, event,
								preprocessedEvent);
			logger.warn(msg);
			throw new FSMException(msg);
		}

		return processInternal(transitionCollection, preprocessedEvent, event);
	}

	/**
	 * Preprocess event using all registered PreProcessors (recursive). Checker
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
	 * @param transitionCollection
	 *            There maybe many transitions in this collection since it's non deterministic state machine.
	 * @param eventToProcess
	 *            the event that should be processed
	 * @param matchedEvent
	 *            the event that matched, for logging purposes
	 * @return the final processed event
	 * @throws FSMException
	 *             if something fails
	 */
	private Event processInternal(Collection<Transition> transitionCollection, Event eventToProcess,
			Event matchedEvent) throws FSMException {
		State source, destination;
		Transition transition = null;
		//Which transition should be executed according to the current condition as well as the event content
		for(Iterator it=transitionCollection.iterator();it.hasNext();)
		{
			transition = (Transition) it.next();
			source = transition.getSource();
			destination = transition.getDestination();
			
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
			/*
			Swicth(current.condition(input)){
				//Select one path to trigger the transition
			}
			*/
			
			//Currently, we default there will a transition can match our request
			notifyExit(source, eventToProcess, destination);
			currentState = destination;
			notifyTransition(transition, source, eventToProcess, destination);
			notifyEnter(source, eventToProcess, destination);

			if (logger.isInfoEnabled()) {
				logger.info("Transition finished: " + transStr);
			}
		
		} 
		
		return eventToProcess;	

	}

	@Override
	public Set<State> getStates() {
		return null;
	}

	@Override
	public Set<Transition> getTransitions() {

		return null;
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
	protected Collection<Transition> getTransition(State state, Event event) {
		Checker.ensureNotNull(state, "state");
		Checker.ensureNotNull(event, "event");
		
		List<Transition> matchingTransitions = new LinkedList<Transition>();

		for (Transition transition : transitions) {
			//Only get the corresponding transitions, please note: we only compare the class name and event name now.
			//Maybe we need to identify them by unique hash code in the future
			if (state.equals(transition.getSource())&&event.equals(transition.getEvent())) {
				matchingTransitions.add(transition);
			}
		}

		return matchingTransitions;
	}

	@Override
	public boolean isInFinalState() {
		return currentState.isFinalState();
	}

	@Override
	public void addState(State state) throws FSMException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPreProcessor(PreProcessor preprocessor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(StateListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(TransitionListener listener) {
		// TODO Auto-generated method stub
		
	}
}
