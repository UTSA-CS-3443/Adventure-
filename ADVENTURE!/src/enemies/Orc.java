package enemies;

import java.util.HashMap;

import objects.Enemy;

public class Orc extends Enemy {
	
	private String name;
	private int hp;
	private HashMap<String, Integer> stats;
	
	public Orc() {
		super();
		super.setMoney(30);
		super.setHp(25);
		super.setStats("STR", 15);
		super.setStats("PER", 5);
		super.setStats("INT", 4);
		super.setStats("AGI", 8);
		super.setStats("LUC", 5);
	}

}
