package objects;

import java.util.HashMap;

/**
 * Framework for all game world entities
 * @author JASON
 *
 */
public interface Entity {

	public String getName();
	
	public int getHp();

	public void setName(String name);

	public HashMap<String, Integer> getStats();

	public void setStats(String s, int i);

	void setHp(int hp);
}
