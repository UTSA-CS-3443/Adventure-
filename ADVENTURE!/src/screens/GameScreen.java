package screens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameScreen {

	private static Scene scene;
	private static BorderPane root;
	
	public GameScreen(Stage stage) {
		try {
			root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
			scene = new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
			stage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BorderPane getRoot()
	{
		return root;
	}
	
	public static Scene getScene()
	{
		return scene;
	}

}
