package unsw.dungeon.test;

import static org.junit.Assert.*;
import unsw.dungeon.*;

import org.junit.Test;

public class EnemyCollisionTest {
    
    // Test User Story Collide with enemies

    // 3. User can be killed by collision with an enemy if no sword or potion retrieved
	@Test
	public void PlayerWillDieIfNoSwordNoPotion() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementHandler enemyM = new EnemyMovementHandler();
		MovementManager n = new MovementManager(d,enemyM);
		Player p = new Player(1,1,m);
		Enemy y = new Enemy(1, 2, n);
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(y);
		d.initialise();
		
		p.moveDown();
		assertEquals(p.getX(), y.getX());
		assertEquals(p.getY(), y.getY());
		assertFalse(p.isAlive());
		assertTrue(y.isAlive());
	}
	
	// 4. If a sword is worn or the player is invincible, the enemy will be killed and disappear after collision
	@Test
	public void EnemyWillDieIfPlayerHasSword() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementHandler enemyM = new EnemyMovementHandler();
		MovementManager n = new MovementManager(d,enemyM);
		Player p = new Player(1,1,m);
		Enemy y = new Enemy(1, 3, n);
		Sword s = new Sword(1, 2);
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(y);
		d.addEntity(s);
		d.initialise();
		
		p.moveDown();
		assertTrue(p.hasSword());
		assertTrue(p.isAlive());
		assertTrue(y.isAlive());
		
		p.moveDown();
		assertTrue(p.hasSword());
		assertTrue(p.isAlive());
		assertFalse(y.isAlive());
		assertEquals(s.getWearability(), 4);
	}
	
	// 4. If a sword is worn or the player is invincible, the enemy will be killed and disappear after collision
	@Test
	public void EnemyWillDieIfPlayerInvincible() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementHandler enemyM = new EnemyMovementHandler();
		MovementManager n = new MovementManager(d,enemyM);
		Player p = new Player(1,1,m);
		Enemy y = new Enemy(1, 3, n);
		InvinciblePotion s = new InvinciblePotion(1, 2, 10);
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(y);
		d.addEntity(s);
		d.initialise();
		
		p.moveDown();
		assertTrue(p.isInvincible());
		p.moveDown();
		assertTrue(p.isAlive());
		assertFalse(y.isAlive());
	}
	
	// 6. If Player both wearing a sword and invincible, the wearability of sword will not decrease after collision
	@Test
	public void SwordWearabilityNotDecreaseWhenInvincible() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementHandler enemyM = new EnemyMovementHandler();
		MovementManager n = new MovementManager(d,enemyM);
		Player p = new Player(1,1,m);
		Enemy y = new Enemy(1, 4, n);
		InvinciblePotion s = new InvinciblePotion(1, 2, 10);
		Sword o = new Sword(1, 3);
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(y);
		d.addEntity(s);
		d.addEntity(o);
		d.initialise();
		
		p.moveDown();
		assertTrue(p.isInvincible());
		p.moveDown();
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 5);
		p.moveDown();
		assertTrue(p.isAlive());
		assertFalse(y.isAlive());
		assertEquals(o.getWearability(), 5);
	}
	
	// 5. If a sword is worn, the wearability of the sword is decreased by one after collision (zero to disappear)
	@Test
	public void SwordWillDisappearWhenAttackedFiveTimes() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(5,5,e,DummyGoalDetector.produce());
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementHandler enemyM = new EnemyMovementHandler();
		MovementManager n = new MovementManager(d,enemyM);
		Player p = new Player(1,1,m);
		Enemy y1 = new Enemy(1, 3, n);
		Enemy y2 = new Enemy(2, 3, n);
		Enemy y3 = new Enemy(3, 3, n);
		Enemy y4 = new Enemy(4, 3, n);
		Enemy y5 = new Enemy(4, 4, n);
		Enemy y6 = new Enemy(4, 5, n);
		Sword o = new Sword(1, 2);
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(y1);
		d.addEntity(y2);
		d.addEntity(y3);
		d.addEntity(y4);
		d.addEntity(y5);
		d.addEntity(y6);
		d.addEntity(o);
		d.initialise();
		
		p.moveDown();
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 5);
		p.moveDown();
		assertTrue(p.isAlive());
		assertFalse(y1.isAlive());
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 4);
		p.moveRight();
		assertTrue(p.isAlive());
		assertFalse(y2.isAlive());
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 3);
		p.moveRight();
		assertTrue(p.isAlive());
		assertFalse(y3.isAlive());
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 2);
		p.moveRight();
		assertTrue(p.isAlive());
		assertFalse(y4.isAlive());
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 1);
		p.moveDown();
		assertTrue(p.isAlive());
		assertFalse(y5.isAlive());
		assertFalse(p.hasSword());
		assertEquals(o.getWearability(), 0);
		p.moveDown();
		assertTrue(y6.isAlive());
		assertFalse(p.isAlive());

	}
	
	// 8. After killing all enemies, the color of the name of the mission should change to green
	@Test
	public void KilledAllEnemyAchieveGoal() {
		EnemyManager e = new EnemyManager();
		Goal g1 = new BasicGoal("enemies", new EnemyChecker());
		GoalDetector detector = new GoalDetector(g1);
		Dungeon d = new Dungeon(5,5,e,detector);
		MovementHandler playerM = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,playerM);
		MovementHandler enemyM = new EnemyMovementHandler();
		MovementManager n = new MovementManager(d,enemyM);
		Player p = new Player(1,1,m);
		Enemy y1 = new Enemy(1, 3, n);
		Enemy y2 = new Enemy(2, 3, n);
		Enemy y3 = new Enemy(3, 3, n);
		Sword o = new Sword(1, 2);
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(y1);
		d.addEntity(y2);
		d.addEntity(y3);
		d.addEntity(o);
		d.initialise();
		
		p.moveDown();
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 5);
		assertFalse(detector.isClear());
		
		p.moveDown();
		assertTrue(p.isAlive());
		assertFalse(y1.isAlive());
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 4);
		assertFalse(detector.isClear());
		
		p.moveRight();
		assertTrue(p.isAlive());
		assertFalse(y2.isAlive());
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 3);
		assertFalse(detector.isClear());
		
		p.moveRight();
		assertTrue(p.isAlive());
		assertFalse(y3.isAlive());
		assertTrue(p.hasSword());
		assertEquals(o.getWearability(), 2);
		assertTrue(detector.isClear());

	}

}
