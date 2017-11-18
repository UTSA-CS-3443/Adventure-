package items.potions;

import java.util.HashMap;

import objects.Items;

public abstract class Potion implements Items{
	
	private String name;
	private HashMap<String, Integer> stats;
	
	public HashMap<String, Integer> getStats() {
		return stats;
	}
	
	public void setStats(String s, Integer i){
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
