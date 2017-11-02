package locations;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.*;
import java.util.HashMap;
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
>>>>>>> branch 'master' of https://github.com/UTSA-CS-3443/Adventure-.git
...
<<<<<<< HEAD
ENDLOC
 
 */

public class ReadLocations {

	public ReadLocations(URL defaultFile)
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
		Location loc = null;
		
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
			//we will manually read in the up down left right name and description based on regex patterns then we will use another
			//while loop to keep going as long as the next line contains ATTR at the start, then we put the stuff in
			//the location and then start again at the top
			identifier = null;
			String line = in.nextLine();
			identifier = line.split(">");
			if(identifier[0].equals("LOC"))
			{
				loc = new Location();
				loc.setLocName(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}

			if(identifier[0].equals("DESC"))
			{
				loc.setLocDesc(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
			if(identifier[0].equals("NORTH"))
			{
				loc.setNorth(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
			if(identifier[0].equals("SOUTH"))
			{
				loc.setSouth(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
				
			if(identifier[0].equals("EAST"))
			{
				loc.setEast(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
				
			if(identifier[0].equals("WEST"))
			{
				loc.setWest(identifier[1]);
				System.out.printf("%s, %s\n", identifier[0], identifier[1] );
			}
				
			//for every attribute we have for the particular location, parse it
			if(identifier[0].equals("ATTR"))
			{
				System.out.printf("%s, %s\n", identifier[0], identifier[1]);
			}
				
		}
			
			//use location.setNorth, etc to set the attributes of the location, then load locations into a hashmap of locationName, Location then return that to LoadLocations
			//close the file
		in.close();
	}
}
