package unsw.dungeon;

public class ApproachState extends EnemyState {

	public ApproachState(Enemy e) {
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
			this.a = new ApproachHard();
		} else if (e.getDifficulty() == 1) {
			this.a = new ApproachMedium();
		} else if (e.getDifficulty() >= 0) {
			this.a = new RandomMovement();
		}
	}

}
