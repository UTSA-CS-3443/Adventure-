package locations;

/**
 * A place in the game world. A location has pointers to other locations
 * and lists actions that can be taken in the location.
 * @author JASON
 *
 */
import java.util.HashMap;
public class Location {
	
	private static String locName; // Name of location
	private static String locDesc; // Description of location
	private static String locImage; // Image to use for the location
	private static String locAttributes[]; // Array of attributes for a given location
	private static HashMap<String, String> relativeLoc = new HashMap<String, String>();
	public Location()
	{
		//initialize the name, image, relative locations, and attributes of a particular location
		
	}
	
	public static void setNorth(String value)
	{
		relativeLoc.put("north", value);
	}
	
	public static void setSouth(String value)
	{
		relativeLoc.put("south", value);

	}
	public static void setEast(String value)
	{
		relativeLoc.put("east", value);

	}
	public static void setWest(String value)
	{
		relativeLoc.put("west", value);

	}
	public static void setAttr(String[] attr)
	{
		locAttributes = attr;
	}

	public static String getLocName() {
		return locName;
	}

	public static void setLocName(String locName) {
		Location.locName = locName;
	}

	public static String getLocDesc() {
		return locDesc;
	}

	public static void setLocDesc(String locDesc) {
		Location.locDesc = locDesc;
	}

	public static String getLocImage() {
		return locImage;
	}

	public static void setLocImage(String locImage) {
		Location.locImage = locImage;
	}

	public static String[] getLocAttributes() {
		return locAttributes;
	}

	public static void setLocAttributes(String locAttributes[]) {
		Location.locAttributes = locAttributes;
	}

	//this particular block of information is being worked on currently with relation to locationlayout.txt
	/* locations will load in data from a file to determine
	 * what type of buttons to generate
	 * Locations: e.g. other locations buttons like a tavern
	 * Actions: e.g. Ask Inn Keeper about rumors
	 * 
	 */

}
