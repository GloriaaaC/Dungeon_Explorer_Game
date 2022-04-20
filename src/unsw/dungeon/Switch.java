package unsw.dungeon;

import java.util.ArrayList;

public class Switch extends Entity implements Observer{

	private boolean activated;
	private ArrayList<Boulder> boulders;
	
	public Switch (int x, int y) {
		super(x, y);
		this.activated = false;
		this.boulders = new ArrayList<Boulder>(); 
	}
	
	/**
	 * check whether the switch is activated
	 * @return
	 */
	public boolean isactivated() {
		return activated;
	}
	
	/**
	 * change the state of activated
	 */
	public void activated() {
		this.activated = true;
		notifyObs();
	}
	
	/**
	 * change switch to inactivated
	 */
	public void inactivated() {
		this.activated = false;
		notifyObs();
	}

	/**
	 * add all boulders in the list
	 * @param b
	 */
	public void addBoulder(Boulder b) {
		if (!boulders.contains(b)) {
			boulders.add(b);
		}
	}
	
	/**
	 * remove a boulder from the list
	 * @param b
	 */
	public void removeBoulder(Boulder b) {
		boulders.remove(b);
	}
	

	@Override
	/**
	 * update when boulder has changed
	 */
	public void update(Object o) {
		for (Boulder b : boulders) {
			if (b.sameLocation(this)) {
				activated();
				return;
			}
		}
		inactivated();
		return;
	}
	
	@Override
	public String getType() {
		return "switch";
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
}
