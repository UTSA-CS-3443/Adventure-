package enemies;

import objects.Enemy;

public class WildBeast extends Enemy {
	
	private String name;
	private int hp;
	private int[] stats;
	
	public WildBeast(String name) {
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
	public int[] getStats() {
		return this.stats;
	}

	@Override
	public void setStats(int[] stats) {
		this.stats = stats;
	}

}