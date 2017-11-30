package controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import application.Main;
import events.EventReader;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import locations.Location;
import locations.ReadLocations;
import objects.Player;

/**
 * Loads Game Screen scene and handles player actions. This is where the game is played
 * @author JASON
 *
 */
public class GameScreen extends AnchorPane implements EventHandler<ActionEvent>{
	public Player player;
	Location loc;
	Stage eventStage;
	
	
	@FXML Text NAME;
	@FXML Text HP;
	@FXML Text STR;
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text AGI;
	@FXML Text LUC;
	@FXML Text MONEY;
	@FXML ImageView IMAGE;
	@FXML HBox INV;
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
		eventStage = new Stage();
		eventStage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/GameScreenV2.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		NAME.setText(this.player.getName());
		String formattedHP = String.format("%d/%d", player.getHp(), player.getMaxHP());
		HP.setText(formattedHP);
		STR.setText(player.stats.get("STR").toString());
		PER.setText(player.stats.get("PER").toString());
		INT.setText(player.stats.get("INT").toString());
		AGI.setText(player.stats.get("AGI").toString());
		LUC.setText(player.stats.get("LUC").toString());
		MONEY.setText(Integer.toString(player.getWalletAmt()));
		
		ObservableList<Node> list = DESC.getChildren();
		
		loc = ReadLocations.locations.get(1);
		System.out.println(ReadLocations.locations.get(1));
		System.out.println(loc.getLocName());
		
		locName = new Text(loc.getLocName() + "\n\n");
		locDesc = new Text(loc.getLocDesc());
		list.add(locName);
		list.add(locDesc);
		IMAGE.setImage(loc.getImage());

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
		
		if(Game.NPCM.containsKey(text))
		{
			Main.stage.setScene(new Scene(new NPCInteractionScreen(player, Game.NPCM.get(text))));
			Main.stage.show();
			return;
		}
		
		CHOICES.getChildren().clear();
		loc = ReadLocations.locations.get(ReadLocations.locationIndex.get(text));
		this.locName.setText(loc.getLocName() + "\n\n");
		this.locDesc.setText(loc.getLocDesc());
		IMAGE.setImage(loc.getImage());
		
		if(loc.hasEvent())
		{
			events.Event locEvent = EventReader.eventsM.get(EventReader.eventIndex.get(loc.getEvent()));
			eventStage.setScene(new Scene(new EventController(locEvent, eventStage), 900, 600));
			eventStage.showAndWait();
			String formattedHP = String.format("%d/%d", player.getHp(), player.getMaxHP());
			HP.setText(formattedHP);
			STR.setText(player.stats.get("STR").toString());
			PER.setText(player.stats.get("PER").toString());
			INT.setText(player.stats.get("INT").toString());
			AGI.setText(player.stats.get("AGI").toString());
			LUC.setText(player.stats.get("LUC").toString());
			MONEY.setText(Integer.toString(player.getWalletAmt()));
			loc.setHasEvent(false);
		}
		
		if(player.getHp() <= 0)
		{
			Main.stage.setScene(Main.death);
			return;
		}
		
		
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
