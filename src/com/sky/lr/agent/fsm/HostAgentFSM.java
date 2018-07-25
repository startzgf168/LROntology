package com.sky.lr.agent.fsm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.PreProcessor;
import com.sky.lr.agent.fsm.interf.StateListener;
import com.sky.lr.agent.fsm.interf.StateMachine;
import com.sky.lr.agent.fsm.interf.TransitionListener;
import com.sky.lr.agent.fsm.interf.TypeEvent;
import com.sky.lr.utility.logger.BasicLogger;
import com.sky.lr.utility.logger.FSMLoggerFactory;

public class HostAgentFSM extends SCORFSM {

	public HostAgentFSM(String name) throws FSMException {
		super(name);
		// TODO Auto-generated constructor stub
		EqualsPreProcessor equalsPreProcessor = new EqualsPreProcessor(name);
		
		// We need to predefine some rules to handle the invalid input events
		equalsPreProcessor.addProcessor(SCOREvent.instance("Invalid", TypeEvent.Type.INVALID),
				new PreProcessor.Processor<SCOREvent>() {
					@Override
					public Event process(SCOREvent event) {
						// Ignore the invalid input event 
						return null;
					}
				});
		
		addPreProcessor(equalsPreProcessor);
		
		//
		addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				// The last state of the string is a final state
				if (current.isFinalState()) {
					logger.info("The session of Supply Chain is over!");
				}
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
				
				//we need to check if the statFailed state can be safety transferred into stateIdle
				if(current.equals(stateFailed)){
					/*if(condition)
					 * 
					*/
					logger.info("stateFailed->stateIdle");
				}
			}
		});
		
		addListener(new TransitionListener() {
			@Override
			public void onTransition(State source, Event event,
					State destination) {
				// State machine tries to process OtherEvent event if no
				// transition matches
				if (!(event instanceof SCOREvent)) {
					logger.info("Invalid SCOR Event, do nothing...");
				}
			}
		});
		
		stateInit.addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				logger.info("System is intialising, please wait a moment.");
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
			}
		});
		
		stateIdle.addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				logger.info("System is ready to work, please input your request.");
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
			}
		});
		
		stateChecking.addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				logger.info("Agent received Source Event, it is checking the condition to determine the next state.");
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
			}
		});
		
		stateDelivering.addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				logger.info("Agent is delivering resource!.");
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
			}
		});
		
		stateMaking.addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				logger.info("Agent is making resource!.");
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
			}
		});
		
		stateSourcing.addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				logger.info("Agent is sourcing resource!.");
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
			}
		});
		
		stateReturning.addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				logger.info("Agent is returning resource!.");
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
			}
		});
		
		stateFailed.addListener(new StateAdapter(StateListener.Type.LOOP_NO_PROCESS) {
			@Override
			public void onStateEnter(State previous, Event event, State current) {
				logger.info("There are some errors during the Supply chain session!.");
			}

			@Override
			public void onStateExit(State current, Event event, State next) {
				// TODO Auto-generated method stub
			}
		});
		
		
		stateInit_StartEvent_stateIdle
		.addListener(new TransitionListener() {
			@Override
			public void onTransition(State source, Event event,
					State destination) {
				// The following code is generated, don't edit it
				// directly

			}
		});
		
		stateIdle_SourceEvent_stateChecking	
		.addListener(new TransitionListener() {
			@Override
			public void onTransition(State source, Event event,
					State destination) {
				// The following code is generated, don't edit it
				// directly

			}
		});
		
		//TBD
	}
	
	static {
		// Register factory of loggers before any logger is created
		ConfigurationFSM.setLoggerFactory(new FSMLoggerFactory());
	}

	/** The logger object for this class. */
	private final static BasicLogger logger = ConfigurationFSM.getLogger(HostAgentFSM.class);
	
	public static void main(String args[]){
		// Create instance of the state machine
		try (StateMachine machine = new HostAgentFSM(
				HostAgentFSM.class.getSimpleName())) {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in, "UTF-8"));
			// Building done in the constructor, prepare for events processing
			machine.start();

			while (true) {				
				logger.info("Type a SCOR Event: [Source|Make|Deliver|Return] to test the FSM over SCOR.");

				// Ignore empty commands (enter presses)
				String event = null;

				while (event == null || event.isEmpty())
					event = in.readLine();

				// Exit on 'exit' command
				if ("exit".equals(event)) {
					logger.info("Exiting...");
					break;
				}
				//Create the SCOR event
				SCOREvent scorEvent = null;
				
				try {
					scorEvent = SCOREvent.instance(event.toUpperCase(Locale.ENGLISH), TypeEvent.Type.valueOf(event.toUpperCase(Locale.ENGLISH)));
				} catch (IllegalArgumentException e) {
					logger.error("Unknown event: " + event);
					continue;
				}

				machine.process(scorEvent);

				// Exit after a final state is entered
				if (machine.isInFinalState()) {
					logger.debug("there are some errors in the supply chain session, exiting");
					break;
				}
			}
		} catch (FSMException | IOException e) {
			// Process any exception that may occur
			logger.fatal("Unexpected exception occurred", e);
		}

		logger.debug("End of main()");
	}

}
