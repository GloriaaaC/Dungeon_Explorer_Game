package unsw.dungeon;

public class Portal extends Entity implements Visible {

	private int id;
	
	public Portal(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}
	
	/**
	 * return the id of this portal
	 * @return
	 */
	public int getPortalID() {
		return this.id;
	}
	
	@Override
	public String getType() {
		return "portal";
	}
	
	@Override
	public boolean canMoveEnemy() {
		return false;
	}

	@Override
	public boolean canMovePlayer() {
		return true;
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
