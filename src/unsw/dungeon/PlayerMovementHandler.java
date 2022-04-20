package unsw.dungeon;

import java.util.ArrayList;

public class PlayerMovementHandler implements MovementHandler{

	/**
	 * player can not move on a wall, a closed door, and need to check if the in front boulder can move or not
	 */
	public boolean ismovable(ArrayList<Entity> es, String s) {
		for(Entity e : es) {
			if(e instanceof Boulder) {
				Boulder b = (Boulder) e;
				if (s.equals("up")) {
					if (!b.moveUp()) {
						return false;
					}
				} else if (s.equals("down")) {
					if (!b.moveDown()) {
						return false;
					}
				} else if (s.equals("left")) {
					if (!b.moveLeft()) {
						return false;
					}
				} else if (s.equals("right")) {
					if (!b.moveRight()) {
						return false;
					}
				}
			} else {
				if (!e.canMovePlayer()) {
					return false;
				}
			}
		}
		return true;
	}
	
}
