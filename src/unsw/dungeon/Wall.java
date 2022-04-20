package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }
    
    @Override
	public String getType() {
		return "wall";
	}
    
    @Override
	public boolean canMoveEnemy() {
		return false;
	}

	@Override
	public boolean canMovePlayer() {
		return false;
	}

	@Override
	public boolean canMoveBoulder() {
		return false;
	}
}
