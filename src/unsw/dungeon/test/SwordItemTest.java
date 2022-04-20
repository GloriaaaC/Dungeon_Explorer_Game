package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class SwordItemTest {
    
    // Test User Story Collect sword

    // 2. User will auto collect the sword when moving to that square if no other sword is weared
    // 6. The wearability of the sword is 5 at the start
	@Test
	public void collectSwordWhenNoSwordShouldDisappeared() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(2,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Sword k = new Sword(1,0);
		d.addEntity(p);
		d.addEntity(k);
		d.setPlayer(p);
		
		assertFalse(p.hasSword());
		assertTrue(k.isVisible());
		assertTrue(k.isCollectable());
		p.moveRight();
		assertTrue(p.hasSword());
		assertFalse(k.isCollectable());
		assertFalse(k.isVisible());
		assertEquals(k.getWearability(),5);
	}
	
	// 3. User should be able to move through without collecting it if a sword is already weared
	// 4. User should not be able to change a new sword before the current one disappear
	@Test
	public void collectSwordWhenHasSwordShouldremained() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(3,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		Sword k1 = new Sword(1,0);
		Sword k2 = new Sword(2,0);
		d.addEntity(p);
		d.addEntity(k1);
		d.addEntity(k2);
		d.setPlayer(p);
		
		assertTrue(k2.isVisible());
		assertTrue(k2.isCollectable());
		p.moveRight();
		p.moveRight();
		assertTrue(p.hasSword());
		assertTrue(k2.isCollectable());
		assertTrue(k2.isVisible());
		assertEquals(p.getX(),2);
		assertEquals(p.getY(),0);
	}

}
