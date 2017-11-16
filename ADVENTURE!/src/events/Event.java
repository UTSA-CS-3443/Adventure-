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
	HashMap<String, String> choices = new HashMap<>();
	HashMap<String, Integer> stats = new HashMap<>();
	HashMap<String, Integer> statMods = new HashMap<>();
	private int hp;
	Player player;
	boolean failedCheck;
	
	public Event() {
		eventName = "";
		eventDesc = "";
		completed = false;
		player = Main.gs.player.getPlayer();
		stats = player.getStats();
		statMods = player.getStatMods();
		hp = player.getHp();
		failedCheck = false;
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
			for(int j=0;j<operation.length;j++)
			{
				if(operation[0].equals("STR|PER|INT|AGI|LUC"))
				{
					if(operation[1].equals("+"))
					{
						player.setStats(operation[0], stats.get(operation[0]) + Integer.valueOf(operation[3]));
					}
					else if(operation[0].equals("-"))
					{
						player.setStats(operation[0], stats.get(operation[0]) - Integer.valueOf(operation[3]));
					}
					else
						break;
				}
				else if(operation[0].equals("HP"))
				{
					if(operation[1].equals("+"))
					{
						player.setHp(hp + Integer.valueOf(operation[3]));
					}
					else if(operation[1].equals("-"))
					{
						player.setHp(hp - Integer.valueOf(operation[3]));
					}
					else
						break;
				}
				else if(operation[0].equals("ROLL"))
				{
					int die = (int) (Math.random()*20+1);
					
					if(die == 20)
					{
						failedCheck = false;
					}
					else if(die == 1)
					{
						failedCheck = true;
						break;
					}
					else if(die + statMods.get(operation[1]) < Integer.valueOf(operation[3]))
					{
						failedCheck = true;
						break;
					}
				}
			}
			
			if(failedCheck)
				break;
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
