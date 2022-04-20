package unsw.dungeon;

public class InvinciblePotion extends Item implements Visible {

	private int time;
	
	public InvinciblePotion(int x, int y, int time) {
		super(x, y);
		this.time = time;
	}
	
	/**
	 * get the duration of the power
	 * @return
	 */
	public int getTime() {
		return this.time;
	}
	
	@Override
	public String getType() {
		return "potion";
	}
	
	@Override
	public boolean canMoveEnemy() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canMovePlayer() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canMoveBoulder() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
