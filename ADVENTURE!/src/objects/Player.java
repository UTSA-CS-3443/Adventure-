package objects;

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
	private String name;
	private int hp;
	private int walletAmt;
	
	public Player()
	{
		name = "";
		hp = 10;
		stats = new HashMap<String, Integer>();
		statMods = new HashMap<>();
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
