package unsw.dungeon;

public interface Subject {
	/**
	 * add observer
	 * @param o
	 */
	public void addObs(Observer o);
	/**
	 * remove observer
	 * @param o
	 */
	public void removeObs(Observer o);
	/**
	 * notify observers
	 */
	public void notifyObs();
}
