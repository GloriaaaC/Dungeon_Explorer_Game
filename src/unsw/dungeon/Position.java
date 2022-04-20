package unsw.dungeon;

import java.util.ArrayList;

public class Position {
	
	/**
	 * this class is used for A* search for enemy movement
	 */

        private int x;
        private int y;
        private int g;
        private int h;
        private int f;
        private Position parent;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
            this.g = 0;
            this.h = 0;
            this.f = 0;
        }

        /**
         * get the x coordinate
         * @return
         */
        public int getX() {
            return x;
        }

        /**
         * change x
         * @param x
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * get the y coordinate
         * @return
         */
        public int getY() {
            return y;
        }

        /**
         * change y
         * @param y
         */
        public void setY(int y) {
            this.y = y;
        }

        /**
         * get the cost from start point
         * @return
         */
        public int getG() {
            return g;
        }

        /**
         * set the g
         * @param g
         */
        public void setG(int g) {
            this.g = g;
        }

        /**
         * get the cost from current coordinate to target coordinate by Manhattan's algorithm
         * @return
         */
        public int getH() {
            return h;
        }

        /**
         * set the h
         * @param h
         */
        public void setH(int h) {
            this.h = h;
        }

        /**
         * get the total cost
         * @return
         */
        public int getF() {
            return f;
        }

        /**
         * set the total cost
         * @param f
         */
        public void setF(int f) {
            this.f = f;
        }

        /**
         * get the parent position
         * @return
         */
        public Position getParent() {
            return parent;
        }

        /**
         * set the parent position
         * @param parent
         */
        public void setParent(Position parent) {
            this.parent = parent;
        }
        
        static public ArrayList<Position> getSurroundPoints(Position currentPoint, Enemy enemy){
            int x = currentPoint.getX();
            int y = currentPoint.getY();

            ArrayList<Position> surroundPoints = new ArrayList<Position>();
            if (enemy.isMovable(x, y - 1)) {
            	surroundPoints.add(new Position(x , y - 1));
            }
            if (enemy.isMovable(x + 1, y)) {
            	surroundPoints.add(new Position(x + 1, y));
            }
            if (enemy.isMovable(x, y + 1)) {
            	surroundPoints.add(new Position(x, y + 1));
            }
            if (enemy.isMovable(x - 1, y)) {
            	surroundPoints.add(new Position(x - 1, y));
            }

            return surroundPoints;
        }
        

        public static int extractG(Position curr, Position next) {
        	int total = 0;
        	total += Math.abs(curr.getX() - next.getX());
        	total += Math.abs(curr.getY() - next.getY());
        	return total;
        }
        
        public static int extractH(Position next, int x, int y) {
        	int total = 0;
        	total += Math.abs(next.getX() - x);
        	total += Math.abs(next.getY() - y);
        	return total;
        }
        
        public static boolean has(ArrayList<Position> p, int x, int y) {
        	for (Position a : p) {
        		if (a.getX() == x && a.getY() == y) {
        			return true;
        		}
        	}
        	return false;
        }
        
        public static Position getPoint(ArrayList<Position> p, int x, int y) {
        	Position point = new Position(x, y);
        	for (Position a : p) {
        		if (a.getX() == x && a.getY() == y) {
        			point = a;
        		}
        	}
        	return point;
        }
        
		@Override
		/**
		 * print the x and y coordinates
		 */
		public String toString() {
			return "Position x=" + x + ", y=" + y;
		}
      
    
}
