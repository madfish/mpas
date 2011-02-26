package EventMechanism.Events;

import algorithms.myPoint;
import EventMechanism.ApplicationEvent;
import EventMechanism.ApplicationEventSource;

public class SetFinishCellEvent extends ApplicationEvent{

	private myPoint _p;
	private int _agentNumber;
	
	public SetFinishCellEvent(ApplicationEventSource source, myPoint p,int agentNumber) {
		super(source);
		this._p = p;
		this._agentNumber = agentNumber;
	}
	@Override
	public String getDescription() {
		return "SetFinishCellEvent";
	}
	
	public myPoint getPosition(){
		return this._p;
	}
	public int getAgent(){
		return this._agentNumber;
	}
}
