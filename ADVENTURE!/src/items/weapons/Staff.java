package items.weapons;

import java.util.HashMap;

public class Staff extends Weapon {
	
	private String name;
	private HashMap<String, Integer> stats;
	
	public Staff(String name) {
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
