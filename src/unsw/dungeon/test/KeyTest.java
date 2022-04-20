package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class KeyTest {
    
    //Test User Story Collect key and User Story Open Door

    // 1. User should be able to collect the key when move to a square with key when the user doesn't have a key
	@Test
	public void collectKeyWhenNoKeyShouldDisappeared() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(2,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Key k = new Key(1,0,0);
		d.addEntity(p);
		d.addEntity(k);
		d.setPlayer(p);
		
		assertEquals(-1,p.getKeyID());
		assertTrue(k.isVisible());
		assertTrue(k.isCollectable());
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),0);
		assertEquals(0,p.getKeyID());
		assertFalse(k.isCollectable());
		assertFalse(k.isVisible());
	}
	
	// 3. User can carry only one key at the same time
	// 4. If user already have another key with them, user should be able to walk through the square contains key without collecting the key
	@Test
	public void collectKeyWhenHasKeyShouldremained() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(3,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Key k1 = new Key(1,0,0);
		Key k2 = new Key(2,0,1);
		d.addEntity(p);
		d.addEntity(k1);
		d.addEntity(k2);
		d.setPlayer(p);
		
		assertEquals(-1,p.getKeyID());
		assertTrue(k2.isVisible());
		assertTrue(k2.isCollectable());
		p.moveRight();
		p.moveRight();
		assertEquals(0,p.getKeyID());
		assertTrue(k2.isCollectable());
		assertTrue(k2.isVisible());
		assertEquals(p.getX(),2);
		assertEquals(p.getY(),0);
	}
	
	// Open Door : 1. User should be able to open the door by using a key, which has the same id with door
	@Test
	public void openDoorSameKeyIdKeyShouldDisappeared() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(3,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Key k1 = new Key(1,0,0);
		Door d1 = new Door(2,0,0);
		d.addEntity(p);
		d.addEntity(k1);
		d.addEntity(d1);
		d.setPlayer(p);
		
		p.moveRight();
		assertFalse(d1.isOpen());
		assertEquals(0,p.getKeyID());
		p.moveRight();
		assertTrue(d1.isOpen());
		assertEquals(-1,p.getKeyID());
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),2);
		assertEquals(p.getY(),0);
	}
	
	// Open Door: 3. User should not be able to open a door with a key of different ID
	@Test
	public void openDoorDifferentKeyIdKeyShouldRemained() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(3,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Key k1 = new Key(1,0,0);
		Door d1 = new Door(2,0,1);
		d.addEntity(p);
		d.addEntity(k1);
		d.addEntity(d1);
		d.setPlayer(p);
		
		p.moveRight();
		assertFalse(d1.isOpen());
		assertEquals(0,p.getKeyID());
		p.moveRight();
		assertFalse(d1.isOpen());
		assertEquals(0,p.getKeyID());
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),0);
	}
	
	
	// Open Door: 5. User should be able to walk through the door when the door is open
	@Test
	public void playCanWalkThroughOpenDoor() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(3,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Door d1 = new Door(1,0,1);
		d.addEntity(p);
		d.addEntity(d1);
		d.setPlayer(p);
		d1.setState();
		
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),2);
		assertEquals(p.getY(),0);
		p.moveLeft();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),0);
		p.moveLeft();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		
	}

}
