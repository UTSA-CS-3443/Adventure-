package items;

import java.util.HashMap;

public class Staff implements Weapon {
	
	private String name;
	private HashMap<String, Integer> stats;
	
	public Staff(String name) {
		super();
		this.name = name;
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
	public HashMap<String, Integer> getStats() {
		return stats;
	}

	@Override
	public void setStats(String s, Integer i) {
		stats.put(s, i);
	}

}