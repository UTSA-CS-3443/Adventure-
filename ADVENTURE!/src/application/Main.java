package application;
	
import java.util.HashMap;

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
 * @author JASON, TYLER
 *
 */
public class Main extends Application {
	
	public static GameScreen gs;
	public static Scene mainGame;
	public static Stage stage;
	public static Game game;
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		stage.setTitle("ADVENTURE!");
		
		MainMenuController menu = new MainMenuController();
		stage.setScene(new Scene(menu, 900, 600));
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
