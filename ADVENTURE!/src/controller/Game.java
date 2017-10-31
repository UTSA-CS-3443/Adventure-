package controller;

import java.util.HashMap;

import application.Main;
import javafx.scene.Scene;
import locations.Attribute;
import java.util.HashMap;
import locations.*;
import events.*;
import objects.*;
import screens.*;
import worldattributes.*;

public class Game {

	public static HashMap<String, Attribute> attributeMap = new HashMap<String, Attribute>();
	
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
		initLoc();
	}
	
	public static void initLoc()
	{
		String defaultFile = "locations/locations.txt"
		LoadLocations loadLoc = new LoadLocations(defaultFile);
	}
	
	public static void gameLoop()
	{
		//primary game loop
	}

}
