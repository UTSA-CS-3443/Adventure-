package enemies;

import java.util.HashMap;
import objects.Enemy;
import javafx.scene.image.Image;

public class Bandit extends Enemy {
	
	private String name;
	private int hp;
	private HashMap<String, Integer> stats;
	private Image image = new Image("/images/placeHolderHighwayman.jpg");
	
	public Bandit() {
		super.setMoney(10);
		super.setHp(10);
		super.setStats("STR", 8);
		super.setStats("PER", 10);
		super.setStats("INT", 7);
		super.setStats("AGI", 15);
		super.setStats("LUC", 7);
		super.setImage(image);
	}

}
