package com.sky.lr.agent.fsm;

import com.sky.lr.agent.fsm.interf.TypeEvent;


public class SCORFSM extends NonDeterministicStateMachine{
	/**
	 * "Init" state while Agent is initialising its current state.
	 */
	public final State stateInit;

	/**
	 * "Idle" state, the Agent is ready to receive the SCOR event.
	 */
	public final State stateIdle;

	/**
	 * "Checking" state, the Agent FSM is running after receiving the source event.
	 */
	public final State stateChecking;

	/**
	 * "Delivering" state, which means the Agent can satisfy the users' requirement directly 
	 * since it has been stored in the stocked inventory.
	 */
	public final State stateDelivering;

	/**
	 * "Making" state, which means the Agent need do something to satisfy user's requirement.
	 */
	public final State stateMaking;

	/**
	 * "Sourcing" state, the local Agent cann't satisfy user's requirement and has to ask for help from other suppliers.
	 */
	public final State stateSourcing;

	/**
	 * "Returning" state, which means the Agent is receiving or returning the resource for some reasons.
	 */
	public final State stateReturning;

	/**
	 * "Failed" state, which means there are some errors happened during the process of sourcing/making/delivering/returning.
	 */
	public final State stateFailed;

	/**
	 * Transition always from {Init} to {Idle} on whatever event.
	 */
	public final Transition stateInit_StartEvent_stateIdle;

	/**
	 * Transition  from {Idle} to {Checking} on {Source} event.
	 */
	public final Transition stateIdle_SourceEvent_stateChecking;

	/**
	 * Transition  from {Idle} to {Returning} on {Return} event.
	 */
	public final Transition stateIdle_ReturnEvent_stateReturning;

	/**
	 * Transition  from {Checking} to {Delivering} on {Deliver} event in the condition1.
	 */
	public final Transition stateChecking_DeliverEvent_Condition1_stateDelivering;
	
	/**
	 * Transition  from {Checking} to {Making} on {Make} event in the condition2.
	 */
	public final Transition stateChecking_MakeEvent_Condition2_stateMaking;
	
	/**
	 * Transition  from {Checking} to {Sourcing} on {Source} event in the condition3.
	 */
	public final Transition stateChecking_SourceEvent_Condition3_stateSourcing;
	
	/**
	 * Transition  from {Sourcing} to {Making} on {Make} event after completing source.
	 */
	public final Transition stateSourcing_MakeEvent_Condition4_stateMaking;
	
	/**
	 * Transition  from {Sourcing} to {Failed} if source failed for some reasons.
	 */
	public final Transition stateSourcing_NullEvent_Condition5_stateFailed;
	
	/**
	 * Transition  from {Making} to {Delivering} on {Deliver} event after completing make.
	 */
	public final Transition stateMaking_DeliverEvent_Condition6_stateDelivering;
	
	/**
	 * Transition  from {Making} to {Failed} if make failed for some reasons.
	 */
	public final Transition stateMaking_NullEvent_Condition7_stateFailed;
	
	/**
	 * Transition  from {Delivering} to {Idle} if deliver completed successful.
	 */
	public final Transition stateDelivering_NullEvent_Condition8_stateIdle;
	
	/**
	 * Transition  from {Delivering} to {Returning} on {Return} event for it happens failed and needs to return in the process of delivering.
	 */
	public final Transition stateDelivering_ReturnEvent_Condition9_stateReturning;
	
	/**
	 * Transition  from {Delivering} to {Failed} for some reasons(timeout, etc) result in failure in the process of delivering.
	 */
	public final Transition stateDelivering_NullEvent_Condition10_stateFailed;
	
	/**
	 * Transition  from {Failed} to {Idle} after completing clear action.
	 */
	public final Transition stateFailed_NullEvent_Condition11_stateIdle;
	
	/**
	 * Transition  from {Returning} to {Idle} after completing update successfully.
	 */
	public final Transition stateReturning_NullEvent_Condition12_stateIdle;
	

