package unsw.dungeon;

import java.util.ArrayList;

public class EnemyMovementHandler implements MovementHandler{

	/**
	 * enemy can not move on wall, boulder, portal, enemy and exit
	 */
	public boolean ismovable(ArrayList<Entity> es, String s) {
		for (Entity e : es) {
			if (!e.canMoveEnemy()) {
				return false;
			}
		}
		return true;
	}

}
