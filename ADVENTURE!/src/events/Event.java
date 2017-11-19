package events;

import java.util.HashMap;
import java.util.Random;

import application.Main;
import locations.Attribute;
import objects.Player;

public class Event	{

	private String eventName;
	private String eventDesc;
	private boolean completed;
	public HashMap<String, String> choices = new HashMap<>();
	
	public Event() {
		eventName = "";
		eventDesc = "";
		completed = false;
	}
	
	public String getName()
	{
		return this.eventName;
	}
	
	public void setName(String name)
	{
		this.eventName = name;
	}
	
	public String getDesc()
	{
		return this.eventDesc;
	}
	
	public void setDesc(String desc)
	{
		this.eventDesc = desc;
	}
	
	public void setChoices(String choice, String effect)
	{
		choices.put(choice, effect);
	}
	
	public boolean getStatus()
	{
		return completed;
	}
	
	public void setStatus(boolean status)
	{
		this.completed = status;
	}
	
}
