package items.potions;

import java.util.HashMap;

import objects.Items;

public class Potion implements Items{
	
	private String name;
	private static HashMap<String, Integer> stats;
	
	public Potion(String name) {
		this.name = name;
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
	
}
