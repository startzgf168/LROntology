package com.sky.lr.agent.fsm;

import java.util.Set;

import com.sky.lr.agent.fsm.interf.Event;

public class SynchNonDeterministicStateMachine extends NonDeterministicStateMachine {

	public SynchNonDeterministicStateMachine(String name) {
		super(name);
		// TODO Auto-generated constructor stub
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
