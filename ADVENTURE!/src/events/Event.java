package events;

import locations.Attribute;

public abstract class Event implements Attribute {

	private String eventName;
	private String eventDesc;
	private boolean completed = false;
	
	public Event(String eName) {
		this.eventName = eName;
	}
	
	public void setDesc(String desc)
	{
		this.eventDesc = desc;
	}
	
	public abstract boolean eventSucceed();
	
	public abstract boolean eventFail();
	
	public boolean getStatus()
	{
		return completed;
	}
	
	public void setStatus(boolean status)
	{
		this.completed = status;
	}
	
}
