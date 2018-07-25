package com.sky.lr.agent.fsm;

import java.util.Set;

import com.sky.lr.agent.fsm.interf.Event;


public class SynchDeterministicStateMachine extends DeterministicStateMachine{
	/**
	 * Create the object.
	 * 
	 * @param name
	 *            the name of the state machine
	 */
	public SynchDeterministicStateMachine(String name) {
		super(name);
	}

	@Override
	public synchronized Event process(Event event) throws FSMException {
		return super.process(event);
	}

	@Override
	public synchronized State getActiveState() {
		return super.getActiveState();
	}

	@Override
	public synchronized Set<State> getActiveStates() {
		return super.getActiveStates();
	}

	@Override
	public synchronized boolean isInFinalState() {
		return super.isInFinalState();
	}
}
