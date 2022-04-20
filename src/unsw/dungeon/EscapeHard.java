package unsw.dungeon;

import java.util.ArrayList;
import java.util.Comparator;

public class EscapeHard implements EnemyAI {
	
	private ApproachHard a;
	
	public EscapeHard() {
		super();
		this.a = new ApproachHard();
	}
    
    public void nextMove(Enemy enemy, int x, int y) {
    	Position p = searchTarget(enemy, x, y);
    	ArrayList<Position> array = this.a.searchRoad(enemy, p.getX(), p.getY());
    	Position result = array.get(array.size() - 1);
    	if (result.getX() == enemy.getX() && result.getY() == enemy.getY() + 1) {
    		enemy.moveDown();
    	} else if (result.getX() == enemy.getX() && result.getY() == enemy.getY() - 1){
    		enemy.moveUp();
    	} else if (result.getX() == enemy.getX() + 1 && result.getY() == enemy.getY()){
    		enemy.moveRight();
    	} else if (result.getX() == enemy.getX() - 1 && result.getY() == enemy.getY()){
    		enemy.moveLeft();
    	} else {
    		EscapeMedium e = new EscapeMedium();
    		e.nextMove(enemy, x, y);
    	}
    }
    
    private Position searchTarget(Enemy enemy, int endX, int endY){
    	Position n = new Position(endX, endY);
    	if (enemy.getX() != endX || enemy.getY() != endY) {
	        ArrayList<Position> openList = new ArrayList<>();
	        ArrayList<Position> closeList = new ArrayList<>();    
	
	        openList.add(new Position(enemy.getX(), enemy.getY()));
	        while (openList.size() > 0 ){
	        	Position currentPoint = openList.get(0);
	        	closeList.add(currentPoint);
	        	openList.remove(currentPoint);
	        	
	        	ArrayList<Position> surroundPoints = Position.getSurroundPoints(currentPoint, enemy);
	        	if (surroundPoints.size() > 0) {
		        	for (Position pos : surroundPoints) {
			        	boolean isExistList = Position.has(closeList, pos.getX(), pos.getY());
			        	if (!isExistList) {
			        		int g = currentPoint.getG() + Position.extractG(currentPoint, pos);
		                    if (!Position.has(openList, pos.getX(), pos.getY())){
		                        pos.setH(Position.extractH(pos, endX, endY));
		                        pos.setG(g);
		                        pos.setF(pos.getH());
		                        pos.setParent(currentPoint);
		                        openList.add(pos);
		                    } else {
		                        int index = openList.indexOf(Position.getPoint(openList, pos.getX(), pos.getY()));
		                        if (g < openList.get(index).getG()){
		                            openList.get(index).setParent(currentPoint);
		                            openList.get(index).setG(g);
		                            openList.get(index).setF(openList.get(index).getH());
		                        }
		                    }
			        	}
		                openList.sort(new Comparator<Position>() {
		                    public int compare(Position o1, Position o2) {
		                        return Integer.compare(o1.getF(), o2.getF());
		                    }
		                });
		        	}
	        	}
	        }
	        
	    	closeList.sort(new Comparator<Position>() {
	            public int compare(Position o1, Position o2) {
	                return Integer.compare(o1.getF(), o2.getF());
	            }
	        });
	    	
	    	Position next = closeList.get(closeList.size() - 1);
	        return next;
    	}
    	return n;
    }
 
    
}
