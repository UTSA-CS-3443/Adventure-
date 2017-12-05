package model;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;

import application.Main;
import controller.CharacterCreator;
import javafx.scene.Scene;
import locations.Attribute;
import npc.NPCReader;

import java.util.HashMap;
import locations.*;
import events.*;
import objects.*;
import view.*;
import worldattributes.*;

public class Game {

	public static HashMap<String, Attribute> attributeMap = new HashMap<String, Attribute>();
	public static HashMap<String, NPC> NPCM = new HashMap<String, NPC>();
	public static HashMap<String, Items> ItemM = new HashMap<String, Items>();
	public static HashMap<String, Enemy> enemiesM = new HashMap<>();
	public Game() {
		//initialize the game
		init();
		//gameLoop();
	}
	
	public static void init()
	{
		//create character
		CharacterCreator cc = new CharacterCreator();
		Main.stage.setScene(new Scene(cc));
		Main.stage.show();
		
		//load locations
		
		initEvents();
		initNPC();
		initLoc();
		
	}
	
	public static void initLoc()
	{
		URL defaultFile = Game.class.getResource("/locations/Locations.txt");
		LoadLocations loadLoc = new LoadLocations(defaultFile);
	}
	
	public static void initNPC()
	{
		URL NPCFile = Game.class.getResource("/npc/NPCs.txt");
		NPCReader npcReader = new NPCReader(NPCFile);
	}
	
	public static void initEvents()
	{
		URL eventFile = Game.class.getResource("/events/Events.txt");
		EventReader eventReader = new EventReader(eventFile);
	}

}
