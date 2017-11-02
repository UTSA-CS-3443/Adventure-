package locations;

import java.net.URL;

public class LoadLocations {

	public LoadLocations(URL defaultFile) {
		ReadLocations readLoc = new ReadLocations(defaultFile);
	}

}
