package items;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import application.Main;
import controller.Game;
import items.potions.Elixer;
import items.potions.StrengthBooster;
import items.weapons.Dagger;
import items.weapons.Mace;
import items.weapons.Staff;
import items.weapons.Sword;
import objects.Items;
import objects.NPC;
import npc.*;

public class ItemReader	{

	private Scanner in;
	private String itemName;
	private String itemDesc;
	private String[] identifier;
	private Items item;
	
	
	public ItemReader(URL items) {
		
		in = null;
		itemName = "";
		itemDesc = "";
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
					item = new Sword(identifier[2]);
					
				} else if (identifier[1].equals("Staff")) {
					item = new Staff(identifier[2]);
					
				} else if (identifier[1].equals("Mace")) {
					item = new Mace(identifier[2]);
					
				} else if (identifier[1].equals("Dagger")) {
					item = new Dagger(identifier[2]);
				}
			}
			
			else if(identifier[0].equals("POTION")) {
				if (identifier[1].equals("Elixer")) {
					item = new Elixer("INSERT NAME HERE");   // Needs name
				} else if (identifier[1].equals("StrengthBooster")) {
					item = new StrengthBooster("INSERT NAME HERE"); // Needs name
				}
			}
			
			else if(identifier[0].equals("ENDITEM"))
			{
				Game.ItemM.put(identifier[0], item);
			}
		}
	}
}
