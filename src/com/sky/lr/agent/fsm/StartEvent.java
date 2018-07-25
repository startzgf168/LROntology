package com.sky.lr.agent.fsm;

import com.sky.lr.agent.fsm.interf.Event;
import com.sky.lr.agent.fsm.interf.TypeEvent.Type;

/*
 * It's always transfer from Init to Idle in the beginning
 * */
public class StartEvent implements Event{

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getEventType() {
		// TODO Auto-generated method stub
		return null;
	}

}
