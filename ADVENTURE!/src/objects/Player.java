package objects;

/**
 * The player character
 * @author JASON
 *
 */
public class Player implements Entity{

	private String name;
	private int hp;
	private int[] stats;
	
	public Player()
	{
		name = "";
		hp = 10;
		stats = new int[5];
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

	public int[] getStats() {
		return stats;
	}

	public void setStats(int[] stats) {
		this.stats = stats;
	}
	
}
