package unsw.dungeon.test;


import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class ExitTest {
    
	// Test User Story Enter exit
	
	// 1. User should be able to enter the exit by moving the player to exit square
	@Test
	public void PlayerCanMoveOnExit() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		Player p = new Player(1,1,m);
		Exit x = new Exit(1, 3);
		d.addEntity(p);
		d.addEntity(x);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		p.moveDown();
		assertEquals(p.getX(), x.getX());
		assertEquals(p.getY(), x.getY());
		
	}
	
	// 2. If the user has completed required mission when entering the exit, 
	// a pop-up with words "You win" should be displayed to the user, and then transfer to start page
	@Test
	public void PlayerCompleteAllMissionThenExit() {
		EnemyManager e = new EnemyManager();
		Goal g1 = new BasicGoal("treasure", new TreasureChecker());
		Goal g2 = new BasicGoal("exit", new ExitChecker());
		Goal g3 = new CompositeGoal("AND", new AndLogic());
		g3.addGoal(g1);
		g3.addGoal(g2);
		GoalDetector detector = new GoalDetector(g3);
		Dungeon d = new Dungeon(5,5,e,detector);
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		Player p = new Player(1,1,m);
		Exit x = new Exit(1, 3);
		Treasure t1 = new Treasure(1,0);
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(x);
		d.addEntity(t1);
		d.initialise();
		
		assertFalse(detector.isClear());
		p.moveUp();
		assertFalse(t1.isCollectable());
		assertFalse(detector.isClear());
		p.moveDown();
		p.moveDown();
		p.moveDown();
		assertEquals(p.getX(), x.getX());
		assertEquals(p.getY(), x.getY());
		assertTrue(detector.isClear());
	}
	
	// 4. User should be able to move out from the exit if the user still have missions to accomplish
	@Test
	public void PlayerMoveOutIfHasOtherMission() {
		EnemyManager e = new EnemyManager();
		Goal g1 = new BasicGoal("treasure", new TreasureChecker());
		Goal g2 = new BasicGoal("exit", new ExitChecker());
		Goal g3 = new CompositeGoal("AND", new AndLogic());
		g3.addGoal(g1);
		g3.addGoal(g2);
		GoalDetector detector = new GoalDetector(g3);
		Dungeon d = new Dungeon(5,5,e,detector);
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		Player p = new Player(1,1,m);
		Exit x = new Exit(1, 3);
		Treasure t1 = new Treasure(1,0);
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(x);
		d.addEntity(t1);
		d.initialise();
		
		assertFalse(detector.isClear());
		p.moveDown();
		p.moveDown();
		assertEquals(p.getX(), x.getX());
		assertEquals(p.getY(), x.getY());
		assertFalse(detector.isClear());
		p.moveUp();
		p.moveUp();
		p.moveUp();
		assertFalse(t1.isCollectable());
		assertFalse(detector.isClear());
		p.moveDown();
		p.moveDown();
		p.moveDown();
		assertTrue(detector.isClear());
		
	}
	
}
