package unsw.dungeon;

public class Sword extends Item implements Visible {

		private int wearability = 5;
		
		public Sword(int x, int y) {
			super(x, y);
		}
		
		/**
		 * every attack will -1 wearability
		 */
		public void attack() {
			this.wearability = wearability - 1;
		}
		
		/**
		 * return the wearability
		 * @return
		 */
		public int getWearability() {
			return this.wearability;
		}
		
		@Override
		public String getType() {
			return "sword";
		}
		
		@Override
		public boolean canMoveEnemy() {
			return true;
		}

		@Override
		public boolean canMovePlayer() {
			return true;
		}

		@Override
		public boolean canMoveBoulder() {
			return true;
		}
		
		@Override
		public void accept(Visitor visitor) {
	        visitor.visit(this);
	    }
}
