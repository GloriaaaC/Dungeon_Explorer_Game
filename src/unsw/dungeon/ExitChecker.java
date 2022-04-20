package unsw.dungeon;

public class ExitChecker implements GoalChecker {

	@Override
	/**
	 * check whether the exit goal is achieved
	 */
	public boolean isAchieved(GoalDetector d) {
		return d.arriveExit();
	}

}
