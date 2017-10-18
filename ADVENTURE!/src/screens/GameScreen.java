package screens;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameScreen {

	private static Scene scene;
	
	public GameScreen() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
			scene = new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
			Main.stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Scene getScene()
	{
		return scene;
	}

}
