package enemies;

import java.util.HashMap;

import objects.Enemy;

public class Goblin extends Enemy {
	
	private String name;
	private int hp;
	private HashMap<String, Integer> stats;
	
	public Goblin() {
		super();
		super.setMoney(4);
		super.setHp(8);
		super.setStats("STR", 5);
		super.setStats("PER", 7);
		super.setStats("INT", 6);
		super.setStats("AGI", 10);
		super.setStats("LUC", 10);
	}

}
