package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;


public class Door extends Entity implements Visible {
	
	private int id;
	private SimpleBooleanProperty state;
	
	public Door(int x, int y, int id) {
		super(x, y);
		this.id = id;
		this.state = new SimpleBooleanProperty(false);
	}
	
	/**
	 * @return the distinct Id of this door
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Set the state of door, when door has been opened
	 */
	public void setState() {
		this.state.set(true);;
	}
	
	/**
	 * 
	 * @return the state of door
	 */
	public boolean isOpen() {
		return this.state.getValue();
	}
	
	public SimpleBooleanProperty open() {
		return this.state;
	}
	
	@Override
	public String getType() {
		return "door";
	}

	@Override
	public boolean canMoveEnemy() {
		return this.state.getValue();
	}

	@Override
	public boolean canMovePlayer() {
		return this.state.getValue();
	}

	@Override
	public boolean canMoveBoulder() {
		return false;
	}
	
	@Override
	public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
