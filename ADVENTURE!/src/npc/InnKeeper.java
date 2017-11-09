package npc;

import java.util.HashMap;

import objects.NPC;

public class InnKeeper extends NPC {
	
	String name;
	private int hp;
	private int[] stats;
	
	public InnKeeper(String name) {
		super();
		this.name = name;
	}
}
