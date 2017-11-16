package objects;

import java.util.HashMap;

/**
 * Framework for all Enemies
 * @author JASON
 *
 */
public abstract class Enemy implements Entity{

	private String name;
	private int hp;
	private HashMap<String, Integer> stats;
	
	public Enemy()
	{
		name = "";
		hp = 5;
		stats = new int[5];
	}
	
	public abstract String getName();
	
	public abstract void setName(String name);

	public abstract int getHp();
	
	public abstract void setHp(int hp);
	
	public abstract HashMap<String, Integer> getStats();
		
}
