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

public class CharacterCreator extends Pane{
	
	Player player;
	
	@FXML Text STR;
	@FXML Text INT;
	@FXML Text PER;
	@FXML Text AGI;
	@FXML Text LUC;
	@FXML Text HP;
	@FXML TextField NAME;
	
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
	
	@FXML
	protected void handleBegin(ActionEvent e)
	{
		player.setName(NAME.getText());
		GameScreen gs = new GameScreen(player);
		Main.stage.setScene(new Scene(gs));
		Main.stage.show();
	}
	
	@FXML
	protected void handleRollStats(ActionEvent e)
	{
		int[] stats = new int[5];
		for(int i=0;i<stats.length;i++)
			stats[i] = (int) (Math.random()*20+5);
		
		player.setStats(stats);
		
		STR.setText(Integer.toString(stats[0]));
		PER.setText(Integer.toString(stats[1]));
		INT.setText(Integer.toString(stats[2]));
		AGI.setText(Integer.toString(stats[3]));
		LUC.setText(Integer.toString(stats[4]));
		HP.setText(Integer.toString((stats[0]/2)+10));
		player.setHp((stats[0]/2)+player.getHp());
	}

}
