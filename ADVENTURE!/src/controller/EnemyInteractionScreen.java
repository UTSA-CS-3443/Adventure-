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
public class EnemyInteractionScreen extends AnchorPane implements EventHandler<ActionEvent>{

	Enemy enemy;
	Player player;
	ArrayList<File> inventoryImages;
	
	@FXML Text NAME;
	@FXML Text HP;
	@FXML Text STR;
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text AGI;
	@FXML Text LUC;
	@FXML Pagination PAGINATION;
	@FXML TextFlow DESC;
	@FXML VBox CHOICES;
	@FXML VBox stockImages;
	@FXML ImageView IMAGE;
	
	Text dialogue;
		
	/**
	 * Load up the NPC screen for interaction with by the player
	 * 
	 * N is the generic NPC
	 */
	public EnemyInteractionScreen(Player p, Enemy e) {
		this.enemy = e;
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
		
		
		NAME.setText(this.player.getName());
		HP.setText(Integer.toString(this.player.getHp()));
		STR.setText(player.stats.get("STR").toString());
		PER.setText(player.stats.get("PER").toString());
		INT.setText(player.stats.get("INT").toString());
		AGI.setText(player.stats.get("AGI").toString());
		LUC.setText(player.stats.get("LUC").toString());
		
		ObservableList<Node> list = DESC.getChildren();
		
		//add some information to the readout list descriptor
		dialogue.setText("How can I help you?");
		list.add(dialogue);
		
		IMAGE.setImage(enemy.getImage());

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
		
	}
	
	public void buyItem(String text)
	{
		int cost = 0;
		player.setWalletAmt(player.getWalletAmt() - cost);
	}
	
	public void createPagination()
	{
		PAGINATION = new Pagination(player.getInventoryLength());
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
        for(int i = 0; i < player.getInventoryLength(); i++)
        {
        	//load file based on npc inventory and length
        	fName = player.getItemFromInventory(i);
        	File f = new File(fName);
        	inventoryImages.add(f);
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(inventoryImages.get(index));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imageView.setFitWidth(400);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {
            //some exception handling
        	System.out.println("Could not load image file");
        }
         
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);
        return pageBox;
    }
}