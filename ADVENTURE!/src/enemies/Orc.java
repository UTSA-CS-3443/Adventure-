package enemies;

import java.util.HashMap;

import objects.Enemy;

public class Orc extends Enemy {
	
	private String name;
	private int hp;
	private HashMap<String, Integer> stats;
	
	public Orc(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getHp() {
		return this.hp;
	}

	@Override
	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public void setStats(String s, int i) {
		stats.put(s, i);
	}

	@Override
	public HashMap<String, Integer> getStats() {
		return this.stats;
	}


}
