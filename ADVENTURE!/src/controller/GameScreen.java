package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class GameScreen extends BorderPane{
	
	public GameScreen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/GameScreen.fxml"));
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
