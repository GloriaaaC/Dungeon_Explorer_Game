package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class PlayerMovementTest {
    
    // Test User Story Moving in the dungeon

	// 2. User should be able to move the player in front, back, left and right four directions. (One square each move)
	@Test
	public void userCanMove() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(3,3,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(1,1,m);
		d.addEntity(p);
		d.setPlayer(p);
		
		assertEquals("origin",p.getX(),1);
		assertEquals(p.getY(),1);
		p.moveUp();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),0);
		p.moveDown();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),1);
		p.moveLeft();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),1);
		p.moveRight();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),1);
	}
	
	
	// 3. User should be blocked by boundary
	@Test
	public void userBlockedByBoundary() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(1,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		d.addEntity(p);
		d.setPlayer(p);
		
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveUp();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveDown();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveLeft();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
	}
	
	// 4. User should be blocked by the wall or closed door
	@Test
	public void userBlockedByWall() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(2,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Wall w = new Wall(1,0);
		
		d.addEntity(w);
		d.addEntity(p);
		d.setPlayer(p);
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
	}
	
	// 4. User should be blocked by the wall or closed door
	@Test
	public void userBlockedByClosedDoor() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(2,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Door w = new Door(1,0,0);
		d.addEntity(w);
		d.addEntity(p);
		d.setPlayer(p);
		
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
	}
	
	// 5. User should not be blocked by treasure, potion, opened door, sword, switch, and exit
	@Test
	public void userGoThroughEntitires() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(8,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		InvinciblePotion i = new InvinciblePotion(1,0,5);
		Treasure t = new Treasure(2,0);
		Sword sw = new Sword(3,0);
		Switch s = new Switch(4,0);
		Door door = new Door(5,0,0);
		door.setState();
		Exit exi = new Exit(6,0);
		
		d.addEntity(exi);
		d.addEntity(p);
		d.addEntity(i);
		d.addEntity(t);
		d.addEntity(sw);
		d.addEntity(s);
		d.addEntity(door);
		d.setPlayer(p);
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),2);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),3);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),4);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),5);
		assertEquals(p.getY(),0);
		p.moveRight();
		assertEquals(p.getX(),6);
		assertEquals(p.getY(),0);
	}



}
