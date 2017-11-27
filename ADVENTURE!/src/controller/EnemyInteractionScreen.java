package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import application.Main;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import locations.Location;
import locations.ReadLocations;
import npc.ShopKeeper;
import objects.Enemy;
import objects.NPC;
import objects.Player;

/**
 * Loads Game Screen scene and handles player actions. This is where the game is played
 * @author Tyler Rasmussen and Jason Morales
 *
 */
public class EnemyInteractionScreen extends AnchorPane {

	Enemy enemy;
	Player player;
	ArrayList<File> inventoryImages;
	
	//player stats
	@FXML Text HP;
	@FXML Text STR;
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text AGI;
	@FXML Text LUC;
	
	//enemy stats
	@FXML Text EHP;
	@FXML Text ESTR;
	@FXML Text EPER;
	@FXML Text EINT;
	@FXML Text EAGI;
	@FXML Text ELUC;
	
	@FXML Pagination PAGINATION;
	@FXML TextFlow BATTLELOG;
	@FXML VBox stockImages;
	@FXML ImageView IMAGE;
	@FXML Button ATTACK;
	@FXML Button PARRY;
	@FXML Button USE;
	@FXML Button RUN;
	@FXML Button END;
	@FXML Button LOOT;
	
	Text dialogue;
	ObservableList<Node> list;
	
	Text enemyDodge = new Text("You missed\n");
	Text enemyHit = new Text("You were hit\n");
	Text playerDodge = new Text("Enemy missed\n");
	Text playerHit = new Text("Enemy hit for\n");
	Text opportunity = new Text("Attack of Opportunity!\n");
	Text fleeMinorSuccess = new Text("You escaped, but they were able to hit you.\n");
	Text fleeMajorSuccess = new Text("You escaped!\n");
	Text victory = new Text("Your foe is vanquished.\n");
	Text looted;
	Text death = new Text("You have been slain.\n");
	Text parrySuc = new Text("Attack Parried!");
	Text parryFail = new Text("Parry Failed!");
	
	String enemyMoney;
	int eDodgeDie;
	int pDodgeDie;
	int eHitDie;
	int pHitDie;
	int eAtkDie;
	int pAtkDie;
	int parryDie;
	boolean parrySuccess;
		
	/**
	 * Load up the enemy screen for interaction with by the player
	 * 
	 * e is the generic Enemy
	 */
	public EnemyInteractionScreen(Player p, Enemy e) {
		this.enemy = e;
		enemyMoney = Integer.valueOf(enemy.getMoney()).toString();
		looted = new Text("You looted " + enemyMoney + "gold");
		this.player = p;
		this.inventoryImages = new ArrayList<File>();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/EnemyInteractionScreen.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		HP.setText(Integer.toString(this.player.getHp()));
		STR.setText(player.stats.get("STR").toString());
		PER.setText(player.stats.get("PER").toString());
		INT.setText(player.stats.get("INT").toString());
		AGI.setText(player.stats.get("AGI").toString());
		LUC.setText(player.stats.get("LUC").toString());
		
		EHP.setText(Integer.toString(this.enemy.getHp()));
		ESTR.setText(enemy.stats.get("STR").toString());
		EPER.setText(enemy.stats.get("PER").toString());
		EINT.setText(enemy.stats.get("INT").toString());
		EAGI.setText(enemy.stats.get("AGI").toString());
		ELUC.setText(enemy.stats.get("LUC").toString());
		
		list = BATTLELOG.getChildren();
		
		//add some information to the readout list descriptor
		dialogue = new Text("Now would be a goodtime to defend yourself.\n");
		list.add(dialogue);
		
		IMAGE.setImage(enemy.getImage());

	}
	
	
	public void update()
	{	
		HP.setText(Integer.toString(this.player.getHp()));
		STR.setText(player.stats.get("STR").toString());
		PER.setText(player.stats.get("PER").toString());
		INT.setText(player.stats.get("INT").toString());
		AGI.setText(player.stats.get("AGI").toString());
		LUC.setText(player.stats.get("LUC").toString());
		
		EHP.setText(Integer.toString(this.enemy.getHp()));
		ESTR.setText(enemy.stats.get("STR").toString());
		EPER.setText(enemy.stats.get("PER").toString());
		EINT.setText(enemy.stats.get("INT").toString());
		EAGI.setText(enemy.stats.get("AGI").toString());
		ELUC.setText(enemy.stats.get("LUC").toString());
		
		if(enemy.getHp() <= 0)
		{
			ATTACK.setDisable(true);
			PARRY.setDisable(true);
			USE.setDisable(true);
			RUN.setDisable(true);
			LOOT.setDisable(false);
			END.setDisable(false);
			list.add(victory);
			return;
		}
		
		if(parrySuccess == true)
		{
			parrySuccess = false;
			PARRY.setDisable(false);
			return;
		}
		enemyAttack();
		
		if(player.getHp() <= 0)
		{
			ATTACK.setDisable(true);
			PARRY.setDisable(true);
			USE.setDisable(true);
			RUN.setDisable(true);
			LOOT.setDisable(true);
			END.setDisable(false);
			list.add(death);
			return;
		}
		
		HP.setText(Integer.toString(this.player.getHp()));
		STR.setText(player.stats.get("STR").toString());
		PER.setText(player.stats.get("PER").toString());
		INT.setText(player.stats.get("INT").toString());
		AGI.setText(player.stats.get("AGI").toString());
		LUC.setText(player.stats.get("LUC").toString());
		
		EHP.setText(Integer.toString(this.enemy.getHp()));
		ESTR.setText(enemy.stats.get("STR").toString());
		EPER.setText(enemy.stats.get("PER").toString());
		EINT.setText(enemy.stats.get("INT").toString());
		EAGI.setText(enemy.stats.get("AGI").toString());
		ELUC.setText(enemy.stats.get("LUC").toString());
	}
	
