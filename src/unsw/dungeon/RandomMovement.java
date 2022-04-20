package unsw.dungeon;

import java.util.Random;

public class RandomMovement implements EnemyAI {

	private Random r;
	
	/**
	 * initiallize the easy approach algorithm 
	 */
	public RandomMovement() {
		super();
		this.r = new Random();
	}

	@Override
	/**
	 * this is a easy approach algorithm, enemy move torward to player randomly
	 */
	public void nextMove(Enemy enemy, int x, int y) {
		int num = r.nextInt(3);
		if (num == 0) {
			enemy.moveUp();
		} else if (num == 1) {
			enemy.moveLeft();
		} else if (num == 2) {
			enemy.moveRight();
		} else if (num == 3) {
			enemy.moveDown();
		}
	}

}
