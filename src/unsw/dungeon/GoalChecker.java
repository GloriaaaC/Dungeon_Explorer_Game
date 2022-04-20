package unsw.dungeon;

public interface GoalChecker {
	/**
	 * check whether the goal is achieved
	 * @param d
	 * @return
	 */
	public boolean isAchieved(GoalDetector d);
}
