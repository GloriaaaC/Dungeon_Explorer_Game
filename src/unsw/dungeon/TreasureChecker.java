package unsw.dungeon;

public class TreasureChecker implements GoalChecker {

	@Override
	/**
	 * check whether all treasures have been collected
	 */
	public boolean isAchieved(GoalDetector d) {
		return d.clearTreasure();
	}

}
