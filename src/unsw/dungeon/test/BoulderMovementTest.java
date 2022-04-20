package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class BoulderMovementTest {
    
    // Test User Story Moving Boulder
	
	
	// 1. User should be able to push a boulder one unit square forward at a time (pulling not possible).
	// 5. User can push a boulder onto a normal square or a floor switch.
	// 4. User can not be able to walk through a boulder
	@Test
	public void OneSquareMoveEachTimeAndCanMoveOnSwitch() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1,1,m);
		Boulder b = new Boulder(1, 2, n);
		Switch s = new Switch(2, 1);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(s);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 3);
		
		p.moveLeft();
		p.moveDown();
		p.moveRight();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 3);
		assertEquals(b.getX(), 2);
		assertEquals(b.getY(), 3);
		
		p.moveDown();
		p.moveRight();
		p.moveUp();
		assertEquals(p.getX(), 2);
		assertEquals(p.getY(), 3);
		assertEquals(b.getX(), 2);
		assertEquals(b.getY(), 2);
		
		p.moveUp();
		assertEquals(p.getX(), 2);
		assertEquals(p.getY(), 2);
		assertEquals(b.getX(), 2);
		assertEquals(b.getY(), 1);
	}
	
	// 2. User is only strong enough to push one boulder at a time.
	@Test
	public void CannotMoveTwoBoulderEachTime() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1,1,m);
		Boulder b = new Boulder(1, 2, n);
		Boulder b1 = new Boulder(1, 3, n);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(b1);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);
		assertEquals(b1.getX(), 1);
		assertEquals(b1.getY(), 3);
		
		p.moveLeft();
		p.moveDown();
		p.moveRight();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		assertEquals(b.getX(), 2);
		assertEquals(b.getY(), 2);
	}
	
	// 3. User can't push boulder through the wall, door, portal, lived enemy, and exit.
	@Test
	public void BoulderCannotThroughWall() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1, 1, m);
		Boulder b = new Boulder(1, 2, n);
		Wall w = new Wall(1, 3);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(w);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);
	}
	
	//3. User can't push boulder through the wall, door, portal, lived enemy, and exit.
	@Test
	public void BoulderCannotThroughDoor() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1, 1, m);
		Boulder b = new Boulder(1, 2, n);
		Door o = new Door(1, 3, 0);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(o);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);
		o.setState();
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);

	}
	
	// 3. User can't push boulder through the wall, door, portal, lived enemy, and exit.
	@Test
	public void BoulderCannotThroughPortal() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1, 1, m);
		Boulder b = new Boulder(1, 2, n);
		Portal a = new Portal(1, 3, 0);
		Portal k = new Portal(3, 3, 0);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(a);
		d.addEntity(k);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);
	}
	
	
	// 3. User can't push boulder through the wall, door, portal, lived enemy, and exit.
	@Test
	public void BoulderCannotThroughEnemy() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementHandler EnemyM = new EnemyMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		MovementManager l = new MovementManager(d,EnemyM);
		Player p = new Player(1, 1, m);
		Boulder b = new Boulder(1, 2, n);
		Enemy en = new Enemy(1, 3, l);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(en);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);
		assertTrue(en.isAlive());
	}
	
	// 3. User can't push boulder through the wall, door, portal, lived enemy, and exit.
	@Test
	public void BoulderCannotThroughExit() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1, 1, m);
		Boulder b = new Boulder(1, 2, n);
		Exit a = new Exit(1, 3);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(a);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);
	}
	
	// 6. User should not be able to move a boulder anymore when a boulder is pushed into a corner (With two sides connect with the wall). 
	@Test
	public void BoulderCannotWhenInCorner() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementHandler boulderM = new BoulderMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementManager n = new MovementManager(d,boulderM);
		Player p = new Player(1, 1, m);
		Boulder b = new Boulder(1, 2, n);
		Wall w = new Wall(1, 3);
		Wall w1 = new Wall(2, 2);
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(w);
		d.addEntity(w1);
		d.setPlayer(p);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);
		
		p.moveLeft();
		p.moveDown();
		p.moveRight();
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 2);
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 2);
	}
	
	
}
