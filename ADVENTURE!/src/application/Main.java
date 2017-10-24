package application;
	
import controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Launches the game and begins at the Main Menu
 * @author JASON
 *
 */
public class Main extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		stage.setTitle("ADVENTURE!");
		
		MainMenuController menu = new MainMenuController();
		stage.setScene(new Scene(menu, 900, 600));
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
