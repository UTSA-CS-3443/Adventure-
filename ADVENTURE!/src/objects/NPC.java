package objects;

import java.util.HashMap;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public abstract class NPC implements Entity {
	
	protected int stockAmt = 0;
	protected int isMerchant = 0;
	protected String name;
	protected int hp;
	protected Image image;
	protected String description;
	protected String speech;
	ArrayList<String> InventoryItems = new ArrayList<String>();
	HashMap<String, Integer> Inventory = new HashMap<String, Integer>(); 
	private HashMap<String, Integer> stats;

	
	public void interact(Player player)
	{
		
	}
	
	public void setImage(Image img)
	{
		this.image = img;
	}
	
	public Image getImage()
	{
		return this.image;
	}
	
	public int getCost(String item)
	{
		return Inventory.get(item);
	}
	
	public void addItemToInventory(String name, int cost)
	{
		Inventory.put(name, cost);
	}
	
	public int getMerchantStatus()
	{
		return isMerchant;
	}
	
	public void setMerchantStatus(int i)
	{
		isMerchant = i;
	}
	
	public void setStockAmt(int i)
	{
		this.stockAmt = i;
	}
	
	public int getStockAmt()
	{
		return this.stockAmt;
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
	
	public void setSpeech(String speech)
	{
		this.speech = speech;
	}
	
	public String getSpeech()
	{
		return this.speech;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getHp() {
		return this.hp;
	}

	@Override
	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public HashMap<String, Integer> getStats() {
		return this.stats;
	}

	@Override
	public void setStats(String s, int i) {
		stats.put(s, i);
		
	}

}
