package controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import objects.Player;

/**
 * Loads Game Screen scene and handles player actions. This is where the game is played
 * @author JASON
 *
 */
public class GameScreen extends AnchorPane{
	Player player;
	
	@FXML Text NAME;
	@FXML Text HP;
	@FXML Text STR;
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text AGI;
	@FXML Text LUC;
	
	@FXML TextFlow DESC;
	
	/**
	 * Loads the Game Screen and initializes the player character that was created in Character Creator.
	 * @param p Player character from Character Creator
	 */
	public GameScreen(Player p) {
		this.player = p;
		int[] initStats = player.getStats();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/GameScreenV2.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		NAME.setText(this.player.getName());
		HP.setText(Integer.toString(this.player.getHp()));
		STR.setText(Integer.toString(initStats[0]));
		PER.setText(Integer.toString(initStats[1]));
		INT.setText(Integer.toString(initStats[2]));
		AGI.setText(Integer.toString(initStats[3]));
		LUC.setText(Integer.toString(initStats[4]));
		
		ObservableList list = DESC.getChildren();
		Text test = new Text("THE VOID\n\nThere is nothing here for you now. All around you is blackness and were it not"
				+ " for your sense of feeling, you could not tell your body was physically here at all.");
		list.add(test);
	}
	
	/**
	 * Exit to Desktop
	 * @param e ActionEvent Quit button was clicked
	 */
	@FXML
	protected void handleQuit(ActionEvent e)
	{	
		Platform.exit();
	}

}
