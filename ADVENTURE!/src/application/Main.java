package application;
	
import java.util.HashMap;

import controller.DeathScreen;
import controller.Game;
import controller.GameScreen;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import locations.ReadLocations;
import objects.NPC;
import javafx.scene.Scene;


/**
 * Launches the game and begins at the Main Menu
 * @author JASON, TYLER, CHANDLER, BRYLAND
 *
 */
public class Main extends Application {
	
	public static GameScreen gs;
	public static Scene mainGame;
	public static Scene mainMenuScreen;
	public static Scene death;
	public static Stage stage;
	public static Game game;
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		stage.setTitle("ADVENTURE!");
		
		DeathScreen ds = new DeathScreen();
		death = new Scene(ds, 900, 600);
		MainMenuController menu = new MainMenuController();
		mainMenuScreen = new Scene(menu, 900, 600);
		stage.setScene(mainMenuScreen);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
