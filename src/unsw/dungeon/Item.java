package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

public abstract class Item extends Entity{
	
	private boolean collectable;
	private SimpleBooleanProperty visible;

	public Item(int x, int y) {
		super(x, y);
		this.collectable = true;
		this.visible = new SimpleBooleanProperty(true);
	}
	
	/**
	 * check whether the item is collectible
	 * @return
	 */
	public boolean isCollectable() {
		return collectable;
	}
	
	/**
	 * If the item has been collected, it should change to uncollectable and invisible;
	 */
	public void hasbeenUsed() {
		this.collectable = false;
		this.visible.setValue(false);
		notifyObs();
	}
	
	/**
	 * check whether the item is visible
	 * @return
	 */
	public boolean isVisible() {
		return visible.getValue();
	}
	
	/**
	 * check whether the item is visible
	 * @return
	 */
	public SimpleBooleanProperty visible() {
		return this.visible;
	}
	
}

