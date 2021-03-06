package locations;

import java.util.ArrayList;
/**
 * A place in the game world. A location has pointers to other locations
 * and lists actions that can be taken in the location.
 * @author JASON
 *
 */
import java.util.HashMap;

import javafx.scene.image.Image;
public class Location {
	
	private String locName; // Name of location
	private String locDesc; // Description of location
	private String locImage; // Image to use for the location
	private String locEvent; // An Event
	private boolean hasEvent;
	private String locWorldAttributes[]; // Array of world attributes for a particular location
	public HashMap<String, String> relativeLoc = new HashMap<String, String>();
	private Location prevLoc;
	private Image image;
	
	public Location()
	{
		//initialize the name, image, relative locations, and attributes of a particular location
		locName = "";
		locDesc = "";
		locImage = "";
		prevLoc = null;
		locEvent = "";
		hasEvent = false;
	}
	
	public void setImage(Image img)
	{
		this.image = img;
	}
	
	public Image getImage()
	{
		return this.image;
	}
	
	public void setNorth(String value)
	{
		relativeLoc.put("north", value);
	}
	
	public void setSouth(String value)
	{
		relativeLoc.put("south", value);
	}
	public void setEast(String value)
	{
		relativeLoc.put("east", value);

	}
	public void setWest(String value)
	{
		relativeLoc.put("west", value);

	}
	public void setWorldAttributes(String[] attr)
	{
		locWorldAttributes = attr;
	}

	public void setEvent(String event)
	{
		locEvent = event;
		hasEvent = true;
	}
	
	public String getEvent()
	{
		return locEvent;
	}
	
	public boolean hasEvent()
	{
		return hasEvent;
	}
	
	public void setHasEvent(boolean status)
	{
		hasEvent = false;
	}
	
	public String[] getWorldAttributes()
	{
		return locWorldAttributes;
	}


	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocDesc() {
		return locDesc;
	}

	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}

	public String getLocImage() {
		return locImage;
	}

	public void setLocImage(String locImage) {
		this.locImage = locImage;
	}
	

	//this particular block of information is being worked on currently with relation to locationlayout.txt
	/* locations will load in data from a file to determine
	 * what type of buttons to generate
	 * Locations: e.g. other locations buttons like a tavern
	 * Actions: e.g. Ask Inn Keeper about rumors
	 * 
	 */

}
