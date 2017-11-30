package items.potions;

import java.util.HashMap;

import objects.Items;

public abstract class Potion implements Items{
	
	private String name;
	private static HashMap<String, Integer> stats;
	private int worth = 0;
	private int statHPInc = 0;
	private int statLUCInc = 0;
	private int statPERInc = 0;
	private int statSTRInc = 0;
	private int statAGIInc = 0;
	private int statINTInc = 0;

	public Potion() {
		this.name = "";
	}
	
	public HashMap<String, Integer> getStats() {
		return stats;
	}
	
	public static void setStats(String s, Integer i){
		stats.put(s, i);
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
	
	public void setStatLUCInc(int val)
	{
		this.statLUCInc = val;
	}
	
	public int getStatLUCInc() 
	{
		return this.statLUCInc;
	}
	
	public void setStatPERInc(int val)
	{
		this.statPERInc = val;
	}
	
	public int getStatPERInc() 
	{
		return this.statPERInc;
	}
	
	public void setStatAGIInc(int val)
	{
		this.statAGIInc = val;
	}
	
	public int getStatAGIInc() 
	{
		return this.statAGIInc;
	}
	
	public void setStatINTInc(int val)
	{
		this.statINTInc = val;
	}
	
	public int getStatINTInc() 
	{
		return this.statINTInc;
	}
	
	public void setStatSTRInc(int val)
	{
		this.statSTRInc = val;
	}
	
	public int getStatSTRInc() 
	{
		return this.statSTRInc;
	}
}
