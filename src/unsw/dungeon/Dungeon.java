/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private ArrayList<Entity> entities;
    private Player player;
    private GoalDetector g;
    private EnemyManager e;

    public Dungeon(int width, int height,EnemyManager e, GoalDetector g) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.e = e;
        this.g = g;
    }

    /**
     * get the width of the dungeon
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * get the height of the dungeon
     * @return
     */
    public int getHeight() {
        return height;
    }

    /** 
     * get the player
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * change the player
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * change the goal detector
     * @param g
     * @return
     */
    public GoalDetector setGoalDetector(GoalDetector g) {
    	return this.g = g;
    }
    
    /**
     * get the goal detector
     * @return
     */
    public GoalDetector getGoalDetector() {
    	return g;
    }

    /**
     * add entity into this dungeon
     * @param entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        g.addEntity(entity);
        if (entity.getType().equals("enemy")) {
        	e.addEnemy((Enemy) entity);
        }
    }
    
    /**
     * get all entities in this dungeon
     * @return
     */
    public ArrayList<Entity> getAllEntities(){
    	return this.entities;
    }

    /**
     * initialize some entities in this dungeon
     */
	public void initialise() {
		iniExit();
		iniSwitch();
		iniEnemy();
	}
	
	/**
     * get all entities in this dungeon
     * @return
     */
    public void next(){
    	e.nextStep();
    }
    
    public void enemyLeft(){
    	e.allLeft();
    }
    
    public void enemyUp(){
    	e.allUp();
    }
    
    public void enemyRight(){
    	e.allRight();
    }
    
    public void enemyDown(){
    	e.allDown();
    }
    
    /**
     * get all entities in this dungeon
     * @return
     */
    public SimpleBooleanProperty status(){
    	return g.status();
    }
	
	/**
	 * get all floor switches in the dungeon
	 * @return
	 */
	private ArrayList<Switch> allSwitches() {
		ArrayList<Switch> b = new ArrayList<Switch>();
		for (Entity e : entities) {
			if (e.getType().equals("switch")) {
				b.add((Switch) e);
			}
		}
		return b;
	}
	
	/**
	 * get all exit in this dungeon
	 * @return
	 */
	private ArrayList<Exit> allExits() {
		ArrayList<Exit> b = new ArrayList<Exit>();
		for (Entity e : entities) {
			if (e.getType().equals("exit")) {
				b.add((Exit) e);
			}
		}
		return b;
	}
	
	/**
	 * get all boulders in this dungeon
	 * @return
	 */
	private ArrayList<Boulder> allBoulders() {
		ArrayList<Boulder> b = new ArrayList<Boulder>();
		for (Entity e : entities) {
			if (e.getType().equals("boulder")) {
				b.add((Boulder) e);
			}
		}
		return b;
	}
	
	/**
	 * initialize exits, exit will observe player
	 */
	private void iniExit() {
		ArrayList<Exit> exits = allExits();
		for (Exit ele : exits) {
			player.addObs(ele);
		}
	}
	
	/**
	 * initialize switches, floor switches will observe all boulders
	 */
	private void iniSwitch() {
		ArrayList<Switch> switches = allSwitches();
		ArrayList<Boulder> boulders = allBoulders();
		for (Boulder e : boulders) {
			for (Switch ele : switches) {
				e.addObs(ele);
			}
		}
		for (Switch s : switches) {
			for (Boulder b : boulders) {
				s.addBoulder(b);
			}
			s.update(this.player);
		}
	}
	
	/**
	 * initialize enemies, enemy will observe player 
	 */
	private void iniEnemy() {
		ArrayList<Enemy> es = allEnemy();
		for (Enemy e : es) {
			player.addObs(e);
			player.notifyObs();
		}
	}
	
	/**
	 * get all enemies in this dungeon
	 * @return
	 */
	private ArrayList<Enemy> allEnemy() {
		ArrayList<Enemy> b = new ArrayList<Enemy>();
		for (Entity e : entities) {
			if (e.getType().equals("enemy")) {
				b.add((Enemy) e);
			}
		}
		return b;
	}
	
	/**
	 * move all enemies
	 */
	public void moveEnemy() {
		e.nextStep();
	}
	
	public void setDifficulty(int dif) {
		e.setDifficulty(dif);
	}
	
	/**
     * get the string desribes goals
     * @return
     */
    public String getGoal() {
        return g.getGoal();
    }
}
