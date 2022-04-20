package unsw.dungeon;

import java.util.ArrayList;

public class EnemyManager {
	ArrayList<Enemy> enemies;
	
	public EnemyManager() {
		this.enemies = new ArrayList<Enemy>();
	}
	
	/**
	 * add a new enemy into the enemy manager
	 * @param e
	 */
	public void addEnemy(Enemy e) {
		if (!enemies.contains(e)) {
			enemies.add(e);
		}
	}
	
	/**
	 * remove an enemy
	 * @param e
	 */
	public void removeEnemy(Enemy e) {
		enemies.remove(e);
	}
	
	/**
	 * enemy move to next step
	 */
	public void nextStep() {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				e.nextStep();
			}
		}
	}
	
	
	/**
	 * alter difficulty of all enemies
	 */
	public void setDifficulty(int dif) {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				e.setDifficulty(dif);
			}
		}
	}
	
	/**
	 * enemy move to next step
	 */
	public void allLeft() {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				e.moveLeft();
			}
		}
	}
	
	/**
	 * enemy move to next step
	 */
	public void allRight() {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				e.moveRight();
			}
		}
	}

	/**
	 * enemy move to next step
	 */
	public void allUp() {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				e.moveUp();
			}
		}
	}
	
	/**
	 * enemy move to next step
	 */
	public void allDown() {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				e.moveDown();
			}
		}
	}
}
