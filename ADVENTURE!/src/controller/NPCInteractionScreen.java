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
import items.potions.HealthPotion;
import items.potions.Potion;
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
import objects.NPC;
import objects.Player;

/**
 * Loads Game Screen scene and handles player actions. This is where the game is played
 * @author Tyler Rasmussen and Jason Morales
 *
 */
public class NPCInteractionScreen extends AnchorPane {

	NPC npc;
	Player player;
	ArrayList<Image> inventoryImages;

	@FXML Text NAME;
	@FXML Text HP;
	@FXML Text STR;
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text AGI;
	@FXML Text LUC;
	@FXML Text MONEY;
	@FXML Button buy;
	@FXML Pagination PAGINATION;
	@FXML TextFlow DESC;
	@FXML VBox CHOICES;
	@FXML VBox stockImages;
	@FXML ImageView IMAGE;
	Text dialogue;
	ObservableList<Node> list;
	/**
	 * Load up the NPC screen for interaction with by the player
	 * 
	 * N is the generic NPC
	 */
	public NPCInteractionScreen(Player p, NPC n) {
		this.npc = n;
		this.player = p;
		this.inventoryImages = new ArrayList<Image>();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NPCInteractionScreen.fxml"));
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

		list = DESC.getChildren();
		//add some information to the readout list descriptor
		dialogue =  new Text(npc.getSpeech());
		list.add(dialogue);
		IMAGE.setImage(npc.getImage());

		//initalize the pagination of item selections
		if(npc.getMerchantStatus() == 1 && npc.getInventoryLength() > 0)
		{
			createPagination();
		}
		else if(npc.getMerchantStatus() == 0 && npc.getInventoryLength() > 0)
		{
			PAGINATION.setVisible(false);
			buy.setText("Rent Room");
		}
		else
		{
			buy.setVisible(false);
			PAGINATION.setVisible(false);
			list.add(new Text("\n\nI'm all sold out!"));
		}
		
	}


	@FXML
	public void buy(ActionEvent event) {
		Button b = (Button)event.getSource();
		buyItem( b.getText() );
	}

	@FXML
	public void leave(ActionEvent event) 
	{
		String formattedHP = String.format("%d/%d", player.getHp(), player.getMaxHP());
		Main.gs.HP.setText(formattedHP);
		Main.gs.STR.setText(player.stats.get("STR").toString());
		Main.gs.PER.setText(player.stats.get("PER").toString());
		Main.gs.INT.setText(player.stats.get("INT").toString());
		Main.gs.AGI.setText(player.stats.get("AGI").toString());
		Main.gs.LUC.setText(player.stats.get("LUC").toString());
		Main.gs.MONEY.setText(Integer.toString(player.getWalletAmt()));
		Main.stage.setScene(Main.mainGame);
		Main.stage.show();
	}

	public void update()
	{
		if(npc.getMerchantStatus() == 1 && npc.getInventoryLength() > 0)
		{
			createPagination();
			dialogue.setText("Anything else?");
		}
		else
		{
			buy.setVisible(false);
			PAGINATION.setVisible(false);
			list.add(new Text("\n\nI'm all sold out!"));
		}
		
		String formattedHP = String.format("%d/%d", player.getHp(), player.getMaxHP());
		HP.setText(formattedHP);
		STR.setText(player.stats.get("STR").toString());
		PER.setText(player.stats.get("PER").toString());
		INT.setText(player.stats.get("INT").toString());
		AGI.setText(player.stats.get("AGI").toString());
		LUC.setText(player.stats.get("LUC").toString());
		MONEY.setText(Integer.toString(player.getWalletAmt()));
	}

	public void buyItem(String text)
	{
		int cost = 0;
		cost = npc.getCost(npc.getItemFromInventory(PAGINATION.getCurrentPageIndex()));
		if(cost > player.getWalletAmt())
		{
			dialogue.setText("You don't have the coin for that, friend.");
			return;
		}
		player.setWalletAmt(player.getWalletAmt() - cost);
		if(npc.getItemFromInventory(PAGINATION.getCurrentPageIndex()).equals("Inn Room"))
		{
			dialogue.setText("You feel fully rejuvinated");
			player.setHp(player.getMaxHP());
			update();
			return;
		}
		if(npc.getItemFromInventory(PAGINATION.getCurrentPageIndex()).equals("Health Potion"))
		{
			Potion health = new HealthPotion();
			player.addHealthPotion(health);
			npc.removeItemFromInventory(npc.getItemFromInventory(PAGINATION.getCurrentPageIndex()), PAGINATION.getCurrentPageIndex());
			inventoryImages.remove(PAGINATION.getCurrentPageIndex());
			update();
			return;
		}
		player.addItemToInventory(npc.getItemFromInventory(PAGINATION.getCurrentPageIndex()), cost);
		String itemPic = npc.getItemFromInventory(PAGINATION.getCurrentPageIndex());
		itemPic = String.format("/images/%s%s", itemPic, ".jpg");
		Image itemPicImg = new Image(itemPic);
		ImageView inv = new ImageView(itemPicImg);
		inv.setFitWidth(70);
		inv.setFitHeight(70);
		Main.gs.INV.getChildren().add(inv);
		npc.removeItemFromInventory(npc.getItemFromInventory(PAGINATION.getCurrentPageIndex()), PAGINATION.getCurrentPageIndex());
		inventoryImages.remove(PAGINATION.getCurrentPageIndex());
		update();
	}

	public void createPagination()
	{
		PAGINATION.setMaxPageIndicatorCount(npc.getStockAmt());
		PAGINATION.setCurrentPageIndex(0);
		PAGINATION.setPageCount(npc.getStockAmt());
		PAGINATION.setPageFactory(new Callback<Integer, Node>() 
		{
			@Override
			public Node call(Integer pageIndex) 
			{
				return createPage(pageIndex);
			}
		});
	}

	//load up the individual pages to put into the pagination of inventory
	//example code found online and modified to suit our use case
	public VBox createPage(int index)
	{
		ImageView imageView = new ImageView();
		String fName = ""; 
		//load our files into an array
		//load file based on npc inventory and length
		for(int i=0;i<npc.getStockAmt();i++)
		{
			fName = npc.getItemFromInventory(i);
			fName = String.format("/images/%s%s", fName, ".jpg");
			try{
				Image f = new Image(fName);
				inventoryImages.add(f);
			}
			catch(Exception e)
			{
				System.out.printf("Unable to load image file: %s\n", fName);
			}
		}
		
		try {
			imageView.setImage(inventoryImages.get(index));
			imageView.setFitWidth(200);
			imageView.setFitHeight(200);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);
			imageView.setCache(true);
		} catch (Exception ex) {
			//some exception handling
			System.out.printf("Could not display image: %s\n", fName);
		}

		VBox pageBox = new VBox();
		pageBox.getChildren().add(imageView);
		return pageBox;
	}
}