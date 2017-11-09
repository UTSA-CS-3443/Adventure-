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
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import locations.Location;
import locations.ReadLocations;
import npc.ShopKeeper;
import objects.Player;

/**
 * Loads Game Screen scene and handles player actions. This is where the game is played
 * @author JASON
 *
 */
public class NPCInteractionScreen<N> extends AnchorPane implements EventHandler<ActionEvent>{

	Location loc;
	
	
	@FXML Text NAME;
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text LUC;
	@FXML Pagination stock;
	@FXML TextFlow DESC;
	@FXML VBox CHOICES;
	@FXML VBox stockImages;
	Text locName;
	Text locDesc;
	
	/**
	 * Load up the NPC screen for interaction with by the player
	 * 
	 * N is the generic NPC
	 */
	public NPCInteractionScreen(Player p, N npc) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/NPCInteractionScreen.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		NAME.setText(p.getName());
		PER.setText(p.stats.get("Perception").toString());
		INT.setText(p.stats.get("Intelligence").toString());
		LUC.setText(p.stats.get("Luck").toString());
		
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
