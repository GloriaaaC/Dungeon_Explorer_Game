package unsw.dungeon;

public interface EnemyAI {

	/**
	 * enemy move to next step
	 * @param enemy
	 * @param x
	 * @param y
	 */
	public void nextMove(Enemy enemy, int x, int y);

}
