package core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import screens.MainMenu;;

public class Adventure extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	
// this was all testing out javafx. code should be moved to more appropriate
// classes/packages.
// currently this makes an extremely basic main menu with non-functioning buttons.
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("ADVENTURE!");
		
		Group root = new Group();
		Scene theScene = new Scene(root);
		stage.setScene(theScene);
		
		Canvas canvas = new Canvas(1280,800);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(2);
		Font font = Font.font("Times New Roman", FontWeight.BOLD, 48);
		gc.setFont(font);
		gc.fillText("ADVENTURE!", 60, 50);
		gc.strokeText("ADVENTURE!", 60, 50);
		//*/
		
		//background image
		
		VBox box = new VBox();
		box.setLayoutX(60);
		box.setLayoutY(100);
		box.setSpacing(10);
		
		Button newGame = new Button("New Game");
		newGame.setFont(font);
		box.getChildren().add(newGame);
		//newGame.setOnAction(event -> startGame());
		
		Button quit = new Button("Quit");
		quit.setFont(font);
		box.getChildren().add(quit);
		//quit.setOnAction(event -> exit());
		
		root.getChildren().add(box);
		//*/
		stage.show();
	}
	
	

}
