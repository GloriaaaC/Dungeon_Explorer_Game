package unsw.dungeon;

public class BasicGoal implements Goal {

	String type;
	GoalChecker l;
	
	public BasicGoal(String t, GoalChecker l) {
		this.type = t;
		this.l = l;
	}
	@Override
	/**
	 * add a goal into the basic goal class
	 */
	public void addGoal(Goal g) {
		this.type = g.getType();
	}

	@Override
	/**
	 * check whether the goal is achieved
	 */
	public boolean isAchieved(GoalDetector d) {
		return l.isAchieved(d);
	}
	@Override
	/**
	 * return the type of this class
	 */
	public String getType() {
		return this.type;
	}
	@Override
	public String getGoal() {
		return this.type;
	}

}
