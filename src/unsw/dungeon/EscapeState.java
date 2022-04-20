package unsw.dungeon;

public class EscapeState extends EnemyState {
	
	public EscapeState(Enemy e) {
		super(e);
		changeAI();
	}

	
	@Override
	/**
	 * enemy move to next step
	 */
	public void nextStep() {
		a.nextMove(e, e.getPlayerX(), e.getPlayerY());
	}

	@Override
	/**
	 * get the level of the algorithm of the enemy movement
	 */
	public void changeAI() {
		if (e.getDifficulty() == 2) {
			this.a = new EscapeHard();
		} else if (e.getDifficulty() == 1) {
			this.a = new EscapeMedium();
		} else if (e.getDifficulty() >= 0) {
			this.a = new RandomMovement();
		}
	}

}