	/**
	 * Create the object, build the state machine.
	 * 
	 * @param name
	 *            the name of the state machine
	 * @throws FSMException
	 *             if building of state machine fails
	 */
	public SCORFSM(String name) throws FSMException {
		super(name);

		stateInit = new State("Init");
		addState(stateInit);

		stateIdle = new State("Idle");
		addState(stateIdle);

		stateChecking = new State("Checking");
		addState(stateChecking);

		stateDelivering = new State("Delivering");
		addState(stateDelivering);

		stateMaking = new State("Making");
		addState(stateMaking);

		stateSourcing = new State("Sourcing");
		addState(stateSourcing);

		stateReturning = new State("Returning");
		addState(stateReturning);
		
      //stateFailed = new State("Failed", State.Type.FINAL);
		stateFailed = new State("Failed", State.Type.FINAL);
		addState(stateFailed);

		stateInit_StartEvent_stateIdle = new Transition(stateInit, NullEvent.instance, stateIdle);
		addTransition(stateInit_StartEvent_stateIdle);
		
		//How to construct source event with some wrapped information
		stateIdle_SourceEvent_stateChecking = new Transition(stateIdle, SCOREvent.instance("Source", TypeEvent.Type.SOURCE), stateChecking);
        addTransition(stateIdle_SourceEvent_stateChecking);
        
        stateIdle_ReturnEvent_stateReturning = new Transition(stateIdle,SCOREvent.instance("Return", TypeEvent.Type.RETURN),stateReturning);
        addTransition(stateIdle_ReturnEvent_stateReturning);
        
        stateChecking_DeliverEvent_Condition1_stateDelivering = new Transition(stateChecking,SCOREvent.instance("Deliver", TypeEvent.Type.DELIVER),stateDelivering);
        addTransition(stateChecking_DeliverEvent_Condition1_stateDelivering);
        
        stateChecking_MakeEvent_Condition2_stateMaking = new Transition(stateChecking,SCOREvent.instance("Make", TypeEvent.Type.MAKE),stateMaking);
        addTransition(stateChecking_MakeEvent_Condition2_stateMaking);
        
        stateChecking_SourceEvent_Condition3_stateSourcing = new Transition(stateChecking,SCOREvent.instance("Source", TypeEvent.Type.SOURCE),stateSourcing);
        addTransition(stateChecking_SourceEvent_Condition3_stateSourcing);
        
        stateSourcing_MakeEvent_Condition4_stateMaking = new Transition(stateSourcing,SCOREvent.instance("Make", TypeEvent.Type.MAKE),stateMaking);
        addTransition(stateSourcing_MakeEvent_Condition4_stateMaking);
        
        stateSourcing_NullEvent_Condition5_stateFailed = new Transition(stateSourcing,NullEvent.instance,stateFailed);
        addTransition(stateSourcing_NullEvent_Condition5_stateFailed);
        
        stateMaking_DeliverEvent_Condition6_stateDelivering = new Transition(stateMaking,SCOREvent.instance("Deliver", TypeEvent.Type.DELIVER),stateDelivering);
        addTransition(stateMaking_DeliverEvent_Condition6_stateDelivering);
        
        stateMaking_NullEvent_Condition7_stateFailed = new Transition(stateMaking,NullEvent.instance,stateFailed);
        addTransition(stateMaking_NullEvent_Condition7_stateFailed);
        
        stateDelivering_NullEvent_Condition8_stateIdle = new Transition(stateDelivering,NullEvent.instance,stateIdle);
        addTransition(stateDelivering_NullEvent_Condition8_stateIdle);
        
        stateDelivering_ReturnEvent_Condition9_stateReturning = new Transition(stateDelivering,SCOREvent.instance("Return", TypeEvent.Type.RETURN),stateReturning);
        addTransition(stateDelivering_ReturnEvent_Condition9_stateReturning);
        
        stateDelivering_NullEvent_Condition10_stateFailed = new Transition(stateDelivering,NullEvent.instance,stateFailed);
        addTransition(stateDelivering_NullEvent_Condition10_stateFailed);
        
        stateFailed_NullEvent_Condition11_stateIdle = new Transition(stateFailed,NullEvent.instance,stateIdle);
        addTransition(stateFailed_NullEvent_Condition11_stateIdle);
        
        stateReturning_NullEvent_Condition12_stateIdle = new Transition(stateReturning,NullEvent.instance,stateIdle);
        addTransition(stateReturning_NullEvent_Condition12_stateIdle);


		setStartState(stateInit);
	}
}
