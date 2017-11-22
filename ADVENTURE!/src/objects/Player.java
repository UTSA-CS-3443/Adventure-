package objects;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * The player character
 * @author JASON
 * @author Tyler
 *
 */

public class Player implements Entity{
	
	public HashMap<String, Integer> stats;
	public HashMap<String, Integer> statMods;
	ArrayList<String> InventoryItems = new ArrayList<String>();
	HashMap<String, Integer> Inventory = new HashMap<String, Integer>(); 
	private String name;
	private int hp;
	private int maxHP;
	private int walletAmt;
	
	public Player()
	{
		name = "";
		hp = 10;
		maxHP = 10;
		walletAmt = 5;
		stats = new HashMap<String, Integer>();
		statMods = new HashMap<>();
	}

	public void addItemToInventory(String name, int cost)
	{
		Inventory.put(name, cost);
	}
	
	public int getInventoryLength()
	{
		return this.Inventory.size();
	}

	public String getItemFromInventory(int i)
	{
		return this.InventoryItems.get(i);
	}
	
	public void addInventoryItems(String name)
	{
		InventoryItems.add(name); 
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getMaxHP()
	{
		return this.maxHP;
	}
	
	public void setMaxHP(int max)
	{
		this.maxHP = max;
	}

	public HashMap<String, Integer> getStats() {
		return stats;
	}

	public void setStats(String s, int i) {
		if(stats.containsKey(s))
		{
			stats.replace(s, i);
			statMods.replace(s, ((i - 10)/2));
		}
		else
		{
			stats.put(s, i);
			statMods.put(s, ((i - 10)/2));
		}
	}
	
	public HashMap<String, Integer> getStatMods()
	{
		return statMods;
	}
	
	public Player getPlayer()
	{
		return this;
	}
	
	public void setWalletAmt(int i)
	{
		walletAmt = i;
	}
	
	public int getWalletAmt()
	{
		return walletAmt;
	}
	
}
