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
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text LUC;
	@FXML Text WALLET;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/NPCInteractionScreen.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}


		NAME.setText(this.player.getName());
		PER.setText(this.player.stats.get("PER").toString());
		INT.setText(this.player.stats.get("INT").toString());
		LUC.setText(this.player.stats.get("LUC").toString());
		WALLET.setText(String.valueOf(p.getWalletAmt())); 

		list = DESC.getChildren();
		//add some information to the readout list descriptor
		dialogue =  new Text("How can I help you?");
		list.add(dialogue);
		IMAGE.setImage(npc.getImage());

		//initalize the pagination of item selections
		if(npc.getMerchantStatus() == 1 && npc.getInventoryLength() > 0)
		{
			createPagination();
		}
		else
		{
			buy.setVisible(false);
			PAGINATION.setVisible(false);
			list.add(new Text("\n\nI'm all sold out!"));
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


	@FXML
	public void buy(ActionEvent event) {
		Button b = (Button)event.getSource();
		buyItem( b.getText() );
	}

	@FXML
	public void leave(ActionEvent event) 
	{
		Main.stage.setScene(Main.mainGame);
		Main.stage.show();
	}

	public void update()
	{
		if(npc.getMerchantStatus() == 1 && npc.getInventoryLength() > 0)
		{
			createPagination();
		}
		else
		{
			buy.setVisible(false);
			PAGINATION.setVisible(false);
			list.add(new Text("\n\nI'm all sold out!"));
		}
	}

	public void buyItem(String text)
	{
		int cost = 0;
		cost = npc.getCost(npc.getItemFromInventory(PAGINATION.getCurrentPageIndex()));
		player.setWalletAmt(player.getWalletAmt() - cost);
		player.addItemToInventory(npc.getItemFromInventory(PAGINATION.getCurrentPageIndex()), cost);
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