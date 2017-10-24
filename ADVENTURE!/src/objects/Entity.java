package objects;

/**
 * Framework for all game world entities
 * @author JASON
 *
 */
public interface Entity {

	public String getName();

	public void setName(String name);

	public int getHp();

	public void setHp(int hp);

	public int[] getStats();

	public void setStats(int[] stats);
}
