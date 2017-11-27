package objects;

import java.util.HashMap;

import javafx.scene.image.Image;

/**
 * Framework for all Enemies
 * @author JASON
 *
 */
public abstract class Enemy implements Entity{

	private String name;
	private int hp;
	private Image image;
	private int money;

	public HashMap<String, Integer> stats;
	public HashMap<String, Integer> statMods;
	
	public Enemy()
	{
		name = "";
		hp = 5;
		stats = new HashMap<>();
		statMods = new HashMap<>();
	}
	
	public void setImage(Image img)
	{
		this.image = img;
	}
	
	public Image getImage()
	{
		return this.image;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public int getHp()
	{
		return hp;
	}
	
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	
	public HashMap<String, Integer> getStats()
	{
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
	
	public int getMoney()
	{
		return this.money;
	}
	
	public void setMoney(int mon)
	{
		this.money = mon;
	}
		
}
