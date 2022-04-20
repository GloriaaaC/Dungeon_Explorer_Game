package unsw.dungeon;

public class Treasure extends Item implements Visible {
	
	public Treasure (int x, int y) {
		super(x, y);
	}
	
	@Override
	public String getType() {
		return "treasure";
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
