package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import application.Main;
import events.Event;
import events.EventReader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import objects.Enemy;
import objects.Player;
import controller.Game;
import enemies.*;

public class EventController extends AnchorPane{
	@FXML VBox CHOICES;
	@FXML TextFlow TITLE;
	@FXML TextFlow DESC;
	Text eventTitle;
	Text eventDesc;
	Event event;
	Stage eventStage;
	HashMap<String, Integer> stats = new HashMap<>();
	HashMap<String, Integer> statMods = new HashMap<>();
	private int hp;
	Player player;
	Enemy enemy;
	private boolean failedCheck;
	
	
	public EventController(Event _event, Stage stage)
	{
		event = _event;
		player = Main.gs.player.getPlayer();
		stats = player.getStats();
		statMods = player.getStatMods();
		hp = player.getHp();
		failedCheck = false;
		eventStage = stage;
		eventTitle = new Text("");
		eventDesc = new Text("");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/EventWindow.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ObservableList<Node> titleList = TITLE.getChildren();
		ObservableList<Node> descList = DESC.getChildren();
		
		eventTitle.setText(event.getName());
		eventDesc.setText(event.getDesc());
		titleList.add(eventTitle);
		descList.add(eventDesc);
		
		Set set = event.choices.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	          Map.Entry mentry = (Map.Entry)iterator.next();
	          Button button = new Button("" + mentry.getKey());
	          CHOICES.getChildren().add(button);
	          button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Button b = (Button)event.getSource();
					outcomes( b.getText() );
					eventStage.close();
				}
	          });
	      }
	}
	
	public void handle(ActionEvent event)
	{
		Button b = (Button)event.getSource();
		outcomes( b.getText() );
		eventStage.close();
	}
	
	public void outcomes(String text)
	{
		String outcome = event.choices.get(text);
		if(outcome.equals(""))
			return;
		
		String[] outcomeParser = outcome.split("~");
		String[] operation = null;
		
		for(int i=0;i<outcomeParser.length;i++)
		{
			operation = outcomeParser[i].split(" ");
			for(int j=0;j<operation.length;j++)
			{
				if(operation[0].equals("STR|PER|INT|AGI|LUC"))
				{
					if(operation[1].equals("+"))
					{
						if(failedCheck == true)
							break;
						player.setStats(operation[0], stats.get(operation[0]) + Integer.valueOf(operation[2]));
						if(operation[0].equals("STR"))
							player.setMaxHP((stats.get(operation[0])/2)+10);
					}
					else if(operation[0].equals("-"))
					{
						player.setStats(operation[0], stats.get(operation[0]) - Integer.valueOf(operation[2]));
						if(operation[0].equals("STR"))
							player.setMaxHP((stats.get(operation[0])/2)+10);
					}
					else
						break;
				}
				else if(operation[0].equals("HP"))
				{
					if(operation[1].equals("+"))
					{
						if(failedCheck == true)
							break;
						player.setHp(hp + Integer.valueOf(operation[2]));
						if(player.getHp() > player.getMaxHP())
							player.setHp(hp - Integer.valueOf(operation[2]));
					}
					else if(operation[1].equals("-"))
					{
						player.setHp(hp - Integer.valueOf(operation[2]));
					}
					else
						break;
				}
				else if(operation[0].equals("ROLL"))
				{
					int die = (int) (Math.random()*20+1);
					
					if(die == 20)
					{
						failedCheck = false;
						continue;
					}
					else if(die == 1)
					{
						failedCheck = true;
						continue;
					}
					else if(die + statMods.get(operation[1]) < Integer.valueOf(operation[2]))
					{
						failedCheck = true;
						continue;
					}
					else
					{
						failedCheck = false;
						continue;
					}
				}
				else if(operation[0].equals("ENCOUNTER"))
				{
					if(operation[1].equals("BANDIT"))
					{
						enemy = new Bandit();
					}
					else if(operation[1].equals("GOBLIN"))
					{
						enemy = new Goblin();
					}
					else if(operation[1].equals("ORC"))
					{
						enemy = new Orc();
					}
					else if(operation[1].equals("WILDBEAST"))
					{
						enemy = new WildBeast();
					}
					Main.stage.setScene(new Scene(new EnemyInteractionScreen(player, enemy)));
					Main.stage.show();
				}
			}
		}
		
		
	}
}
