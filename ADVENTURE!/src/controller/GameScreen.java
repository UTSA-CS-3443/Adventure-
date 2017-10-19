package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class GameScreen extends AnchorPane{
	
	public GameScreen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/GameScreenV2.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void handleQuit(ActionEvent e)
	{	
		Platform.exit();
	}

}
