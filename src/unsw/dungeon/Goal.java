package unsw.dungeon;

public interface Goal {
	/**
	 * goal interface
	 * @param g
	 */
	public void addGoal(Goal g);
	public boolean isAchieved(GoalDetector d);
	public String getType();
	public String getGoal();
}
