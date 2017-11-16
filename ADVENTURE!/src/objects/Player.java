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
	private String name;
	private int hp;
	private int walletAmt;
	
	public Player()
	{
		name = "";
		hp = 10;
		stats = new HashMap<String, Integer>();
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
		stats.put(s, i);
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
