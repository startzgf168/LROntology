package com.sky.lr.agent.fsm;

import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import com.sky.lr.agent.fsm.TimeoutEvent.Type;
import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.utility.Checker;

public class TimeoutSynchNonDeterministicStateMachine extends SynchNonDeterministicStateMachine{
	/** The timer for scheduling timeout transitions. */
	private Timer timer = null;

	/** The inner thread used for timer is a daemon thread. */
	private final boolean daemon;

	/**
	 * The unique ID of the last state enter to forbid timeout in incorrect
	 * state. Instance of any object is enough since every two objects are
	 * different. Updated during all state enters.
	 */
	private Object lastStateEnterId = new Object();

	/**
	 * The unique ID of the last state enter to forbid timeout in incorrect
	 * state. Instance of any object is enough since every two objects are
	 * different. Updated only during non-loop state enters.
	 */
	private Object lastStateEnterIdNonLoop = new Object();
	
	public TimeoutSynchNonDeterministicStateMachine(String name) {
		this(name, false);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create the state machine.
	 * 
	 * @param name
	 *            the name of the state machine
	 * @param daemon
	 *            the inner thread used for timer is a daemon thread
	 * 
	 * @see java.lang.Thread
	 */
	public TimeoutSynchNonDeterministicStateMachine(String name, boolean daemon) {
		super(name);
		this.daemon = daemon;
	}
	
	/**
	 * Checker method to get the name of the internally executed thread.
	 * 
	 * @return the thread name
	 */
	String getThreadName() {
		return getClass().getSimpleName() + Checker.CLASS_INSTANCE_DELIMITER
				+ getName();
	}

	@Override
	public synchronized void start() throws FSMException {
		// The timer must be created first, super.start() generates start event
		if (timer != null) {
			throw new FSMException(
					"Timer already running, state machine probably started twice");
		}

		timer = new Timer(getThreadName(), daemon);

		try {
			super.start();
		} catch (FSMException e) {
			timer.cancel();
			timer = null;
			throw e;
		}
	}

	@Override
	public synchronized void close() {
		try {
			super.close();
		} finally {
			if (timer != null) {
				timer.cancel();
				timer = null;
			}
		}
	}
	
	@Override
	synchronized void notifyEnter(State previous, Event event, State current)
			throws FSMException {
		super.notifyEnter(previous, event, current);

		if (timer == null) {
			throw new FSMException(
					"Timer is not running while processing, state machine probably not started yet");
		}

		boolean loopTransition = previous.equals(current);

		lastStateEnterId = new Object();

		if (!loopTransition)
			lastStateEnterIdNonLoop = new Object();
		//Only trigger the timer while entering the delivering state
		scheduleTimeoutTransition(loopTransition,
				TimeoutEvent.instance_LOOP_RESTART);
		scheduleTimeoutTransition(loopTransition,
				TimeoutEvent.instance_LOOP_NO_RESTART);
	}
	
	/**
	 * Schedule a new timeout transition, a state was entered.
	 * 
	 * @param loopTransition
	 *            the transition was loop transition
	 * @param event
	 *            the timeout event
	 */
	private synchronized void scheduleTimeoutTransition(boolean loopTransition,
			TimeoutEvent event) {
		Collection<Transition> timeoutTransition = getTransition(getActiveState(), event);

		if (timeoutTransition == null) {
			return; // Correct
		}
		//We need to start all the timer for the matched transitions since the FSM is non-deterministic state machine
        for(Transition trans : timeoutTransition)
        {
    		TimeoutEvent timeoutEvent = (TimeoutEvent) trans.getEvent();

    		if (loopTransition
    				&& timeoutEvent.getType() == TimeoutEvent.Type.LOOP_NO_RESTART) {
    			return; // Correct
    		}

    		timer.schedule(new TimeoutTask(trans, lastStateEnterId,
    				lastStateEnterIdNonLoop), timeoutEvent.getTimeout());
        }

	}
	
	/**
	 * Process the timeout transition.
	 * 
	 * @param timeoutTransition
	 *            the transition with TimeoutEvent that should be processed
	 * @param stateEnterId
	 *            the unique ID of the state enter
	 * @param stateEnterIdNonLoop
	 *            the unique ID of the non-loop state enter
	 */
	private synchronized void proccessTimeoutTransition(
			Transition timeoutTransition, Object stateEnterId,
			Object stateEnterIdNonLoop) {
		// Different state before timeout occurred -> ignore the timeout
		if (stateEnterIdNonLoop != lastStateEnterIdNonLoop) {
			return;
		}

		// Still in the same state, loop transitions occurred, but loop restarts
		// the timeout -> ignore the timeout
		if (stateEnterId != lastStateEnterId
				&& ((TimeoutEvent) timeoutTransition.getEvent()).getType() == Type.LOOP_RESTART) {
			return;
		}

		try {
			process(timeoutTransition.getEvent());
		} catch (FSMException e) {
			logger.error("Processing of timeout failed: " + timeoutTransition);
			// No other possibility to signal error inside the timer thread
		}
	}
	
	private class TimeoutTask extends TimerTask {
		/** The transition with TimeoutEvent that should be processed. */
		private final Transition timeoutTransition;

		/** The unique ID of the state enter. */
		private final Object stateEnterId;

		/** The unique ID of the non-loop state enter. */
		private final Object stateEnterIdNonLoop;

		/**
		 * Create the object.
		 * 
		 * @param timeoutTransition
		 *            the transition with TimeoutEvent that should be processed
		 * @param stateEnterId
		 *            the unique ID of the state enter
		 * @param stateEnterIdNonLoop
		 *            the unique ID of the non-loop state enter
		 */
		public TimeoutTask(Transition timeoutTransition, Object stateEnterId,
				Object stateEnterIdNonLoop) {
			Checker.ensureNotNull(timeoutTransition, "timeoutTransition");
			Checker.ensureNotNull(stateEnterId, "stateEnterId");

			this.timeoutTransition = timeoutTransition;
			this.stateEnterId = stateEnterId;
			this.stateEnterIdNonLoop = stateEnterIdNonLoop;
		}

		@Override
		public void run() {
			try {
				proccessTimeoutTransition(timeoutTransition, stateEnterId,
						stateEnterIdNonLoop);
			} catch (Throwable e) {
				Checker.logThreadUnexpectedlyFinished(logger,
						"Unexpected exception while processing timeout transition: "
								+ timeoutTransition, e);
				throw e;
			}
		}
	}
}
