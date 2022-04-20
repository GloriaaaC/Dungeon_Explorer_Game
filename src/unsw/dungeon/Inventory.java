package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inventory {
	
	private SimpleIntegerProperty swordWearability;
	private SimpleIntegerProperty numOfTreasure;
	private SimpleBooleanProperty hasKey;
	private ArrayList<Item> items;	
	
	public Inventory() {
		this.items = new ArrayList<Item>();
		this.swordWearability = new SimpleIntegerProperty(0);
		this.numOfTreasure = new SimpleIntegerProperty(0);
		this.hasKey = new SimpleBooleanProperty(false);
	}
	
	/**
	 * remove item from inventory when the item has been used
	 * @param i
	 */
	public void removeItem(Item i) {
		this.items.remove(i);
	}
	
	/**
	 * get the ID of the key, if there is a unused key in the inventory
	 * @return
	 */
	public int getKeyID() {
		for (Item i : items) {
			if (i instanceof Key) {
				return ((Key) i).getID();
			}
		}
		return -1;
	}
	
	/**
	 * insert the id of the door to search for the paired key, if there is a key, use that to open the door
	 * and remove the used key from the inventory
	 * @param i
	 */
	public void usedKey(int i) {
		ArrayList<Item> is = new ArrayList<Item>();
		for (Item m : items) {
			if (m instanceof Key) {
				if (((Key)m).getID() == i) {
					is.add(m);
					hasKey.setValue(false);
				}
			}
		}
		this.items.removeAll(is);
	}
	
	/**
	 * check whether player has a usable sword 
	 * @return
	 */
	public boolean hasSword() {
		ArrayList<Item> is = new ArrayList<Item>();
		for (Item i : items) {
			if (i instanceof Sword) {
				if (((Sword) i).getWearability() > 0) {
					return true;
				} else {
					is.add(i);
				}
			}
		}
		this.items.removeAll(is);
		return false;
	}
	
	/**
	 * @param i
	 * add item into player's inventory when player collected that
	 */
	public void addItem(Item i) {
		this.items.add(i);
		if (i instanceof Sword) {
			swordWearability.setValue(((Sword) i).getWearability());
		} else if (i instanceof Treasure) {
			numOfTreasure.setValue(numOfTreasure.getValue() + 1);
		} else if (i instanceof Key) {
			hasKey.setValue(true);
		}
	}
	
	/**
	 * if user has a usable sword, it will attack the enemy when has collision with an enemy
	 */
	public void swordAttack() {
		if(hasSword()) {
			for (Item i : items) {
				if (i instanceof Sword) {
					Sword s = (Sword) i;
					s.attack();
					swordWearability.setValue(((Sword) i).getWearability());
 				}
			}
		} else {
			swordWearability.setValue(0);
		}
	}
	
	public SimpleIntegerProperty getWearability() {
		return swordWearability;
	}
	
	public SimpleIntegerProperty getNumTreasure() {
		return numOfTreasure;
	}
	
	public SimpleBooleanProperty hasKey() {
		return hasKey;
	}
	
	public int itemCount() {
		return items.size();
	}
	/**
	 * calculate the total number of collected treasure
	 * @return
	 */
	public int getTreasureNo() {
		int num = 0;
		for (Item i : items) {
			if (i instanceof Treasure) {
				num++;
			}
		}
		return num;
	}
	
	/**
	 * drink the invincible potion
	 * @return
	 */
	public int runPotion() {
		int t = 0;
		ArrayList<Item> is = new ArrayList<Item>();
		for (Item i : items) {
			if (i instanceof InvinciblePotion) {
				t = ((InvinciblePotion) i).getTime();
				is.add(i);
			}
		}
		this.items.removeAll(is);
		return t;
	}


}
