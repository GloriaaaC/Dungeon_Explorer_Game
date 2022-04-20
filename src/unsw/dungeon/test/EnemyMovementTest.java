package unsw.dungeon.test;

import static org.junit.Assert.*;
import org.junit.Test;
import unsw.dungeon.*;

public class EnemyMovementTest {

    //Test User Story Movement of enemy

    // 3. Easy enemies move in a random direction    
	@Test
	public void easyEnemyMove() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(3,3,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		MovementHandler h2 = new EnemyMovementHandler();
		MovementManager m2 = new MovementManager(d,h2);
		Enemy e1 = new Enemy(1,1,m2);
		d.addEntity(p);
		d.addEntity(e1);
		d.setPlayer(p);
		e.setDifficulty(0); // Easy Enemy, 0 = Easy, 1 = Medium, 2 = Hard
		d.initialise();
		assertTrue(e1.getX() == 0 || e1.getX() == 1 || e1.getX() == 2);
		assertTrue(e1.getY() == 0 || e1.getY() == 1 || e1.getY() == 2);
		e.nextStep();
		assertTrue(e1.getX() == 0 || e1.getX() == 1 || e1.getX() == 2);
		assertTrue(e1.getY() == 0 || e1.getY() == 1 || e1.getY() == 2);
		e.nextStep();
		assertTrue(e1.getX() == 0 || e1.getX() == 1 || e1.getX() == 2);
		assertTrue(e1.getY() == 0 || e1.getY() == 1 || e1.getY() == 2);
		e.nextStep();
		assertTrue(e1.getX() == 0 || e1.getX() == 1 || e1.getX() == 2);
		assertTrue(e1.getY() == 0 || e1.getY() == 1 || e1.getY() == 2);
		e.nextStep();
		assertTrue(e1.getX() == 0 || e1.getX() == 1 || e1.getX() == 2);
		assertTrue(e1.getY() == 0 || e1.getY() == 1 || e1.getY() == 2);
		e.nextStep();
		assertTrue(e1.getX() == 0 || e1.getX() == 1 || e1.getX() == 2);
		assertTrue(e1.getY() == 0 || e1.getY() == 1 || e1.getY() == 2);
	}
	
