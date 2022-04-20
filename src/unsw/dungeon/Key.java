package unsw.dungeon;

public class Key extends Item implements Visible{
	
	private int id;
		
	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}
	
	/**
	 * get the distinct id of the key
	 * @return
	 */
	public int getID() {
		return this.id;
	}
	
	@Override
	public String getType() {
		return "key";
	}
	
	@Override
	public boolean canMoveEnemy() {
		return true;
	}

	@Override
	public boolean canMovePlayer() {
		return true;
	}

	@Override
	public boolean canMoveBoulder() {
		return true;
	}
	
	@Override
	public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

