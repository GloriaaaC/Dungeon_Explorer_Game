package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

public class Enemy extends MovableEntity implements Observer, Visible {

	protected int playerX;
	protected int playerY;
	private int difficulty;
	private SimpleBooleanProperty isAlive;
	protected EnemyState state;
	
	public Enemy (int x, int y, MovementManager movement) {
		super(x, y, movement);
		difficulty = 2;
		isAlive = new SimpleBooleanProperty(true);
		state = new ApproachState(this);
		playerX = 0;
		playerY = 0;
	}
	
	/**
	 * Set the state of Enemy when the enemy has been killed
	 */
	public void getKilled() {
		isAlive.setValue(false);
		notifyObs();
	}
	
	/**
	 * 
	 * @return whether the enemy is alive
	 */
	public boolean isAlive() {
		return isAlive.getValue();
	}
	
	/**
	 * 
	 * @return whether the enemy is alive
	 */
	public SimpleBooleanProperty alive() {
		return isAlive;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return input a position's location to check whether enemy can move to that position
	 */
	public boolean isMovable(int x, int y) {
		return super.movement.canMove(x, y);
	}
	
	/**
	 * get the next step of enemy
	 */
	public void nextStep() {
		this.state.nextStep();
	}
	
	/**
	 * set the different level of enemy movement -> 2 is the hardest and 0 is the easiest
	 * @param dif
	 */
	public void setDifficulty(int dif) {
		if (dif > 2) {
			difficulty = 2;
		} else if (dif < 0) {
			difficulty = 0;
		} else {
			difficulty = dif;
		}
		state.changeAI();
	}
	
	/**
	 * get the level of difficulty
	 * @return
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/**
	 * get the x_coordinate of player
	 * @return
	 */
	public int getPlayerX() {
		return playerX;
	}
	
	/**
	 * get the y_coordinate of player
	 * @return
	 */
	public int getPlayerY() {
		return playerY;
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
			if (p.isInvincible()) {
				state = new EscapeState(this);
			} else {
				state = new ApproachState(this);
			}
		}
	}
	
	@Override
	public String getType() {
		return "enemy";
	}

	@Override
	public boolean canMoveEnemy() {
		return !isAlive.getValue();
	}

	@Override
	public boolean canMovePlayer() {
		return true;
	}

	@Override
	public boolean canMoveBoulder() {
		return !isAlive.getValue();
	}
	
	@Override
	public void accept(Visitor visitor) {
        visitor.visit(this);
    }

	@Override
	public void visit(Player p) {
		if (p.isAlive() && isAlive()) {
			if (p.isInvincible()) {
				getKilled();
			} else if (p.hasSword()) {
				p.swordAttack();
				getKilled();
			} else {
				p.isKilled();
			}
		}	
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
