package com.sky.lr.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import com.sky.lr.agent.fsm.ConfigurationFSM;
import com.sky.lr.agent.fsm.EqualsPreProcessor;
import com.sky.lr.agent.fsm.FSMException;
import com.sky.lr.agent.fsm.HostAgentFSM;
import com.sky.lr.agent.fsm.SCOREvent;
import com.sky.lr.agent.fsm.State;
import com.sky.lr.agent.fsm.StateAdapter;
import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.PreProcessor;
import com.sky.lr.agent.fsm.interf.StateListener;
import com.sky.lr.agent.fsm.interf.StateMachine;
import com.sky.lr.agent.fsm.interf.TransitionListener;
import com.sky.lr.agent.fsm.interf.TypeEvent;
import com.sky.lr.utility.logger.BasicLogger;
import com.sky.lr.utility.logger.FSMLoggerFactory;

public class TestHostAgentFSM {
	public TestHostAgentFSM(String name) throws FSMException {
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
