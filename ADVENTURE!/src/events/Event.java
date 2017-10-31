package events;

import locations.Attribute;

public abstract class Event implements Attribute {

	private boolean completed = false;
	
	public Event() {

	}
	
	public boolean getStatus()
	{
		return completed;
	}
	
}