	/* map:
	Door     Exit    Portal  Switch
	
	OpenDoor TestOne Sword   Potion
	
	Boulder  Enemy   Wall    Treasure
	*/
	// 5. All enemies should be blocked by a wall and closed-door like user
	// 7. All enemies should not be able to use a portal or move a boulder or enter exit (view them like walls)
	// 12. Enemy cant collide with other alived enemies
	@Test
	public void enemyShouldBeBlockedByEntities() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(4,3,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(0,0,m);
		MovementHandler h2 = new EnemyMovementHandler();
		MovementManager m2 = new MovementManager(d,h2);
		Enemy e1 = new Enemy(1,1,m2);
		Door door = new Door(0,0,0);
		Exit exit = new Exit(1,0);
		MovementHandler h3 = new BoulderMovementHandler();
		MovementManager m3 = new MovementManager(d,h3);
		Boulder boulder = new Boulder(0,2,m3);
		Portal portal = new Portal(2,0,0);
		Portal portal2 = new Portal(2,0,0);
		MovementHandler h5 = new EnemyMovementHandler();
		MovementManager m5 = new MovementManager(d,h5);
		Enemy e5 = new Enemy(1,2,m5);
		Wall wall = new Wall(2,2);
		Door openDoor = new Door(0,1,1);
		openDoor.setState();
		Treasure treasure = new Treasure(3,2);
		InvinciblePotion potion = new InvinciblePotion(3,1,5);
		Switch swi = new Switch(3,0);
		Sword sword = new Sword(2,1);
		
		d.addEntity(p);
		d.addEntity(e5);
		d.addEntity(e1);
		d.addEntity(portal);
		d.addEntity(portal2);
		d.addEntity(potion);
		d.addEntity(swi);
		d.addEntity(sword);
		d.addEntity(boulder);
		d.addEntity(openDoor);
		d.addEntity(door);
		d.addEntity(wall);
		d.addEntity(treasure);
		d.addEntity(exit);
		d.setPlayer(p);
		d.initialise();
		
		assertEquals(1,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveLeft();
		assertEquals(0,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveUp();
		assertEquals(0,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveDown();
		assertEquals(0,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveRight();
		e1.moveUp();
		assertEquals(1,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveDown();
		assertEquals(1,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveRight();
		assertEquals(2,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveUp();
		assertEquals(2,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveDown();
		assertEquals(2,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveRight();
		assertEquals(3,e1.getX());
		assertEquals(1,e1.getY());
		e1.moveUp();
		assertEquals(3,e1.getX());
		assertEquals(0,e1.getY());
		e1.moveDown();
		e1.moveDown();
		assertEquals(3,e1.getX());
		assertEquals(2,e1.getY());	
	}
	
	// Collect invincible potion 6. After a period of invincible effect, 
	// User can see that the enemies will try to approach the player again (Be mortal again)
	// 11. Medium and hard enemies will try to approach player again when the player is mortal
	@Test
	public void mediumEnemyApproachPlayer() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(7,2,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(3,1,m);
		MovementHandler h2 = new EnemyMovementHandler();
		MovementManager m2 = new MovementManager(d,h2);
		Enemy e1 = new Enemy(0,1,m2);
		MovementHandler h3 = new EnemyMovementHandler();
		MovementManager m3 = new MovementManager(d,h3);
		Enemy e2 = new Enemy(6,1,m3);
		Wall w1 = new Wall(2,1);
		Wall w2 = new Wall(4,1);
		d.addEntity(p);
		d.addEntity(e1);
		d.addEntity(e2);
		d.addEntity(w1);
		d.addEntity(w2);
		d.setPlayer(p);
		e.setDifficulty(1);
		d.initialise();
		
		assertEquals(e1.getX(),0);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),6);
		assertEquals(e2.getY(),1);
		e.nextStep();
		assertEquals(e1.getX(),1);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),5);
		assertEquals(e2.getY(),1);
		e.nextStep();
		assertEquals(e1.getX(),1);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),5);
		assertEquals(e2.getY(),1);
		e.nextStep();
		assertEquals(e1.getX(),1);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),5);
		assertEquals(e2.getY(),1);
	}
	
	// 11. Medium and hard enemies will try to approach player again when the player is mortal
	// 9. Hard Enemies should be able to manage a way out the wall if there is a path
	@Test
	public void hardEnemyApproachPlayer() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(7,2,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(3,1,m);
		MovementHandler h2 = new EnemyMovementHandler();
		MovementManager m2 = new MovementManager(d,h2);
		Enemy e1 = new Enemy(0,1,m2);
		MovementHandler h3 = new EnemyMovementHandler();
		MovementManager m3 = new MovementManager(d,h3);
		Enemy e2 = new Enemy(6,1,m3);
		Wall w1 = new Wall(2,1);
		Wall w2 = new Wall(4,1);
		d.addEntity(p);
		d.addEntity(e1);
		d.addEntity(e2);
		d.addEntity(w1);
		d.addEntity(w2);
		d.setPlayer(p);
		e.setDifficulty(2);
		d.initialise();
		
		assertEquals(e1.getX(),0);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),6);
		assertEquals(e2.getY(),1);
		e.nextStep();
		assertEquals(e1.getX(),1);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),5);
		assertEquals(e2.getY(),1);
		e.nextStep();
		assertEquals("Should Approach", e1.getX(),1);
		assertEquals(e1.getY(),0);
		assertEquals(e2.getX(),5);
		assertEquals(e2.getY(),0);
		e.nextStep();
		assertEquals(e1.getX(),2);
		assertEquals(e1.getY(),0);
		assertEquals(e2.getX(),4);
		assertEquals(e2.getY(),0);
		e.nextStep();
		assertEquals(e1.getX(),3);
		assertEquals(e1.getY(),0);
		assertEquals(e2.getX(),4);
		assertEquals(e2.getY(),0);
		e.nextStep();
		assertEquals(e1.getX(),3);
		assertEquals(e1.getY(),1);
	}
	
	
	// 10. Medium and hard enemies will try to run away from player when the player is invincible
	@Test
	public void mediumEnemyEscapeWhenInvisibleAndReapproach() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(7,2,e,DummyGoalDetector.produce());
		InvinciblePotion potion = new InvinciblePotion(3,0,5);
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(3,1,m);
		MovementHandler h2 = new EnemyMovementHandler();
		MovementManager m2 = new MovementManager(d,h2);
		Enemy e1 = new Enemy(2,1,m2);
		MovementHandler h3 = new EnemyMovementHandler();
		MovementManager m3 = new MovementManager(d,h3);
		Enemy e2 = new Enemy(4,1,m3);
		d.addEntity(p);
		d.addEntity(potion);
		d.addEntity(e1);
		d.addEntity(e2);
		d.setPlayer(p);
		e.setDifficulty(1);
		d.initialise();
		
		p.moveUp();
		p.moveDown(); // now invicible player
		assertEquals("Should get away",e1.getX(),2);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),4);
		assertEquals(e2.getY(),1);
		e.nextStep();
		assertEquals(e1.getX(),1);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),5);
		assertEquals(e2.getY(),1);
		e.nextStep();
		assertEquals(e1.getX(),0);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),6);
		assertEquals(e2.getY(),1);
		p.decrementor();
		p.decrementor();
		p.decrementor();
		p.decrementor();
		p.decrementor(); // now mortal
		e.nextStep();
		assertEquals(e1.getX(),1);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),5);
		assertEquals(e2.getY(),1);
		e.nextStep();
		assertEquals(e1.getX(),2);
		assertEquals(e1.getY(),1);
		assertEquals(e2.getX(),4);
		assertEquals(e2.getY(),1);
	}
	
	// 10. Medium and hard enemies will try to run away from player when the player is invincible
	// 9. Hard Enemies should be able to manage a way out the wall if there is a path
	@Test
	public void hardEnemyEscapeWhenInvisibleAndReapproach() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(7,2,e,DummyGoalDetector.produce());
		InvinciblePotion potion = new InvinciblePotion(3,0,5);
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(3,1,m);
		MovementHandler h2 = new EnemyMovementHandler();
		MovementManager m2 = new MovementManager(d,h2);
		Enemy e1 = new Enemy(2,1,m2);
		d.addEntity(p);
		d.addEntity(potion);
		d.addEntity(e1);
		d.setPlayer(p);
		e.setDifficulty(2);
		d.initialise();
		
		p.moveUp();
		p.moveDown(); // now invicible player
		p.moveRight();
		p.moveRight();
		assertEquals(e1.getX(),2);
		assertEquals(e1.getY(),1);
		e.nextStep();
		e.nextStep();
		e.nextStep();
		assertEquals(e1.getX(),0);
		assertEquals(e1.getY(),0);
		p.decrementor();
		p.decrementor();
		p.decrementor();
		p.decrementor();
		p.decrementor(); // now mortal
		e.nextStep();
		assertEquals(e1.getX(),1);
		assertEquals(e1.getY(),0);
		e.nextStep();
		assertEquals(e1.getX(),2);
		assertEquals(e1.getY(),0);
		e.nextStep();
		assertEquals(e1.getX(),3);
		assertEquals(e1.getY(),0);
	}
	
	// 8. Enemies that cant reach the player will still try to approach (e.g. get the square closest to the player)
	@Test
	public void hardEnemyNoWayApproach() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(8,8,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(7,7,m);
		MovementHandler h2 = new EnemyMovementHandler();
		MovementManager m2 = new MovementManager(d,h2);
		Enemy e1 = new Enemy(0,0,m2);
		Wall w1 = new Wall(3,3);
		Wall w2 = new Wall(2,3);
		Wall w3 = new Wall(1,3);
		Wall w4 = new Wall(0,3);
		Wall w5 = new Wall(3,3);
		Wall w6 = new Wall(3,2);
		Wall w7 = new Wall(3,1);
		Wall w8 = new Wall(3,0);
		d.addEntity(w1);
		d.addEntity(w2);
		d.addEntity(w3);
		d.addEntity(w4);
		d.addEntity(w5);
		d.addEntity(w6);
		d.addEntity(w7);
		d.addEntity(w8);
		d.addEntity(p);
		d.addEntity(e1);
		d.setPlayer(p);
		e.setDifficulty(2);
		d.initialise();

		assertEquals(e1.getX(),0);
		assertEquals(e1.getY(),0);
		e.nextStep();
		e.nextStep();
		e.nextStep();
		e.nextStep(); // now move to the closet square
		assertEquals("should come the closet square",e1.getX(),2);
		assertEquals(e1.getY(),2);
		e.nextStep();
		assertEquals(e1.getX(),2);
		assertEquals(e1.getY(),2);
		e.nextStep();
		assertEquals(e1.getX(),2);
		assertEquals("should stay at the closet square", e1.getY(),2); // stay at the closet square
	}
	
	// 8. Enemies that cant reach the player will still try to approach (e.g. get the square closest to the player)
	@Test
	public void mediumEnemyNoWayApproach() {
		EnemyManager e = new EnemyManager();
		Dungeon d = new Dungeon(8,8,e,DummyGoalDetector.produce());
		MovementHandler h = new PlayerMovementHandler();
		MovementManager m = new MovementManager(d,h);
		Player p = new Player(7,7,m);
		MovementHandler h2 = new EnemyMovementHandler();
		MovementManager m2 = new MovementManager(d,h2);
		Enemy e1 = new Enemy(0,0,m2);
		Wall w1 = new Wall(3,3);
		Wall w2 = new Wall(2,3);
		Wall w3 = new Wall(1,3);
		Wall w4 = new Wall(0,3);
		Wall w5 = new Wall(3,3);
		Wall w6 = new Wall(3,2);
		Wall w7 = new Wall(3,1);
		Wall w8 = new Wall(3,0);
		d.addEntity(w1);
		d.addEntity(w2);
		d.addEntity(w3);
		d.addEntity(w4);
		d.addEntity(w5);
		d.addEntity(w6);
		d.addEntity(w7);
		d.addEntity(w8);
		d.addEntity(p);
		d.addEntity(e1);
		d.setPlayer(p);
		e.setDifficulty(1);
		d.initialise();
	
		assertEquals(e1.getX(),0);
		assertEquals(e1.getY(),0);
		e.nextStep();
		e.nextStep();
		e.nextStep();
		e.nextStep();
		assertEquals("should come the closet square",e1.getX(),2);
		assertEquals("should come the closet square",e1.getY(),2);
		e.nextStep();
		assertEquals(e1.getX(),2);
		assertEquals(e1.getY(),2);
		e.nextStep();
		assertEquals("should stay at the closet square",e1.getX(),2);
		assertEquals("should stay at  the closet square",e1.getY(),2);
	}
}