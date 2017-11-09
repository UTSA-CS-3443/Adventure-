package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import objects.Player;

/**
 * Character Creation screen. Players create their characters here.
 * @author JASON
 *
 */
public class CharacterCreator extends Pane{
	
	Player player;
	
	@FXML Text STR;
	@FXML Text INT;
	@FXML Text PER;
	@FXML Text AGI;
	@FXML Text LUC;
	@FXML Text HP;
	@FXML TextField NAME;
	
	/**
	 * Load the character creator scene and initialize a new player character
	 */
	public CharacterCreator()
	{
		player = new Player();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/CharacterCreator.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		
		try {
			loader.load();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * End character creation and start the game
	 * @param e ActionEvent begin button was clicked
	 */
	@FXML
	protected void handleBegin(ActionEvent e)
	{
		player.setName(NAME.getText());
		Main.gs = new GameScreen(player);
		Main.stage.setScene(new Scene(Main.gs));
		Main.stage.show();
	}
	
	/**
	 * Rolls the statistics (stats) for a player's character
	 * @param e ActionEvent roll button is clicked
	 */
	@FXML
	protected void handleRollStats(ActionEvent e)
	{
		player.setHp(10); // Ensure player HP resets to initial 10 if stats are re-rolled
		int[] stats = new int[5];
		for(int i=0;i<stats.length;i++)
		{
			stats[i] = diceRoll();
			stats[i] += (stats[i]-10)/2;	// (stats[i]-10)/2 is the stat modifier bonus
		}
		
		player.setStats("Strength", stats[0]);
		player.setStats("Perception", stats[1]);
		player.setStats("Intelligence", stats[2]);
		player.setStats("Agility", stats[3]);
		player.setStats("Luck", stats[4]);
		player.setHp((stats[0]/2)+player.getHp()); // HP gets bonus modifier based on Strength/2
		
		STR.setText(Integer.toString(stats[0]));
		PER.setText(Integer.toString(stats[1]));
		INT.setText(Integer.toString(stats[2]));
		AGI.setText(Integer.toString(stats[3]));
		LUC.setText(Integer.toString(stats[4]));
		HP.setText(Integer.toString(player.getHp()));
	}
	
	/**
	 * Rolls 4d6 and adds 3 highest dice
	 * to determine a character's stat number
	 * @return	a stat number
	 */
	public int diceRoll()
	{
		int stat = 0;
		int[] roll = new int[4];
		int min = 999;
		
		// roll 4d6 (4 six-sided die)
		for(int i=0;i<roll.length;i++)
			roll[i] = (int) (Math.random()*6+1);
		
		// find lowest rolled die
		for(int j=0;j<roll.length;j++)
		{
			if(roll[j] < min )
				min = roll[j];
		}
		
		// add up 3 highest dice
		for(int k=0;k<roll.length;k++)
		{
			if(roll[k] == min)
			{
				roll[k] = 0;	// remove lowest die
				min = 999;		// in case lowest number occurs twice (e.g. 6,4,2,2) only remove one -> (e.g. 6,4,0,2)
			}
			stat += roll[k];
		}
		
		return stat;
	}

}
