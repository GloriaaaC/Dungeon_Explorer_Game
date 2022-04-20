package unsw.dungeon;

import java.util.ArrayList;

public interface MovementHandler {

	/**
	 * check whether a entity is movable
	 * @param es
	 * @param s
	 * @return
	 */
	public boolean ismovable(ArrayList<Entity> es, String s);

}
