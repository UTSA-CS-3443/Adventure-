package items.weapons;

import java.util.HashMap;

public class Sword extends Weapon {
	
	private String name;
	private HashMap<String, Integer> stats;
	
	public Sword(String name) {
		super();
		this.name = name;
	}

	@Override
	public int getWorth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWorth(int val) {
		// TODO Auto-generated method stub
		
	}

}
