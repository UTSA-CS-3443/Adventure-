package items.potions;

/**
@author JASON, TYLER. BRYLAND
*/

import java.util.HashMap;

import objects.Items;

public abstract class Potion implements Items{
	
	private String name;
	private static HashMap<String, Integer> stats;
	private int worth = 0;
	private int statHPInc = 0;
	
	public Potion() {
		this.name = "";
	}
	
	public HashMap<String, Integer> getStats() {
		return stats;
	}
	
	public static void setStats(String s, Integer i){
		if(stats.containsKey(s))
		{
			stats.replace(s, i);
		}
		else
		{
			stats.put(s, i);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int getWorth() {
		return this.getWorth();
	}
	
	@Override
	public void setWorth(int val) {
		this.worth = val;
	}
	
	public void setStatHPInc(int val) 
	{
		this.statHPInc = val;
	}
	
	public int getStatHPInc() 
	{
		return this.statHPInc;
	}
	
	public int getStatLUCInc() 
	{
		return this.stats.get("LUC");
	}
	
	public int getStatPERInc() 
	{
		return this.stats.get("PER");
	}
	
	public int getStatAGIInc() 
	{
		return this.stats.get("AGI");
	}
	
	public int getStatINTInc() 
	{
		return this.stats.get("INT");
	}
	
	public int getStatSTRInc() 
	{
		return this.stats.get("STR");
	}
}
