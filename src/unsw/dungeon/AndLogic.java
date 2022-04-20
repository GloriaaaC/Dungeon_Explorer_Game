package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class AndLogic implements LogicGate {
	
	@Override
	/**
	 * check whether the composite goal is achieved
	 */
	public boolean isAchieved(List<Goal> g, GoalDetector d) {
		for (Goal ele : g) {
			if (!ele.isAchieved(d)) {
				return false;
			}
		}
		return true;
	}

}
