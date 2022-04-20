package unsw.dungeon;

import java.util.List;

public interface LogicGate {
	/**
	 * check whether all goals have achieved
	 * @param goals
	 * @param d
	 * @return
	 */
	public boolean isAchieved(List<Goal> goals, GoalDetector d);
}
