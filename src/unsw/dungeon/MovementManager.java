package unsw.dungeon;

import java.util.ArrayList;

public class MovementManager {

	private ArrayList<Entity> entities;
	private MovementHandler movement;
	private int dungeonHeight;
	private int dungeonWidth;
	
	public MovementManager(Dungeon dungeon, MovementHandler movement) {
		this.dungeonHeight = dungeon.getHeight();
		this.dungeonWidth = dungeon.getWidth();
		this.movement = movement;
		this.entities = dungeon.getAllEntities();
	}
	
	/**
	 * check whether this entity is able to move up and call reaction
	 * @param character
	 * @return
	 */
	public boolean moveup(MovableEntity character) {
		boolean state = false;
		ArrayList<Entity> up = current(character.getX(), character.getY() - 1);
		if (this.movement.ismovable(up, "up") && character.getY() > 0) {
			character.setY(character.getY() - 1);
			state = true;
		}
		visit(character,up);
		character.notifyObs();
		return state;
	}
	
	/**
	 * check whether this entity is able to move down and call reaction
	 * @param character
	 * @return
	 */
	public boolean movedown(MovableEntity character) {
		boolean state = false;
		ArrayList<Entity> down = current(character.getX(), character.getY() + 1);
		if (this.movement.ismovable(down, "down") && character.getY() < dungeonHeight - 1) {
			character.setY(character.getY() + 1);
			state = true;
		}
		visit(character,down);
		character.notifyObs();
		return state;
	}
	
	/**
	 * check whether this entity is able to move right and call reaction
	 * @param character
	 * @return
	 */
	public boolean moveright(MovableEntity character) {
		boolean state = false;
		ArrayList<Entity> right = current(character.getX() + 1, character.getY());
		if (this.movement.ismovable(right, "right") && character.getX() < dungeonWidth - 1) {
			character.setX(character.getX() + 1);
			state = true;
		}
		visit(character,right);
		character.notifyObs();
		return state;
	}
	
	/**
	 * check whether this entity is able to move left and call reaction
	 * @param character
	 * @return
	 */
	public boolean moveleft(MovableEntity character) {
		boolean state = false;
		ArrayList<Entity> left = current(character.getX() - 1, character.getY());
		if (this.movement.ismovable(left, "left") && character.getX() > 0) {
			character.setX(character.getX() - 1);
			state = true;
		}
		visit(character,left);
		character.notifyObs();
		return state;
	}
	
	/**
	 * get the other paired portal with the same id 
	 * @param p
	 * @return
	 */
	public Portal getPairPortal(Portal p) {
		for (Entity e : entities) {
			if (e instanceof Portal && ((Portal) e).getPortalID() == p.getPortalID() && e != p) {
				return (Portal)e;
			}
		}
		return null;
	}
	
	/**
	 * get all entities at the input x_coordinate and y_coordinate
	 * @param x
	 * @param y
	 * @return
	 */
	public ArrayList<Entity> current(int x, int y){
		ArrayList<Entity> current = new ArrayList<Entity>();
		for (Entity e : entities) {
			if (e.getX() == x && e.getY() == y) {
				current.add(e);
			}
		}
		return current;
	}
	
	/**
	 * check whether the next step is movable
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canMove(int x, int y) {
		ArrayList<Entity> c = current(x, y);
		if (this.movement.ismovable(c, "") && y <= dungeonHeight - 1 && y >= 0 && x >= 0 && x <= dungeonWidth -1) {
			return true;
		}
		return false;
		
	}
	
	private void visit(MovableEntity character,ArrayList<Entity> list) {
		for (Entity ele : list) {
			if (ele instanceof Visible) {
				if (ele instanceof Portal) {
					Portal pair = getPairPortal((Portal)ele);
					Visible e = (Visible) pair;
					e.accept(character);
				} else {
					Visible e = (Visible) ele;
					e.accept(character);
				}
			}
		}
	}
}
