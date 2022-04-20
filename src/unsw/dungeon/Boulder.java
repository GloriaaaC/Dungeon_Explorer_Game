package unsw.dungeon;

public class Boulder extends MovableEntity{
	
	public Boulder(int x, int y, MovementManager movement) {
		super(x, y, movement);
	}

	@Override
	public String getType() {
		return "boulder";
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
	public void visit(Player p) {
		
	}

	@Override
	public void visit(Enemy e) {

	}

	@Override
	public void visit(Door d) {
		
	}

	@Override
	public void visit(Sword s) {
		
	}

	@Override
	public void visit(Treasure t) {
		
	}

	@Override
	public void visit(InvinciblePotion i) {
		
	}

	@Override
	public void visit(Key k) {
		
	}

	@Override
	public void visit(Portal p) {
		
	}

}
