package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class CompositeGoal implements Goal {

	private String type;
	private LogicGate l;
    private List<Goal> goals;
	
	public CompositeGoal(String t, LogicGate l) {
		this.type = t;
		this.l = l;
        this.goals = new ArrayList<Goal>();
	}
	@Override
	/**
	 * add the goal into the composite goal class
	 */
	public void addGoal(Goal g) {
        if (!goals.contains(g)) {
            goals.add(g);
        }
	}

	/**
	 * check whether the goal is achieved
	 */
	@Override
	public boolean isAchieved(GoalDetector d) {
		return l.isAchieved(goals, d);
	}
	
	/**
	 * get the type of this class
	 */
	@Override
	public String getType() {
		return this.type;
	}
	
	@Override
	public String getGoal() {
		String s = "(";
		for (Goal g : goals) {
			s+= "(";
			s+= g.getGoal();
			s+= ")";
			s= s + "  " + getType() + "  ";
		}
		s = s.substring(0, s.length() - 4 - getType().length());
		s += ")\n";
		return s;
	}

}
