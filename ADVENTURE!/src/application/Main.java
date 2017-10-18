package application;
	
import controller.MainMenuController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import screens.GameScreen;

//work in progress screens should be loaded in from screens classes by completion
public class Main extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		stage.setTitle("ADVENTURE!");
		
		MainMenuController menu = new MainMenuController();
		stage.setScene(new Scene(menu));
		stage.setWidth(900);
		stage.setHeight(600);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
