package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import screens.GameScreen;
import screens.MainMenu;

//work in progress screens should be loaded in from screens classes by completion
public class Main extends Application {
	
	private Scene scene;
	//BorderPane g = GameScreen.getRoot();
	GameScreen gs;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("ADVENTURE!");
		gs = new GameScreen(primaryStage);
		
		/*
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("/screens/GameScreen.fxml"));
			Scene scene = new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//*/
		
		scene = gs.getScene();
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
