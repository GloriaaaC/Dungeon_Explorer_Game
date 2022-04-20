package unsw.dungeon;

public abstract class MovableEntity extends Entity implements Visitor{
	
	protected MovementManager movement;
	protected int nextX;
	protected int nextY;
	
	public MovableEntity(int x, int y, MovementManager movement) {
		super(x, y);
		this.movement = movement;
		this.nextX = x;
		this.nextY = y;
	}
	
	/**
	 * set the x coordinate
	 * @param x
	 */
	public void setX(int x) {
		this.x().set(x);
	}
	
	/**
	 * set the y coordinate
	 * @param y
	 */
	public void setY(int y) {
		this.y().set(y);
	}
	
	/**
	 * Move this movable entity up
	 * @return
	 */
    public boolean moveUp() {
    	if (movement.moveup(this)) {
    		notifyObs();
    		nextY = getY() - 2;
    		return true;
    	}
    	return false;
    }

	/**
	 * Move this movable entity down
	 * @return
	 */
    public boolean moveDown() {
    	if (movement.movedown(this)) {
    		notifyObs();
    		nextY = getY() + 2;
    		return true;
    	}
    	return false;
    }

	/**
	 * Move this movable entity left
	 * @return
	 */
    public boolean moveLeft() {
    	if (movement.moveleft(this)) {
    		notifyObs();
    		nextX = getX() - 2;
    		return true;
    	}
        return false;
    }

	/**
	 * Move this movable entity right
	 * @return
	 */
    public boolean moveRight() {
    	if (movement.moveright(this)) {
    		notifyObs();
    		nextX = getX() + 2;
    		return true;
    	}
    	return false;
    }
    /**
     * prediction X
     */
    public int getNextX() {
    	return nextX;
    }
    
    /**
     * prediction Y
     */
    public int getNextY() {
    	return nextY;
    }

}
