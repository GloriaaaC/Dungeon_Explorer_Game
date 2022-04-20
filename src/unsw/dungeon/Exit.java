package unsw.dungeon;

public class Exit extends Entity implements Observer {
	
	private boolean hasPlayer;
	
	public Exit(int x, int y) {
		super(x, y);
		hasPlayer = false;
	}

	public boolean hasPlayer() {
		return hasPlayer;
	}
	
	/**
	 * check whether the player is on this exit
	 * @param p
	 */
	public void checkPlayerLocation(Player p) {
		if (p.sameLocation(this)) {
			hasPlayer = true;
		} else {
			hasPlayer = false;
		}
		notifyObs();
	}
	
	@Override
	/**
	 * update when the observable player changed
	 */
	public void update(Object o) {
		if (o instanceof Player) {
			Player p = (Player) o;
			checkPlayerLocation(p);
		}
	}
	
	@Override
	public String getType() {
		return "exit";
	}

	@Override
	public boolean canMoveEnemy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMovePlayer() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canMoveBoulder() {
		// TODO Auto-generated method stub
		return false;
	}
}

