package items.weapons;

import java.util.HashMap;

import objects.Items;

public abstract class Weapon implements Items{
	
	private String name;
	private static HashMap<String, Integer> stats;
	
	public int getAttackPower()
	{
		//TODO
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	public HashMap<String, Integer> getStats() {
		return stats;
	}
	
	public static void setStats(String s, Integer i) {
		stats.put(s, i);
	}
	
}
