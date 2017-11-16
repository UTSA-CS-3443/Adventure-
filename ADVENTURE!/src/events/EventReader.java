package events;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class EventReader	{

	public HashMap<String, Event> eventsM = new HashMap<>();
	
	private Scanner in;
	private String eventName;
	private String eventDesc;
	private String[] identifier;
	private Event event;
	
	
	public EventReader(URL events) {
		
		in = null;
		eventName = "";
		eventDesc = "";
		identifier = new String[3];
		event = null;
		
		try {
			in = new Scanner(new File(events.toURI()));
		} catch(FileNotFoundException exception){
			System.err.println("failed to open event data");
		    System.exit(1);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		while (in.hasNext())
		{
			String line = in.nextLine();
			identifier = line.split(">");
			
			if(identifier[0].equals("EVENT"))
			{
				event = new Event();
				event.setName(identifier[1]);
			}
			else if(identifier[0].equals("DESC"))
			{
				event.setDesc(identifier[1]);
			}
			else if(identifier[0].equals("CHOICE"))
			{
				event.setChoices(identifier[1], Integer.parseInt(identifier[2]));
			}
			else if(identifier[0].equals("ENDEVENT"))
			{
				eventsM.put(event.getName(), event);
			}
		}
	}

}
