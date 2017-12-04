package controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Game;
import objects.Player;

public class DeathScreen extends AnchorPane{
	
	public DeathScreen() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeathScreen.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void restart()
	{
		Main.game = new Game();
	}
	
	@FXML
	public void mainMenu()
	{
		Main.stage.setScene(Main.mainMenuScreen);
	}
}
