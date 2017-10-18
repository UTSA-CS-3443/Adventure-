package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class CharacterCreator extends Pane{
	
	public CharacterCreator()
	{
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
		GameScreen gs = new GameScreen();
		Main.stage.setScene(new Scene(gs));
		Main.stage.show();
	}

}
