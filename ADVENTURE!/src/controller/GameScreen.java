package controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import locations.Location;
import locations.ReadLocations;
import objects.Player;

/**
 * Loads Game Screen scene and handles player actions. This is where the game is played
 * @author JASON
 *
 */
public class GameScreen extends AnchorPane implements EventHandler<ActionEvent>{
	public static Player player;
	Location loc;
	
	
	@FXML Text NAME;
	@FXML Text HP;
	@FXML Text STR;
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text AGI;
	@FXML Text LUC;
	@FXML ImageView IMAGE;
	Text locName;
	Text locDesc;
	
	@FXML TextFlow DESC;
	@FXML VBox CHOICES;
	
	/**
	 * Loads the Game Screen and initializes the player character that was created in Character Creator.
	 * @param p Player character from Character Creator
	 */
	public GameScreen(Player p) {
		this.player = p;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/GameScreenV2.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		IMAGE.setImage(loc.getImage());
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		NAME.setText(this.player.getName());
		HP.setText(Integer.toString(this.player.getHp()));
		STR.setText(player.stats.get("Strength").toString());
		PER.setText(player.stats.get("Perception").toString());
		INT.setText(player.stats.get("Intelligence").toString());
		AGI.setText(player.stats.get("Agility").toString());
		LUC.setText(player.stats.get("Luck").toString());
		
		ObservableList<Node> list = DESC.getChildren();
		
		loc = ReadLocations.locations.get(1);
		System.out.println(ReadLocations.locations.get(1));
		System.out.println(loc.getLocName());
		
		locName = new Text(loc.getLocName() + "\n\n");
		locDesc = new Text(loc.getLocDesc());
		list.add(locName);
		list.add(locDesc);
		
		Set set = loc.relativeLoc.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	          Map.Entry mentry = (Map.Entry)iterator.next();
	          Button button = new Button("" + mentry.getValue());
	          CHOICES.getChildren().add(button);
	          button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Button b = (Button)event.getSource();
					update( b.getText() );
				}
	          });
	      }
	}
	
	/**
	 * Exit to Desktop
	 * @param e ActionEvent Quit button was clicked
	 */
	@FXML
	protected void handleQuit(ActionEvent e)
	{	
		Platform.exit();
	}
	
	@Override
	public void handle(ActionEvent event) {
		Button b = (Button)event.getSource();
		update( b.getText() );
	}
	
	public void update(String text)
	{
		CHOICES.getChildren().clear();
		loc = ReadLocations.locations.get(ReadLocations.locationIndex.get(text));
		this.locName.setText(loc.getLocName() + "\n\n");
		this.locDesc.setText(loc.getLocDesc());
		IMAGE.setImage(loc.getImage());
		
		Set set2 = loc.relativeLoc.entrySet();
	      Iterator iterator2 = set2.iterator();
	      while(iterator2.hasNext()) {
	          Map.Entry mentry2 = (Map.Entry)iterator2.next();
	          Button button2 = new Button("" + mentry2.getValue());
	          CHOICES.getChildren().add(button2);
	          button2.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						Button b = (Button)event.getSource();
						update( b.getText() );
					}
		          });
	      }
	}
}
