package unsw.dungeon;

public class SwitchChecker implements GoalChecker {

	@Override
	/**
	 * check whether all switches have been activated
	 */
	public boolean isAchieved(GoalDetector d) {
		return d.clearSwitch();
	}

}
