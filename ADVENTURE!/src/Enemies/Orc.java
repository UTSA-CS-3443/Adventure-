package Enemies;

import objects.Enemy;

public class Orc extends Enemy {
	
	private String name;
	
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHp(int hp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStats(int[] stats) {
		// TODO Auto-generated method stub
		
	}

}
