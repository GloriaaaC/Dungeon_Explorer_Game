package unsw.dungeon;

public class EnemyChecker implements GoalChecker {

	@Override
	/**
	 * check whether all enemies died
	 */
	public boolean isAchieved(GoalDetector d) {
		return d.clearEnemy();
	}

}
