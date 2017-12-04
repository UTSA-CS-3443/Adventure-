package controller;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import model.Game;

/**
 * Loads the Main Menu screen and handles the buttons. This is the Main Menu.
 * @author JASON
 *
 */
public class MainMenuController extends AnchorPane{
	
	/**
	 * Loads the Main Menu screen
	 */
	public MainMenuController()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Begins a new game and takes the player to character creation
	 * @param e ActionEvent New Game button was clicked
	 */
	@FXML
	protected void handleNewGame(ActionEvent e)
	{	
		Main.game = new Game();
	}
	
	@FXML
	protected void handleQuit(ActionEvent e)
	{	
		Platform.exit();
	}

}
