package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;

public class GoalDetector implements Observer {
	private Goal goal;
	private SimpleBooleanProperty stageClear;
	private ArrayList<Switch> switches;
	private ArrayList<Enemy> enemies;
	private ArrayList<Treasure> treasures;
	private ArrayList<Exit> exits;
	
	public GoalDetector(Goal g) {
		super();
		this.goal = g;
		this.stageClear = new SimpleBooleanProperty();
		this.exits = new ArrayList<Exit>();
		this.treasures = new ArrayList<Treasure>();
		this.enemies = new ArrayList<Enemy>();
		this.switches = new ArrayList<Switch>();
	}
	
	/**
	 * check whether all goals have been achieved
	 * @return
	 */
	public boolean isClear() {
		return goal.isAchieved(this);
	}
	
	/**
	 * check whether player arrive exit
	 * @return
	 */
	public boolean arriveExit() {
		for (Exit e : exits) {
			if (e.hasPlayer()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check whether all enemies have been killed
	 * @return
	 */
	public boolean clearEnemy() {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * check whether all treasures have been collected
	 * @return
	 */
	public boolean clearTreasure() {
		for (Treasure t : treasures) {
			if (t.isCollectable()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * check whether all switches have been activated
	 * @return
	 */
	public boolean clearSwitch() {
		for (Switch t : switches) {
			if (!t.isactivated()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * add entity into the corresponding lists
	 * @param e
	 */
	public void addEntity(Entity e) {
		if (e instanceof Switch) {
            Switch s = (Switch) e;
			addSwitch(s);
		} else if (e instanceof Enemy) {
            Enemy s = (Enemy) e;
			addEnemy(s);
		} else if (e instanceof Exit) {
            Exit s = (Exit) e;
			addExit(s);
		} else if (e instanceof Treasure) {
            Treasure s = (Treasure) e;
			addTreasure(s);
		}
	}
	
	/**
	 * add treasure
	 * @param t
	 */
	private void addTreasure(Treasure t) {
	    if (!treasures.contains(t)) {
	        treasures.add(t);
	    }
	}

	/**
	 * add switch
	 * @param t
	 */
	private void addSwitch(Switch t) {
	    if (!switches.contains(t)) {
	        switches.add(t);
	    }
	}
	
	/**
	 * add enemy
	 * @param t
	 */
	private void addEnemy(Enemy t) {
	    if (!enemies.contains(t)) {
	        enemies.add(t);
	    }
	}
	
	/**
	 *  add exit
	 * @param t
	 */
	private void addExit(Exit t) {
	    if (!exits.contains(t)) {
	        exits.add(t);
	    }
	}
	
	/**
	 * get the state of the goal detector
	 * @return
	 */
	public boolean getStatus() {
		return stageClear.getValue();
	}
	
	/**
	 * get the state of the goal detector
	 * @return
	 */
	public SimpleBooleanProperty status() {
		return stageClear;
	}
	
	public String getGoal() {
		return goal.getGoal();
	}
	
	@Override
	/**
	 * when all goals achieved, update the state
	 */
	public void update(Object o) {
		if (isClear()) {
			stageClear.setValue(true);
		}
	}
	
}
