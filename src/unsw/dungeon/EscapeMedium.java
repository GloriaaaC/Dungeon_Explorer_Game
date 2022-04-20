package unsw.dungeon;

public class EscapeMedium implements EnemyAI {

	@Override
	/**
	 * this is a medium level escape algorithm, 
	 * enemy will get the coordinate of the player and try to move away from the invincible player.
	 */
	public void nextMove(Enemy e, int targetX, int targetY) {
		int x = e.getX();
		int y = e.getY();
		if (x > targetX) {
			if (y > targetY) {
				if (moveDown(e)) {
					e.moveDown();
				} else if (moveRight(e)) {
					e.moveRight();
				} else if (moveUp(e)) {
					e.moveUp();
				} else if (moveLeft(e)) {
					e.moveLeft();
				}
			} else if (y < targetY) {
				if (moveUp(e)) {
					e.moveUp();
				} else if (moveRight(e)) {
					e.moveRight();
				} else if (moveDown(e)) {
					e.moveDown();
				} else if (moveLeft(e)) {
					e.moveLeft();
				}
			}else if (y == targetY) {
				if (moveRight(e)) {
					e.moveRight();
				} else if (moveLeft(e)) {
					e.moveLeft();
				} else if (moveDown(e)) {
					e.moveDown();
				} else if (moveUp(e)) {
					e.moveUp();
				}
			}
		} else if (x < targetX) {
			if (y > targetY) {
				if (moveDown(e)) {
					e.moveDown();
				} else if (moveLeft(e)) {
					e.moveLeft();;
				} else if (moveUp(e)) {
					e.moveUp();
				} else if (moveRight(e)) {
					e.moveRight();
				}
			} else if (y < targetY) {
				if (moveUp(e)) {
					e.moveUp();
				} else if (moveLeft(e)) {
					e.moveLeft();
				} else if (moveDown(e)) {
					e.moveDown();
				} else if (moveRight(e)) {
					e.moveRight();
				}
			} else if (y == targetY) {
				if (moveLeft(e)) {
					e.moveLeft();
				} else if (moveRight(e)) {
					e.moveRight();
				} else if (moveDown(e)) {
					e.moveDown();
				} else if (moveUp(e)) {
					e.moveUp();
				}
			}
		} else if (x == targetX) {
			if (y > targetY) {
				if (moveDown(e)) {
					e.moveDown();
				} else if (moveUp(e)) {
					e.moveUp();;
				} else if (moveLeft(e)) {
					e.moveLeft();
				} else if (moveRight(e)) {
					e.moveRight();
				}
			} else if (y < targetY) {
				if (moveUp(e)) {
					e.moveUp();
				} else if (moveDown(e)) {
					e.moveDown();
				} else if (moveLeft(e)) {
					e.moveLeft();
				} else if (moveRight(e)) {
					e.moveRight();
				}
			} 
		}
	}
	
	public boolean moveUp(Enemy enemy) {
		int x = enemy.getX();
		int y = enemy.getY() - 1;
		if (enemy.isMovable(x, y)) {
			return true;
		}
		return false;
	}
	
	public boolean moveDown(Enemy enemy) {
		int x = enemy.getX();
		int y = enemy.getY() + 1;
		if (enemy.isMovable(x, y)) {
			return true;
		}
		return false;
	}
	
	public boolean moveRight(Enemy enemy) {
		int x = enemy.getX() + 1;
		int y = enemy.getY();
		if (enemy.isMovable(x, y)) {
			return true;
		}
		return false;
	}
	
	public boolean moveLeft(Enemy enemy) {
		int x = enemy.getX() - 1;
		int y = enemy.getY();
		if (enemy.isMovable(x, y)) {
			return true;
		}
		return false;
	}

}
