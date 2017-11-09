package events;

import java.util.HashMap;

import locations.Attribute;

public class Event	{

	private String eventName;
	private String eventDesc;
	private boolean completed;
	HashMap<String, String> choices = new HashMap<>();
	HashMap<String, Integer> stats = new HashMap<>();
	
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
	
	public void outcomes(String text)
	{
		String outcome = choices.get(text);
		String[] outcomeParser = outcome.split("~");
		String[] operation = null;
		
		for(int i=0;i<outcomeParser.length;i++)
		{
			operation = outcomeParser[i].split(" ");
		}
		
		for(int j=0;j<operation.length;j++)
		{
			if(operation[0].equals("STR|PER|INT|AGI|LUC"))
			{
				
			}
		}
		
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
