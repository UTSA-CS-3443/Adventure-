package npc;

import java.util.HashMap;

import objects.NPC;

public class ShopKeeper extends NPC {
	
	private String name;
	private int hp;
	private HashMap<String, Integer> stats;
	
	public ShopKeeper(String name) {
		super();
		this.name = name;
	}
}
