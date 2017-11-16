package items;

import objects.Items;

public class Elixer implements Items {
	
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
