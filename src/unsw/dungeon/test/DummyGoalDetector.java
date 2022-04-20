package unsw.dungeon.test;

import unsw.dungeon.Goal;
import unsw.dungeon.GoalDetector;


// Prodece a dummy Goal Detector
public class DummyGoalDetector {
	
	static public GoalDetector produce() {
		DummyGoal g = new DummyGoal();
		GoalDetector d = new GoalDetector(g);
		return d;
	}

}


class DummyGoal implements Goal {

	public DummyGoal() {
		super();
	}

	@Override
	public void addGoal(Goal g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAchieved(GoalDetector d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return null;
	}

}