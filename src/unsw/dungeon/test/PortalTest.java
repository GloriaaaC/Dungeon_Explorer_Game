package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class PortalTest {
    
    // Test User Story Interact with portal

    // 1. User should be able to move to the portal and get transported to corresponding portal (paired portals have same ID number)
    // 3. User should be landed at the same square where the corresponding portal located
    // 4. User should be able to teleport back to the origin portal by stepping outside and moving in portal again 
	@Test
	public void PortalShouldTeleport() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(8,8,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Portal p1a = new Portal(1,1,0);
		Portal p1b = new Portal(7,7,0);
		d.addEntity(p);
		d.addEntity(p1a);
		d.addEntity(p1b);
		d.setPlayer(p);
		
		
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveRight();
		p.moveDown();
		assertEquals(p.getX(),7);
		assertEquals(p.getY(),7);
		p.moveLeft();
		assertEquals(p.getX(),6);
		assertEquals(p.getY(),7);
		p.moveRight();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),1);
		p.moveDown();
		p.moveUp();
		assertEquals(p.getX(),7);
		assertEquals(p.getY(),7);
		p.moveLeft();
		p.moveRight();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),1);
	}
	
	// 5. User can only be teleported to the corresponding pairwise portal
	@Test
	public void PortalIspaired() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(8,8,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Portal p1a = new Portal(1,1,0);
		Portal p1b = new Portal(7,7,0);
		Portal p2a = new Portal(1,3,1);
		Portal p2b = new Portal(7,5,1);
		Portal p3a = new Portal(1,5,2);
		Portal p3b = new Portal(7,3,2);
		d.addEntity(p);
		d.addEntity(p1a);
		d.addEntity(p1b);
		d.addEntity(p2a);
		d.addEntity(p2b);
		d.addEntity(p3a);
		d.addEntity(p3b);
		d.setPlayer(p);
		
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
		p.moveRight();
		p.moveDown();
		assertEquals(p.getX(),7);
		assertEquals(p.getY(),7);
		p.moveUp();
		p.moveUp();
		assertEquals(p.getX(),1);
		assertEquals(p.getY(),3);
		p.moveDown();
		p.moveDown();
		assertEquals(p.getX(),7);
		assertEquals(p.getY(),3);
	}

    // 2. User should able to remain his status after teleportation except for position (such as invincibility)
	@Test
	public void PortalRemainState() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(8,8,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Portal p1a = new Portal(1,1,0);
		Portal p1b = new Portal(7,7,0);
		d.addEntity(p);
		d.addEntity(p1a);
		d.addEntity(p1b);
		
		assertFalse(p.isInvincible());
		p.moveRight();
		p.moveDown();
		assertFalse(p.isInvincible());
		p.invincibleController();
		assertTrue(p.isInvincible());
		p.moveUp();
		p.moveDown();
		assertTrue(p.isInvincible());
	}
}
