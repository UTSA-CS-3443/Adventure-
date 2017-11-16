package items;

import java.util.HashMap;

import objects.Items;

public interface Weapon extends Items{
	
	public HashMap<String, Integer> getStats();

	public void setStats(String s, Integer i);
	
}
