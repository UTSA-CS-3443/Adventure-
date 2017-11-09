package objects;

import java.util.HashMap;

public abstract class NPC implements Entity {
	
	protected int stockAmt = 0;
	protected int isMerchant = 0;
	protected String name;
	protected int hp;
	String[] Inventory;
	
	public void interact(Player player)
	{
		
	}

	public int getMerchantStatus()
	{
		return isMerchant;
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
		return this.Inventory.length;
	}

	public String getItemFromInventory(int i)
	{
		return this.Inventory[i];
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStats(String s, int i) {
		// TODO Auto-generated method stub
		
	}

}
