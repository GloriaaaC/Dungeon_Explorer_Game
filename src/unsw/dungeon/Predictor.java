package unsw.dungeon;

	public class Predictor extends Enemy {
		
	private int nextX;
	private int nextY;
	
	public Predictor (int x, int y, MovementManager movement) {
		super(x, y, movement);
	}
		
	@Override
	/**
	 * update the information when the observable item changed
	*/
	public void update(Object o) {
		if (o instanceof Player) {
			Player p = (Player) o;
			playerX = p.getX();
			playerY = p.getY();
			nextX = p.getNextX();
			nextY = p.getNextY();
			if (p.isInvincible()) {
				state = new EscapeState(this);
			} else {
				state = new ApproachState(this);
			}
		}
	}

	@Override
	public int getPlayerX() {
		return nextX;
	}

	@Override
	public int getPlayerY() {
		// TODO Auto-generated method stub
		return nextY;
	}
}