	@FXML
	public void parry()
	{
		eHitDie = (int) (Math.random()*20+1);
		parryDie = (int) (Math.random()*20+1);
		eAtkDie = (int) (Math.random()*6+1);
		pAtkDie = (int) (Math.random()*6+1);
		
		if(player.statMods.get("AGI") + parryDie >= enemy.statMods.get("AGI") + eHitDie)
		{
			parrySuccess = true;
			PARRY.setDisable(true);
			parrySuc = new Text("Attack Parried!\n");
			list.add(parrySuc);
			return;
		}
		else
		{
			parrySuccess = false;
			parryFail = new Text("Parry Failed!\n");
			list.add(parryFail);
			player.setHp(player.getHp() - ((enemy.statMods.get("STR") + eAtkDie)/2));
			playerHit = new Text("You were hit.\n");
			list.add(playerHit);
			if(player.getHp() <= 0)
			{
				ATTACK.setDisable(true);
				PARRY.setDisable(true);
				USE.setDisable(true);
				RUN.setDisable(true);
				LOOT.setDisable(true);
				END.setDisable(false);
				list.add(death);
				return;
			}
			update();
		}
	}
	
	@FXML
	public void attack()
	{	
		eHitDie = (int) (Math.random()*20+1);
		pHitDie = (int) (Math.random()*20+1);
		eAtkDie = (int) (Math.random()*6+1);
		pAtkDie = (int) (Math.random()*6+1);
		
		if(parrySuccess == true)
		{
			enemy.setHp(enemy.getHp() - (player.statMods.get("STR") + pAtkDie));
			enemyHit = new Text("Enemy was hit.\n");
			list.add(enemyHit);
			update();
			return;
		}
			
		if(enemy.stats.get("AGI") > player.statMods.get("AGI") + pHitDie)
		{
			enemyDodge = new Text("You missed.\n");
			list.add(enemyDodge);
			if(enemy.statMods.get("PER") + eHitDie > player.stats.get("AGI"))
			{
				opportunity = new Text("Attack of Opportunity!\n");
				list.add(opportunity);
				attackOppurtunity(0);
				if(player.getHp() <= 0)
					return;
			}
			update();
		}
		else
		{
			enemy.setHp(enemy.getHp() - (player.statMods.get("STR") + pAtkDie));
			enemyHit = new Text("Enemy was hit.\n");
			list.add(enemyHit);
			update();
		}
	}
	
