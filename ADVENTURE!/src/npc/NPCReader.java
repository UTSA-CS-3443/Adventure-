package npc;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import application.Main;
import javafx.scene.image.Image;
import model.Game;
import objects.NPC;
import npc.*;

public class NPCReader	{

	private Scanner in;
	private String npcName;
	private String npcDesc;
	private String[] identifier;
	private String directory;
	Image img;
	private NPC npc;
	
	
	public NPCReader(URL npcs) {
		
		in = null;
		npcName = "";
		npcDesc = "";
		identifier = new String[3];
		npc = null;
		
		try {
			in = new Scanner(new File(npcs.toURI()));
		} catch(FileNotFoundException exception){
			System.err.println("failed to open NPC data");
		    System.exit(1);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		while (in.hasNext())
		{
			String line = in.nextLine();
			identifier = line.split(">");
			//determine if we are making a new npc, if so instantiate with name
			//NPC>NAME
			if(identifier[0].equals("NPC"))
			{
				if(identifier[2].equals("ShopKeeper"))
					npc = new ShopKeeper(identifier[1]);
				else if(identifier[2].equals("InnKeeper"))
					npc = new InnKeeper(identifier[1]);
			}
			//figure out if we have inventory data
			//INV>referenceName>cost
			else if(identifier[0].equals("INV"))
			{
				//read into inventory
				npc.addItemToInventory(identifier[1], Integer.valueOf(identifier[2]));
				npc.addInventoryItems(identifier[1]);
				npc.setStockAmt(npc.getStockAmt() + 1);
			}
			else if(identifier[0].equals("IMG"))
			{
				//read into inventory
				directory = String.format("/images/%s", identifier[1]);
				img = new Image(directory);
				npc.setImage(img);
			}
			//set whether or not the npc is a merchant
			else if(identifier[0].equals("MERCHANT"))
			{
				npc.setMerchantStatus(Integer.valueOf(identifier[1]));
			}
			//add the description of the character if provided
			else if(identifier[0].equals("DESC"))
			{
				npc.setDescription(identifier[1]);
			}
			//add the speech to the character if provided 
			else if(identifier[0].equals("SPEECH"))
			{
				npc.setSpeech(identifier[1]);
			}
			//finally, add the npc to the map of npcs
			else if(identifier[0].equals("ENDNPC"))
			{
				Game.NPCM.put(npc.getName(), npc);
			}
		}
	}
}
