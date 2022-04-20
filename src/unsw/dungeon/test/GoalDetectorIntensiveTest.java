package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;
import org.junit.Test;

public class GoalDetectorIntensiveTest {
	
	// Extra Goal Detector Test
	
	// Test one layer composite goal (with and logic)
	@Test
	public void oneLayerCompositeAndLogicTest() {
		EnemyManager e = new EnemyManager();
		Goal g1 = new BasicGoal("treasure", new TreasureChecker());
		Goal g2 = new BasicGoal("exit", new ExitChecker());
		Goal g4 = new BasicGoal("enemies", new EnemyChecker());
		Goal g5 = new BasicGoal("boulders", new SwitchChecker());
		Goal g3 = new CompositeGoal("AND", new AndLogic());
		g3.addGoal(g1);
		g3.addGoal(g2);
		g3.addGoal(g4);
		g3.addGoal(g5);
		GoalDetector detector = new GoalDetector(g3);
		Dungeon d = new Dungeon(7,1,e,detector);
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(2,0,m);
		Exit exit = new Exit(6,0);
		MovementHandler h3 = new BoulderMovementHandler();
		MovementManager m3 = new MovementManager(d,h3);
		Boulder boulder = new Boulder(1,0,m3);
		MovementHandler h5 = new EnemyMovementHandler();
		MovementManager m5 = new MovementManager(d,h5);
		Enemy e5 = new Enemy(4,0,m5);
		Treasure treasure = new Treasure(5,0);
		InvinciblePotion potion = new InvinciblePotion(3,0,5);
		Switch sw = new Switch(0,0);
		
		d.addEntity(p);
		d.addEntity(e5);
		d.addEntity(potion);
		d.addEntity(boulder);
		d.addEntity(treasure);
		d.addEntity(exit);
		d.addEntity(sw);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(detector.isClear());
		e5.moveRight();
		p.moveLeft();
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertTrue(detector.isClear());
	}
	
	// Test one layer composite goal (with or logic)
	@Test
	public void oneLayerCompositeOrLogicTest() {
		EnemyManager e = new EnemyManager();
		Goal g1 = new BasicGoal("treasure", new TreasureChecker());
		Goal g2 = new BasicGoal("exit", new ExitChecker());
		Goal g4 = new BasicGoal("enemies", new EnemyChecker());
		Goal g5 = new BasicGoal("boulders", new SwitchChecker());
		Goal g3 = new CompositeGoal("Or", new OrLogic());
		g3.addGoal(g1);
		g3.addGoal(g2);
		g3.addGoal(g4);
		g3.addGoal(g5);
		GoalDetector detector = new GoalDetector(g3);
		Dungeon d = new Dungeon(7,1,e,detector);
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(2,0,m);
		Exit exit = new Exit(6,0);
		MovementHandler h3 = new BoulderMovementHandler();
		MovementManager m3 = new MovementManager(d,h3);
		Boulder boulder = new Boulder(1,0,m3);
		MovementHandler h5 = new EnemyMovementHandler();
		MovementManager m5 = new MovementManager(d,h5);
		Enemy e5 = new Enemy(4,0,m5);
		Treasure treasure = new Treasure(5,0);
		InvinciblePotion potion = new InvinciblePotion(3,0,5);
		Switch sw = new Switch(0,0);
		
		d.addEntity(p);
		d.addEntity(sw);
		d.addEntity(e5);
		d.addEntity(potion);
		d.addEntity(boulder);
		d.addEntity(treasure);
		d.addEntity(exit);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(detector.isClear());
		p.moveLeft();
		assertTrue(detector.isClear());
	}
	
	
	// Test multiple layers of composite goals with composite goals
	@Test
	public void multipleLayerCompositeWithCompositeTest() {
		EnemyManager e = new EnemyManager();
		Goal g1 = new BasicGoal("treasure", new TreasureChecker());
		Goal g2 = new BasicGoal("exit", new ExitChecker());
		Goal g4 = new BasicGoal("enemies", new EnemyChecker());
		Goal g5 = new BasicGoal("boulders", new SwitchChecker());
		Goal g3 = new CompositeGoal("AND", new AndLogic()); // First layer
		Goal g6 = new BasicGoal("treasure", new TreasureChecker());
		Goal g7 = new BasicGoal("exit", new ExitChecker());
		Goal g8 = new CompositeGoal("OR", new OrLogic()); // Second layer
		Goal g9 = new CompositeGoal("AND", new AndLogic()); // Second layer
		g9.addGoal(g1);
		g9.addGoal(g2);
		g9.addGoal(g5);
		g8.addGoal(g6);
		g8.addGoal(g7);
		g8.addGoal(g1);
		g8.addGoal(g2);
		g8.addGoal(g4);
		g3.addGoal(g8);
		g3.addGoal(g9);
		GoalDetector detector = new GoalDetector(g3);
		Dungeon d = new Dungeon(7,1,e,detector);
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(2,0,m);
		Exit exit = new Exit(6,0);
		MovementHandler h3 = new BoulderMovementHandler();
		MovementManager m3 = new MovementManager(d,h3);
		Boulder boulder = new Boulder(1,0,m3);
		MovementHandler h5 = new EnemyMovementHandler();
		MovementManager m5 = new MovementManager(d,h5);
		Enemy e5 = new Enemy(4,0,m5);
		Treasure treasure = new Treasure(5,0);
		InvinciblePotion potion = new InvinciblePotion(3,0,5);
		Switch sw = new Switch(0,0);
		
		d.addEntity(p);
		d.addEntity(sw);
		d.addEntity(e5);
		d.addEntity(potion);
		d.addEntity(boulder);
		d.addEntity(treasure);
		d.addEntity(exit);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(detector.isClear());
		e5.moveRight();
		p.moveLeft();
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertFalse(detector.isClear());
		p.moveRight();
		assertTrue("Should Win Now",detector.isClear());
	}
	