	public void enemyAttack()
	{	
		eHitDie = (int) (Math.random()*20+1);
		pHitDie = (int) (Math.random()*20+1);
		eAtkDie = (int) (Math.random()*6+1);
		pAtkDie = (int) (Math.random()*6+1);
		
		if(player.stats.get("AGI") > enemy.statMods.get("AGI") + eHitDie)
		{
			playerDodge = new Text("Enemy missed.\n");
			list.add(playerDodge);
			if(player.statMods.get("PER") + pHitDie > enemy.stats.get("AGI"))
			{
				opportunity = new Text("Attack of Opportunity!\n");
				list.add(opportunity);
				attackOppurtunity(1);
				if(enemy.getHp() <= 0)
				{
					return;
				}
			}
		}
		else
		{
			player.setHp(player.getHp() - (enemy.statMods.get("STR") + eAtkDie));
			HP.setText(Integer.toString(this.player.getHp()));
			playerHit = new Text("You were hit.\n");
			list.add(playerHit);
		}
	}
	
	public void attackOppurtunity(int who)
	{
		eAtkDie = (int) (Math.random()*3+1);
		pAtkDie = (int) (Math.random()*3+1);
		
		if(who == 0)
		{
			player.setHp(player.getHp() - (enemy.statMods.get("STR") + eAtkDie));
			HP.setText(Integer.toString(this.player.getHp()));
			playerHit = new Text("You were hit.\n");
			list.add(playerHit);
			if(player.getHp() <= 0)
			{
				ATTACK.setDisable(true);
				PARRY.setDisable(true);
				USE.setDisable(true);
				RUN.setDisable(true);
				LOOT.setDisable(true);
				END.setDisable(false);
				list.add(death);
				return;
			}
		}
		else
		{
			enemy.setHp(enemy.getHp() - (player.statMods.get("STR") + pAtkDie));
			EHP.setText(Integer.toString(enemy.getHp()));
			enemyHit = new Text("Enemy was hit.\n");
			list.add(enemyHit);
			if(enemy.getHp() <= 0)
			{
				ATTACK.setDisable(true);
				PARRY.setDisable(true);
				USE.setDisable(true);
				RUN.setDisable(true);
				LOOT.setDisable(false);
				END.setDisable(false);
				list.add(victory);
				return;
				
			}
		}
	}
	
	@FXML
	public void usePotion()
	{
		
	}
	
	@FXML
	public void runAway()
	{
		ATTACK.setDisable(true);
		PARRY.setDisable(true);
		USE.setDisable(true);
		RUN.setDisable(true);
		END.setDisable(false);
		pDodgeDie = (int) (Math.random()*20+1);
		eHitDie = (int) (Math.random()*20+1);
		eAtkDie = (int) (Math.random()*6+1);
		
		if(player.statMods.get("LUC") + player.statMods.get("AGI") + pDodgeDie < enemy.statMods.get("AGI") + eHitDie)
		{
			player.setHp(player.getHp() - (enemy.statMods.get("STR") + eAtkDie));
			list.add(fleeMinorSuccess);
		}
		else
		{
			list.add(fleeMajorSuccess);
		}
	}
	
	@FXML
	public void loot()
	{
		LOOT.setDisable(true);
		player.setWalletAmt(player.getWalletAmt() + enemy.getMoney());
		enemy.setMoney(0);
		list.add(looted);
	}
	
	@FXML
	public void endBattle()
	{
		if(player.getHp() <= 0)
		{
			Main.stage.setScene(Main.death);
			Main.stage.show();
			return;
		}
		Main.gs.HP.setText(Integer.toString(this.player.getHp()));
		Main.gs.STR.setText(player.stats.get("STR").toString());
		Main.gs.PER.setText(player.stats.get("PER").toString());
		Main.gs.INT.setText(player.stats.get("INT").toString());
		Main.gs.AGI.setText(player.stats.get("AGI").toString());
		Main.gs.LUC.setText(player.stats.get("LUC").toString());
		Main.gs.MONEY.setText(Integer.toString(player.getWalletAmt()));
		Main.stage.setScene(Main.mainGame);
		Main.stage.show();
	}
}