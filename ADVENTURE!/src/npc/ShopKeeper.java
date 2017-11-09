package npc;

import java.util.HashMap;

import objects.NPC;

public class ShopKeeper extends NPC {
	
	private String name;
	private int hp;
	
	public ShopKeeper(String name) {
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
	public HashMap<String, Integer> getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStats(String s, int i) {
		// TODO Auto-generated method stub
		
	}


}
