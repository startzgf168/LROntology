package com.sky.lr.agent.fsm;

import com.sky.lr.agent.fsm.interf.TypeEvent;

public class SCOREvent extends TypeEvent {
	//Event name
	public String SCOREventName = null;
	
	//Event type
	public TypeEvent.Type SCOREventType = TypeEvent.Type.DEFAULT;
	
	//The event content wrapped by the event 
	public EventContent eventContent = null;
	
	public SCOREvent(String eventName, TypeEvent.Type type){
		this.SCOREventName = eventName;
		this.SCOREventType = type;
	}
	
	public SCOREvent(String eventName, TypeEvent.Type type, EventContent eventContent){
		this.SCOREventName = eventName;
		this.SCOREventType = type;
		this.eventContent = eventContent;
	}
	
	public static SCOREvent instance(String scorName, TypeEvent.Type type){
		return new SCOREvent(scorName, type);
	}
	
	public void setSCOREventType(TypeEvent.Type type){
		this.SCOREventType = type;
	}
	
	public String getSCOREventName(){
		return this.SCOREventName;
	}
	
	public TypeEvent.Type getSCOREventType(){
		return this.SCOREventType;
	}
	
	public void setEventContent(EventContent eContent){
		this.eventContent = eContent;
	}
	
	public EventContent getEventContent(){
		return this.eventContent;
	}

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return this.SCOREventName;
	}

	@Override
	public Type getEventType() {
		// TODO Auto-generated method stub
		return this.SCOREventType;
	}	
	
}
