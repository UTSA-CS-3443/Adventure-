package locations;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
	
/*
LOC>Start-Screen
DESC>"description of the location"
NORTH>WALL
SOUTH>WALL
EAST>village-a1
WEST>field-a3
ATTR>hasEnemies
ATTR>hasEvent\EventType
ATTR>...
ENDLOC>
*/

public class ReadLocations {

	public static HashMap<Integer, Location> locations = new HashMap<>();     // Map of locations indexed by number
	public static HashMap<String, Integer> locationIndex = new HashMap<>();   // Map of location indexes based on Name
																			  // Together these maps allow for custom starting location
																			  // and this is the primary reason locationIndex exists
	private String[] lineBuffer;
	private String[] attr;
	private String[] identifier;	// Parses the locations.txt file keywords to build a location
	private Scanner in;
	private Location loc;
	private int locationNum;		// Gives a sort of order to the locations HashMap. Primary use is to allow for a dynamic
									// starting location that is indexed as 1;
	
	public ReadLocations(URL defaultFile)
	{		
		lineBuffer = new String[2048];
		attr = new String[128];
		identifier = new String[2];
		in = null;
		loc = null;
		locationNum = 0;
		
		try 
		{
			in = new Scanner(new File(defaultFile.toURI()));
		} 
		catch (FileNotFoundException exception) 
		{
			    System.err.println("failed to open location data");
			    System.exit(1);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (in.hasNext()) 
		{
			//create a new location based on the read in data
			//we will manually read in the north, south, east, west, name, and description based on regex patterns then we will use another
			//while loop to keep going as long as the next line contains ATTR at the start, then we put the stuff in
			//the location and then start again at the top
			
			//all print lines are for debug purposes and should be removed when finished
			String line = in.nextLine();
			identifier = line.split(">");
			if(identifier[0].equals("LOC"))
			{
				loc = new Location();
				loc.setLocName(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}

			else if(identifier[0].equals("DESC"))
			{
				loc.setLocDesc(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
			else if(identifier[0].equals("NORTH"))
			{
				loc.setNorth(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
			else if(identifier[0].equals("SOUTH"))
			{
				loc.setSouth(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
				
			else if(identifier[0].equals("EAST"))
			{
				loc.setEast(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
				
			else if(identifier[0].equals("WEST"))
			{
				loc.setWest(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
			//for every attribute we have for the particular location, parse it
			else if(identifier[0].equals("EVENT"))
			{
				loc.setEvents(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1]);
			}
			
			else if(identifier[0].equals("ENDLOC"))
			{
				locationNum++;
				locations.put(locationNum, loc);
				locationIndex.put(loc.getLocName(), locationNum);
				
				// For debug purposes, prints the locations map to see if any are missing or mapped incorrectly
				Set set2 = locations.entrySet();
			      Iterator iterator2 = set2.iterator();
			      while(iterator2.hasNext()) {
			          Map.Entry mentry2 = (Map.Entry)iterator2.next();
			          System.out.print("Key is: "+mentry2.getKey() + " & Value is: ");
			          System.out.println(mentry2.getValue());
			       }
			}
			
		}
			
			//use location.setNorth, etc to set the attributes of the location, then load locations into a hashmap of locationName, Location then return that to LoadLocations
			//close the file
		in.close();
	}
}
