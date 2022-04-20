package unsw.dungeon.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ActivateSwitchTest.class,
        BoulderMovementTest.class, 
        EnemyCollisionTest.class,
        EnemyMovementTest.class,
        ExitTest.class,
        KeyTest.class,
        ExitTest.class,
        PlayerMovementTest.class,
        PortalTest.class,
        PotionTest.class,
        SwordItemTest.class,
        TreasureTest.class,
        GoalDetectorIntensiveTest.class})
   

public class AllTests {

}
