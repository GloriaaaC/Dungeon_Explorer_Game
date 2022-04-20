package unsw.dungeon;

public interface Observer {
	/**
	 * when the observed object changed, update itself
	 * @param o
	 */
	public void update(Object o);
}
