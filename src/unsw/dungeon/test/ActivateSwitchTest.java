package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class ActivateSwitchTest {
	
	// Test User Story Activate Triggers
	
	// 1. User should be able to activate a trigger when a boulder is pushed onto a floor switch.
	// 2. User should be able to inactivate a trigger when push a boulder off the floor switch.
	@Test
	public void BoulderCanActivateAndInactivateSwitch() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1,1,m);
		Boulder b = new Boulder(1, 2, n);
		Switch s = new Switch(1, 3);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(s);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(s.isactivated());
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		assertEquals(b.getX(), s.getX());
		assertEquals(b.getY(), s.getY());
		assertTrue(s.isactivated());
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 3);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 4);
		assertFalse(s.isactivated());

	}
	
	
	// 3. User should be able to move on a floor switch without any reaction.
	@Test
	public void PlayerCanMoveOnSwitch() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		Player p = new Player(1,1,m);
		Switch s = new Switch(1, 3);
		d.addEntity(p);
		d.addEntity(s);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		p.moveDown();
		assertEquals(p.getX(), s.getX());
		assertEquals(p.getY(), s.getY());
		assertFalse(s.isactivated());

	}
	
	// 4. When every floor switches have a boulder, achieve the goal.
	@Test
	public void ActivateAllSwitchToAchieveGoal(){
		EnemyManager e = new EnemyManager();
		Goal g = new BasicGoal("boulders", new SwitchChecker());
		GoalDetector detector = new GoalDetector(g);
		Dungeon d = new Dungeon(5,5,e,detector);
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1,1,m);
		Boulder b1 = new Boulder(1, 2, n);
		Boulder b2 = new Boulder(2, 2, n);
		Switch s1 = new Switch(1, 3);
		Switch s2 = new Switch(2, 3);
		d.addEntity(p);
		d.addEntity(b1);
		d.addEntity(b2);
		d.addEntity(s1);
		d.addEntity(s2);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(detector.isClear());
		p.moveDown();
		assertTrue(s1.isactivated());
		assertFalse(detector.isClear());
		
		p.moveUp();
		p.moveRight();
		p.moveDown();
		assertTrue(s2.isactivated());
		assertTrue(detector.isClear());
		
	}
	
	
	// 5. Only one boulder can at a top of the on-floor switch. (The capacity of a switch is one boulder).
	@Test
	public void OnlyOneBoulderOnSwitchEachTime() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1,1,m);
		Boulder b1 = new Boulder(1, 2, n);
		Boulder b2 = new Boulder(2, 3, n);
		Switch s = new Switch(1, 3);
		d.addEntity(p);
		d.addEntity(b1);
		d.addEntity(b2);
		d.addEntity(s);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(s.isactivated());
		p.moveDown();
		assertEquals(b1.getX(), s.getX());
		assertEquals(b1.getY(), s.getY());
		assertTrue(s.isactivated());
		
		p.moveRight();
		p.moveRight();
		p.moveDown();
		p.moveLeft();

		assertEquals(b2.getX(), 2);
		assertEquals(b2.getY(), 3);
		assertEquals(b1.getX(), s.getX());
		assertEquals(b1.getY(), s.getY());

	}
	
	
	// 6. User should be able to exit to achieved all goals when activated all triggers if reaching the exit is one of the goals as well.

	@Test
	public void CanExitWhenActivatedAllSwitches(){
		EnemyManager e = new EnemyManager();
		Goal g1 = new BasicGoal("boulders", new SwitchChecker());
		Goal g2 = new BasicGoal("exit", new ExitChecker());
		Goal g = new CompositeGoal("AND", new AndLogic());
		g.addGoal(g1);
		g.addGoal(g2);
		GoalDetector detector = new GoalDetector(g);
		Dungeon d = new Dungeon(5,5,e,detector);
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1,1,m);
		Boulder b1 = new Boulder(1, 2, n);
		Boulder b2 = new Boulder(2, 2, n);
		Switch s1 = new Switch(1, 3);
		Switch s2 = new Switch(2, 3);
		Exit t = new Exit(3, 2);
		d.addEntity(p);
		d.addEntity(b1);
		d.addEntity(b2);
		d.addEntity(s1);
		d.addEntity(s2);
		d.addEntity(t);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(detector.isClear());
		p.moveDown();
		assertTrue(s1.isactivated());
		assertFalse(detector.isClear());
		
		p.moveUp();
		p.moveRight();
		p.moveDown();
		assertTrue(s2.isactivated());
		assertFalse(detector.isClear());
		
		p.moveRight();
		assertEquals(p.getX(), t.getX());
		assertEquals(p.getY(), t.getY());
		assertTrue(detector.isClear());
		
	}
	
	//8. Boulders and switches are not distinct
	@Test
	public void BouldersAndSwitchesAreDistinct(){
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1,1,m);
		Boulder b1 = new Boulder(1, 2, n);
		Boulder b2 = new Boulder(2, 2, n);
		Switch s1 = new Switch(1, 3);
		Switch s2 = new Switch(2, 3);
		d.addEntity(p);
		d.addEntity(b1);
		d.addEntity(b2);
		d.addEntity(s1);
		d.addEntity(s2);
		d.setPlayer(p);
		d.initialise();
		
		
		p.moveDown();
		assertTrue(s1.isactivated());
		p.moveLeft();
		p.moveDown();
		p.moveRight();
		assertFalse(s1.isactivated());
		assertTrue(s2.isactivated());
		p.moveUp();
		p.moveUp();
		p.moveRight();
		p.moveRight();
		p.moveDown();
		p.moveLeft();
		p.moveUp();
		p.moveLeft();
		p.moveDown();
		assertTrue(s1.isactivated());
		
	}
	
	

}
