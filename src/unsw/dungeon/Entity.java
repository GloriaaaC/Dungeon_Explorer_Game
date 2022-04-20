package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity implements Subject{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private ArrayList<Observer> observers;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.observers = new ArrayList<Observer>();
    }

    /**
     * get the coordinate of the entity
     * @return
     */
    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    
    /**
     * check whether another entity is on the same square of this one
     * @param e
     * @return
     */
    public boolean sameLocation(Entity e) {
        return e.getX() == getX() && e.getY() == getY();
    }

	@Override
	/**
	 * add observer
	 */
	public void addObs(Observer o) {
		if (!observers.contains(o)) {
			observers.add(o);
		}
	}

	@Override
	/**
	 * remove observer
	 */
	public void removeObs(Observer o) {
		observers.remove(o);
	}

	@Override
	/**
	 * notify observer
	 */
	public void notifyObs() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
	
	public abstract String getType();
	public abstract boolean canMoveEnemy();
	public abstract boolean canMovePlayer();
	public abstract boolean canMoveBoulder();
}
