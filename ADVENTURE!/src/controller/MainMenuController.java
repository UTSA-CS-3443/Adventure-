package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainMenuController extends AnchorPane{
	
	public MainMenuController()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MainMenu.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void handleNewGame(ActionEvent e)
	{	
		CharacterCreator cc = new CharacterCreator();
		Main.stage.setScene(new Scene(cc));
		Main.stage.show();
	}

}
