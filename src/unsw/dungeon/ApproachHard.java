package unsw.dungeon;

import java.util.ArrayList;
import java.util.Comparator;

public class ApproachHard implements EnemyAI {

	public void nextMove(Enemy enemy, int x, int y) {
    	ArrayList<Position> result =  searchRoad(enemy, x, y);
    	if (result.size() != 0) {
	    	Position point = result.get(result.size() - 1);
	    	if (point.getX() == enemy.getX() && point.getY() == enemy.getY() + 1) {
	    		enemy.moveDown();
	    	} else if (point.getX() == enemy.getX() && point.getY() == enemy.getY() - 1){
	    		enemy.moveUp();
	    	} else if (point.getX() == enemy.getX() + 1 && point.getY() == enemy.getY()){
	    		enemy.moveRight();
	    	} else if (point.getX() == enemy.getX() - 1 && point.getY() == enemy.getY()){
	    		enemy.moveLeft();
	    	} 
    	} else {
    		ApproachMedium a = new ApproachMedium();
    		a.nextMove(enemy, x, y);
    	}
    }
	
    public ArrayList<Position> searchRoad(Enemy enemy, int endX, int endY){
    	ArrayList<Position> r = new ArrayList<Position>();
    	if (enemy.getX() != endX || enemy.getY() != endY) {
	        ArrayList<Position> openList = new ArrayList<Position>();
	        ArrayList<Position> closeList = new ArrayList<Position>();
	        ArrayList<Position> resultList = new ArrayList<Position>();
	    	
	        int resultIndex = -1;
	        boolean isGetResult = false;
	        
	        openList.add(new Position(enemy.getX(), enemy.getY()));
	        
	        do {
	        	Position currentPoint = openList.get(0);
	
	        	closeList.add(currentPoint);
	        	openList.remove(currentPoint);
	        	
	        	ArrayList<Position> surroundPoints = Position.getSurroundPoints(currentPoint, enemy);
	        	for (Position pos : surroundPoints) {
	
		        	boolean isExistList = Position.has(closeList, pos.getX(), pos.getY());
		        	if (!isExistList) {
		        		int g = currentPoint.getG() + Position.extractG(currentPoint, pos);
	                    if (!Position.has(openList, pos.getX(), pos.getY())){
	                        pos.setH(Position.extractH(pos, endX, endY));
	                        pos.setG(g);
	                        pos.setF(pos.getG() + pos.getH());
	                        pos.setParent(currentPoint);
	                        openList.add(pos);
	                    } else {
	                    	int index = openList.indexOf(Position.getPoint(openList, pos.getX(), pos.getY()));
	                        if (g < openList.get(index).getG()){
	                            openList.get(index).setParent(currentPoint);
	                            openList.get(index).setG(g);
	                            openList.get(index).setF(g + openList.get(index).getH());
	                        }
	                    }
		        	}
	              
	                openList.sort(new Comparator<Position>() {
	                    public int compare(Position o1, Position o2) {
	                        return Integer.compare(o1.getF(), o2.getF());
	                    }
	                });
	        	}
	        	for (Position tmpPos : openList){
	                if (tmpPos.getX() == endX && tmpPos.getY() == endY){
	                    isGetResult = true;
	                    resultIndex = openList.indexOf(tmpPos);
	                }
	            }   
	        	if (openList.size() < 1) {
	        		break;
	        	}
	        } while (!isGetResult);
	        
	        if (resultIndex == -1) {
	            return r;
	        }else {
	            Position currentPos = openList.get(resultIndex);
	            while (currentPos.getX() != enemy.getX() || currentPos.getY() != enemy.getY()) {
	                resultList.add(currentPos);
	                currentPos = currentPos.getParent();
	            }
	        }

        return resultList;
    	}
    	return r;
    }
}
