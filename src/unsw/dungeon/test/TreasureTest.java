package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class TreasureTest {
    
    // Test User Story Collecting treasure
    
    // 1. User should be able to collect the treasure by moving to the square contains a treasure
	@Test
	public void collectOneTreasure() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(2,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Treasure t = new Treasure(1,0);
		d.addEntity(p);
		d.addEntity(t);
		d.setPlayer(p);

		assertEquals(0,p.itemCount());
		assertTrue(t.isVisible());
		assertTrue(t.isCollectable());
		p.moveRight();
		assertEquals(1,p.itemCount());
		assertFalse(t.isVisible()); // mock treasure disappeared
		assertFalse(t.isCollectable());
	}
	
	// 4. User can have multiple treasures with him at the same time
	@Test
	public void collectMultipleTreasures() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Treasure t1 = new Treasure(1,0);
		Treasure t2 = new Treasure(2,0);
		Treasure t3 = new Treasure(3,0);
		d.addEntity(p);
		d.addEntity(t1);
		d.addEntity(t2);
		d.addEntity(t3);
		d.setPlayer(p);

		assertEquals(0,p.itemCount());
		assertTrue(t2.isVisible());
		assertTrue(t2.isCollectable());
		assertTrue(t3.isVisible());
		assertTrue(t3.isCollectable());
		p.moveRight();
		assertEquals(1,p.itemCount());
		assertFalse(t1.isVisible());
		assertFalse(t1.isCollectable());
		assertTrue(t2.isVisible());
		assertTrue(t2.isCollectable());
		assertTrue(t3.isVisible());
		assertTrue(t3.isCollectable());
		p.moveRight();
		assertEquals(2,p.itemCount());
		assertFalse(t2.isVisible());
		assertFalse(t2.isCollectable());
		assertTrue(t3.isVisible());
		assertTrue(t3.isCollectable());
		p.moveRight();
		assertEquals(3,p.itemCount());
		assertFalse(t2.isVisible());
		assertFalse(t2.isCollectable());
		assertFalse(t3.isVisible());
		assertFalse(t3.isCollectable());
	}
	
	// 6. After collecting all treasures, the color of the name of the mission should change to green
	@Test
	public void singleTreasureShoudTriggerGoalDetector() {
		EnemyManager e = new EnemyManager();
		Goal g = new BasicGoal("treasure", new TreasureChecker());
		GoalDetector detector = new GoalDetector(g);
		Dungeon d = new Dungeon(2,1,e,detector);
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Treasure t1 = new Treasure(1,0);
		d.addEntity(p);
		d.addEntity(t1);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(detector.isClear());
		p.moveRight();
		assertTrue(detector.isClear());
	}
	
	// 6. After collecting all treasures, the color of the name of the mission should change to green
	@Test
	public void multipleTreasureShoudTriggerGoalDetector() {
		EnemyManager e = new EnemyManager();
		Goal g = new BasicGoal("treasure", new TreasureChecker());
		GoalDetector detector = new GoalDetector(g);
		Dungeon d = new Dungeon(3,1,e,detector);
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Treasure t1 = new Treasure(1,0);
		Treasure t2 = new Treasure(2,0);
		d.addEntity(p);
		d.addEntity(t1);
		d.addEntity(t2);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertTrue(detector.isClear());
	}

}
