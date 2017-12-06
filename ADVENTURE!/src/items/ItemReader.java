package items;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import application.Main;
import items.potions.HealthPotion;
import items.potions.Potion;
import items.weapons.Dagger;
import items.weapons.Mace;
import items.weapons.Staff;
import items.weapons.Sword;
import items.weapons.Weapon;
import model.Game;
import objects.Items;
import objects.NPC;
import npc.*;

/**
@author TYLER, BRYLAND
*/

public class ItemReader	{

	private Scanner in;
	private String itemName;
	private String[] identifier;
	private Items item;
	private String[] statName = {"STR", "PER", "INT", "AGI", "LUC"};
	
	public ItemReader(URL items) {
		
		in = null;
		itemName = "";
		identifier = new String[3];
		item = null;
		
		try {
			in = new Scanner(new File(items.toURI()));
		} catch(FileNotFoundException exception){
			System.err.println("failed to open Item data");
		    System.exit(1);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		while (in.hasNext())
		{
			String line = in.nextLine();
			identifier = line.split(">");
			
			if(identifier[0].equals("WEAPON")) {
				if (identifier[1].equals("Sword")) {
					itemName = identifier[2];
					Sword item = new Sword(itemName);
					for (int i = 3; i <= 7; i++) {
						item.setStats(statName[i-3], Integer.valueOf(identifier[i]));
					}
					
				} else if (identifier[1].equals("Staff")) {
					itemName = identifier[2];
					Staff item = new Staff(itemName);
					for (int i = 3; i <= 7; i++) {
						item.setStats(statName[i-3], Integer.valueOf(identifier[i]));
					}
					
				} else if (identifier[1].equals("Mace")) {
					itemName = identifier[2];
					Mace item = new Mace(itemName);
					for (int i = 3; i <= 7; i++) {
						item.setStats(statName[i-3], Integer.valueOf(identifier[i]));
					}
					
				} else if (identifier[1].equals("Dagger")) {
					itemName = identifier[2];
					Dagger item = new Dagger(itemName);
					for (int i = 3; i <= 7; i++) {
						item.setStats(statName[i-3], Integer.valueOf(identifier[i]));
					}
					
				}
			} 
			else if(identifier[0].equals("HEALTHPOTION")) 
			{
				itemName = identifier[1];
				HealthPotion item = new HealthPotion();
				for (int i = 2; i <= 6; i++) 
				{
					item.setStats(statName[i-2], Integer.valueOf(identifier[i]));
				}
				
			} 
			else if(identifier[0].equals("ENDITEM")) 
			{
				Game.ItemM.put(identifier[0], item);
			}
		}
	}
}
