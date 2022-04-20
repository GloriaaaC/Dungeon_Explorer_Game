package unsw.dungeon;

public interface Visitor {
	public void visit(Player p);
	public void visit(Enemy e);
	public void visit(Door d);
	public void visit(Sword s);
	public void visit(Treasure t);
	public void visit(InvinciblePotion i);
	public void visit(Key k);
	public void visit(Portal p);
}
