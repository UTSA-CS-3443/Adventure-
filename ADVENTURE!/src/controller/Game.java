package controller;

import java.util.HashMap;

import application.Main;
import javafx.scene.Scene;
import locations.Attribute;
import java.util.HashMap;

public class Game {

	public static HashMap<String, Attribute> attributeMap = new HashMap<String, Attribute>();
	
	public Game() {
		//initialize the game
		init();
		
		gameLoop();
	}
	
	public static void init()
	{
		//create character
		CharacterCreator cc = new CharacterCreator();
		Main.stage.setScene(new Scene(cc));
		Main.stage.show();
	}
	
	public static void gameLoop()
	{
		//primary game loop
	}

}
