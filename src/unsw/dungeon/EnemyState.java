package unsw.dungeon;

public abstract class EnemyState {
	
	protected Enemy e;
	protected EnemyAI a;
	

	public EnemyState(Enemy e) {
		this.e = e;
	}
	
	abstract void nextStep();
	abstract void changeAI();
}
