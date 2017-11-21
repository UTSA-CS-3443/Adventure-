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

	private HashMap<String, Integer> stats;
	
	public Enemy()
	{
		name = "";
		hp = 5;
	}
	
	public void setImage(Image img)
	{
		this.image = img;
	}
	
	public Image getImage()
	{
		return this.image;
	}
	
	public abstract String getName();
	
	public abstract void setName(String name);

	public abstract int getHp();
	
	public abstract void setHp(int hp);
	
	public abstract HashMap<String, Integer> getStats();
		
}
