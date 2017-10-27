package locations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

import java.util.ArrayList;
public class ReadLocations {

	
/*

NOTE: LOCATIONS MUST BE IN THIS FORMAT

LOC Start-Screen
DESC "description of the location"
UP village-a1
DOWN field-a3
LEFT WALL
RIGHT WALL
ATTR hasEnemies
ATTR hasEvent eventType
ATTR ...
...
ENDLOC
	 
	 */
	public void ReadLocations()
	{		
		String[] lineBuffer = new String[2048];
		String[] attr = new String[128];
		String name = "";
		String desc = "";
		String up = "";
		String down = "";
		String left = "";
		String right = "";
		String[] identifier;
		Scanner in = null;
		
		try 
		{
			in = new Scanner(new File("Locations.txt"));
		} 
		catch (FileNotFoundException exception) 
		{
			    System.err.println("failed to open location data");
			    System.exit(1);
		}
		while (in.hasNext()) 
		{
			//create a new location based on the read in data
			//we will manually read in the up down left right name and description based on regex patterns then we will use another
			//while loop to keep going as long as the next line contains ATTR at the start, then we put the stuff in
			//the location and then start again at the top
			String line = in.nextLine();
			identifier = line.split("\\");
			if(identifier[0].equals("\\"))
			{
				Location loc = new Location();
				loc.setLocName(identifier[1]);
				System.out.printf("%s, %s", identifier[0], identifier[1] );

				if(identifier[0].equals("DESC"))
				{
					loc.setLocDesc(identifier[1]);
					System.out.printf("%s, %s", identifier[0], identifier[1] );
				}
				
				if(identifier[0].equals("UP"))
				{
					loc.setUp(identifier[1]);
					System.out.printf("%s, %s", identifier[0], identifier[1] );
				}
			}
			
			//use location.setUp, etc to set the attributes of the location, then call load locations to put it into a hashmap as detailed in locationlayout.txt
		}
			//close the file
			in.close();
	}

}