	// Test multiple layers of composite goals with basic goals
	@Test
	public void multipleLayerCompositeWithBasicTest() {
		EnemyManager e = new EnemyManager();
		Goal g1 = new BasicGoal("treasure", new TreasureChecker());
		Goal g5 = new BasicGoal("boulders", new SwitchChecker());
		Goal g3 = new CompositeGoal("AND", new AndLogic()); // First layer
		Goal g6 = new BasicGoal("treasure", new TreasureChecker());
		Goal g7 = new BasicGoal("exit", new ExitChecker());
		Goal g8 = new CompositeGoal("OR", new OrLogic()); // Second layer
		g8.addGoal(g6);
		g8.addGoal(g7);
		g8.addGoal(g1);
		g3.addGoal(g8);
		g3.addGoal(g5);
		GoalDetector detector = new GoalDetector(g3);
		Dungeon d = new Dungeon(7,1,e,detector);
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(2,0,m);
		Exit exit = new Exit(6,0);
		MovementHandler h3 = new BoulderMovementHandler();
		MovementManager m3 = new MovementManager(d,h3);
		Boulder boulder = new Boulder(1,0,m3);
		MovementHandler h5 = new EnemyMovementHandler();
		MovementManager m5 = new MovementManager(d,h5);
		Enemy e5 = new Enemy(5,0,m5);
		Treasure treasure = new Treasure(3,0);
		InvinciblePotion potion = new InvinciblePotion(4,0,5);
		Switch sw = new Switch(0,0);
		
		d.addEntity(p);
		d.addEntity(sw);
		d.addEntity(e5);
		d.addEntity(potion);
		d.addEntity(boulder);
		d.addEntity(treasure);
		d.addEntity(exit);
		d.setPlayer(p);
		d.initialise();
		
		assertFalse(detector.isClear());
		p.moveLeft();
		assertFalse(detector.isClear());
		p.moveRight();
		p.moveRight();
		p.moveRight();
		assertTrue("Should Win Now",detector.isClear());
	}
}
