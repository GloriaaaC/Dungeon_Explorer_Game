package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EnemyManager;
import unsw.dungeon.InvinciblePotion;
import unsw.dungeon.MovementHandler;
import unsw.dungeon.MovementManager;
import unsw.dungeon.Player;
import unsw.dungeon.PlayerMovementHandler;

public class PotionTest {
    
    // Test User Story Collect invincible poison

    // 2. User should be able to auto retrieve the potion when moving to that square
	@Test
	public void collectPotionShouldDisappeared() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(2,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		InvinciblePotion k = new InvinciblePotion(1,0,5);
		d.addEntity(p);
		d.addEntity(k);
		d.setPlayer(p);
		
		assertFalse(p.isInvincible());
		assertTrue(k.isVisible());
		assertTrue(k.isCollectable());
		p.moveRight();
		assertTrue(p.isInvincible());
		assertFalse(k.isCollectable());
		assertFalse(k.isVisible());
	}
	
	// 3. User should be able to see the remaining time of the invincible effect
	// 8. The potion will have a limit duration
	@Test
	public void playerBackToMortal() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(2,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		InvinciblePotion k = new InvinciblePotion(1,0,5);
		d.addEntity(p);
		d.addEntity(k);
		d.setPlayer(p);
		
		assertFalse(p.isInvincible());
		p.moveRight();
		assertTrue(p.isInvincible());
		p.decrementor(); // Represent one unit of time has passed
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertFalse(p.isInvincible());
		p.decrementor();
		assertFalse(p.isInvincible());
		p.decrementor();
		assertFalse(p.isInvincible());
	}
	
	// 5. User should be able to refresh the remaining time of effect if the user already has an invincible effect
	@Test
	public void refreshInvincibleEffect() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(3,1,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		InvinciblePotion k = new InvinciblePotion(1,0,5);
		InvinciblePotion k2 = new InvinciblePotion(2,0,5);
		d.addEntity(p);
		d.addEntity(k);
		d.addEntity(k2);
		d.setPlayer(p);
		
		assertFalse(p.isInvincible());
		p.moveRight();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		p.moveRight();
		assertFalse(k2.isVisible());
		assertFalse(k2.isCollectable());
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertTrue(p.isInvincible());
		p.decrementor();
		assertFalse(p.isInvincible());
		p.decrementor();
		assertFalse(p.isInvincible());
		p.decrementor();
		assertFalse(p.isInvincible());
	}

}
