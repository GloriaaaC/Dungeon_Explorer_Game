package unsw.dungeon;

import java.util.ArrayList;

public class BoulderMovementHandler implements MovementHandler{
	
	/**
	 * Boulder can't move on the Wall, Door, Boulder, Portal, Enemy and Exit
	 */
	public boolean ismovable(ArrayList<Entity> es, String s) {
		for (Entity e : es) {
			if (!e.canMoveBoulder()) {
				return false;
			}
		}
		return true;
	}

}
