package enemies;

import java.util.HashMap;

import objects.Enemy;

public class WildBeast extends Enemy {
	
	private String name;
	private int hp;
	private HashMap<String, Integer> stats;
	
	public WildBeast() {
		super();
		super.setMoney(3);
		super.setHp(10);
		super.setStats("STR", 12);
		super.setStats("PER", 10);
		super.setStats("INT", 4);
		super.setStats("AGI", 10);
		super.setStats("LUC", 3);
	}
	
}
