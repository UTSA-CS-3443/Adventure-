package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import objects.NPC;
import objects.Player;

/**
 * Loads Game Screen scene and handles player actions. This is where the game is played
 * @author Tyler Rasmussen and Jason Morales
 *
 */
public class NPCInteractionScreen extends AnchorPane implements EventHandler<ActionEvent>{

	NPC npc;
	
	@FXML Text NAME;
	@FXML Text PER;
	@FXML Text INT;
	@FXML Text LUC;
	@FXML Pagination stock;
	@FXML TextFlow DESC;
	@FXML VBox CHOICES;
	@FXML VBox stockImages;
	Text dialogue;
		
	/**
	 * Load up the NPC screen for interaction with by the player
	 * 
	 * N is the generic NPC
	 */
	public NPCInteractionScreen(Player p, NPC n) {
		this.npc = n;
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
		
		//add some information to the readout list descriptor
		dialogue.setText("How can I help you?");
		list.add(dialogue);

		//initalize the pagination of item selections
		if(npc.getMerchantStatus() == 1)
		{
			createPagination();
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
	
	public void buy(ActionEvent event) {
		Button b = (Button)event.getSource();
		buyItem( b.getText() );
	}
	
	public void leave(ActionEvent event) 
	{
		Main.stage.setScene(Main.mainGame);
	}
	
	public void update(String text)
	{
		
	}
	
	public void buyItem(String text)
	{
		
	}
	
	public void createPagination()
	{
		stock = new Pagination(npc.getStockAmt());
		stock.setPageFactory(new Callback<Integer, Node>() 
		{
			@Override
			public Node call(Integer pageIndex) 
			{
				return createPage(pageIndex);
			}
		});
	}
	
	//load up the individual pages to put into the pagination of inventory
	public VBox createPage(int index)
	{
		ImageView imageView = new ImageView();
		 
        File file = filesJpg[index];
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imageView.setFitWidth(400);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {
            //some exception handling
        }
         
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);
        return pageBox;
    }
}