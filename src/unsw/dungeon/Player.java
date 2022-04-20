package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovableEntity implements Visible{

    private Inventory inventory;
    private SimpleBooleanProperty isAlive;
    private SimpleBooleanProperty isInvincible;
    private SimpleBooleanProperty isUpdate;
    private int remainingTime;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(int x, int y, MovementManager movement) {
        super(x, y, movement);
        this.inventory = new Inventory();
        this.isAlive = new SimpleBooleanProperty(true);
        this.isInvincible = new SimpleBooleanProperty(false);
        this.remainingTime = 0;
        this.nextX = x;
        this.nextY = y;
        this.isUpdate = new SimpleBooleanProperty(false);
    }
    
    /**
     * get the id of owned key
     * @return
     */
    public int itemCount() {
    	return this.inventory.itemCount();
    }
    
    
    
    public SimpleIntegerProperty getWearability() {
		return inventory.getWearability();
	}
    /**
     * 
     * @return
     */
    public int getKeyID() {
    	return this.inventory.getKeyID();
    }

    /**
     * when player collects a item, add into its inventory
     * @param i
     */
    public void addItem(Item i) {
    	this.inventory.addItem(i);
    }
    
    /**
     * remove the used item
     * @param i
     */
    public void removeItem(Item i) {
    	this.inventory.removeItem(i);
    }
    
    /**
     * check whether player owns a usable sword
     * @return
     */
    public boolean hasSword() {
    	return this.inventory.hasSword();
    }
    
    /**
     * use sword to attack enemy
     */
    public void swordAttack() {
    	inventory.swordAttack();
    }
    
    public SimpleIntegerProperty getNumTreasure() {
    	return this.inventory.getNumTreasure();
    }
    
    public SimpleBooleanProperty hasKey() {
    	return this.inventory.hasKey();
    }
    
    /**
     * use key to open the paired door
     * @param i
     */
    public void usedKey(int i) {
    	this.inventory.usedKey(i);
    }
    
    /**
     * if player is killed by enemy, change to died
     */
    public void isKilled() {
    	this.isAlive.setValue(false);
    }
    
    /**
     * return whether the player is alive
     * @return
     */
    public boolean isAlive() {
    	return isAlive.getValue();
    }
    
    /**
     * return whether the player is alive
     * @return
     */
    public SimpleBooleanProperty alive() {
    	return this.isAlive;
    }
    
    /**
     * return whether the player is invincible
     * @return
     */
    public boolean isInvincible() {
    	return this.isInvincible.getValue();
    }
    
    /**
     * return whether the player is invincible
     * @return
     */
    public SimpleBooleanProperty invincible() {
    	return this.isInvincible;
    }
    
    /**
     * use the invincible potion
     */
    public void invincibleController() {
    	remainingTime = this.inventory.runPotion();
    	isInvincible.setValue(true);
    	isUpdate.setValue(true);
    	notifyObs();
    }
    
    /**
     * return whether the player is invincible
     * @return
     */
    public SimpleBooleanProperty IsUpdate() {
    	return this.isUpdate;
    }
    
    /**
     * set a timer to limit the invincible power
     */
    public void decrementor() {
    	if (remainingTime > 0) {
    		remainingTime-=1;
    		if (remainingTime <= 0) {
    			isInvincible.setValue(false);
    			notifyObs();
    		}
    	}
    }
    
    @Override
	public String getType() {
		return "player";
	}
    
    @Override
	public boolean canMoveEnemy() {
		return true;
	}

	@Override
	public boolean canMovePlayer() {
		return true;
	}

	@Override
	public boolean canMoveBoulder() {
		return true;
	}

	@Override
	public void accept(Visitor visitor) {
        visitor.visit(this);
    }

	@Override
	public void visit(Player p) {

	}

	@Override
	public void visit(Enemy e) {
		if (isAlive() && e.isAlive()) {
			if (isInvincible()) {
				e.getKilled();
			} else if (hasSword()) {
				swordAttack();
				e.getKilled();
			} else {
				isKilled();
			}
		}	
	}

	@Override
	public void visit(Door d) {
		if (!d.isOpen()) {
			if (getKeyID() == d.getID()) {
				d.setState();
				usedKey(d.getID());
			}
		}		
	}

	@Override
	public void visit(Sword s) {
		if (s.isCollectable() && hasSword() == false) {
			addItem(s);
			s.hasbeenUsed();
		}
	}

	@Override
	public void visit(Treasure t) {
		if (t.isCollectable()) {
			addItem(t);
			t.hasbeenUsed();
		}	
	}

	@Override
	public void visit(InvinciblePotion i) {
		if (i.isCollectable()) {
			addItem(i);
			i.hasbeenUsed();
			invincibleController();
		}	
	}

	@Override
	public void visit(Key k) {
		if (k.isCollectable() && getKeyID() == -1) {
			addItem(k);
			k.hasbeenUsed();
		}
	}

	@Override
	public void visit(Portal p) {
		setX(p.getX());
		setY(p.getY());		
	}
	
}
