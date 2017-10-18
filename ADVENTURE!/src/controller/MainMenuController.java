package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import screens.GameScreen;

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
		//FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/GameScreen.fxml"));
		GameScreen gs = new GameScreen();
		Main.stage.setScene(gs.getScene());
		Main.stage.show();
	}

}
