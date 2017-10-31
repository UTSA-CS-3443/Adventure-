package locations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;
import java.util.HashMap;

import java.util.ArrayList;
public class ReadLocations {

	
/*

NOTE: LOCATIONS MUST BE IN THIS FORMAT
NOTE: ATTR would reference the idea that there exists an attribute interface that some events, etc. derive from
NOTE: there is an implication that attributes would be loaded into a submap of attributes of which each have their own sub-maps or other related information. allows easy expansion
LOC Start-Screen
DESC "description of the location"
NORTH village-a1
SOUTH field-a3
EAST WALL
WEST WALL
ATTR hasEnemies
ATTR hasEvent eventType test-condition
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
		String north = "";
		String south = "";
		String east = "";
		String west = "";
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
				
				if(identifier[0].equals("NORTH"))
				{
					loc.setNorth(identifier[1]);
					System.out.printf("%s, %s", identifier[0], identifier[1] );
				}
				
				if(identifier[0].equals("SOUTH"))
				{
					loc.setSouth(identifier[1]);
					System.out.printf("%s, %s", identifier[0], identifier[1] );
				}
				
				
				if(identifier[0].equals("EAST"))
				{
					loc.setEast(identifier[1]);
					System.out.printf("%s, %s", identifier[0], identifier[1] );
				}
				
				
				if(identifier[0].equals("WEST"))
				{
					loc.setWest(identifier[1]);
					System.out.printf("%s, %s", identifier[0], identifier[1] );
				}
				
				//for every attribute we have for the particular location, parse it
				while(in.next("ATTR.*+") != null)
				{
					
				}
				
				
				
				
				
				
			}
			
			//use location.setUp, etc to set the attributes of the location, then call load locations to put it into a hashmap as detailed in locationlayout.txt
		}
			//close the file
			in.close();
	}

}
