package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Goal primaryGoal = GoalLoader(json.getJSONObject("goal-condition"));
        GoalDetector g = new GoalDetector(primaryGoal);
        EnemyManager e = new EnemyManager();
        
        Dungeon dungeon = new Dungeon(width, height,e,g);
        

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        dungeon.initialise();
        return dungeon;
    }

    private Goal GoalLoader(JSONObject j) {
    	String type = j.getString("goal");
        Goal g = null;
        if (type.equals("AND") || type.equals("OR")) {
            g = compositeGoalLoader(j);
        } else {
            g = basicGoalLoader(j);
        }
        return g;
    }

    private Goal compositeGoalLoader(JSONObject j){
    	String type = j.getString("goal");
        Goal g = null;
        switch (type) {
            case "AND":
                LogicGate l = new AndLogic();
                g = new CompositeGoal(type, l);
                break;
            case "OR":
                LogicGate b = new OrLogic();
                g = new CompositeGoal(type, b);
                break;
            default :
                break;
            }
        if (g == null) {
            System.out.println("Error in GoalDetector");
            return g;
        }

        JSONArray jsonSubGoals = j.getJSONArray("subgoals");

        for (int i = 0; i < jsonSubGoals.length(); i++) {
                Goal b = GoalLoader(jsonSubGoals.getJSONObject(i));
                g.addGoal(b);
        }

        return g;
    }

    private Goal basicGoalLoader(JSONObject j){
    	String type = j.getString("goal");
        Goal g = null;
        switch (type) {
            case "exit":
                GoalChecker l = new ExitChecker();
                g = new BasicGoal("Get to Exit", l);
                break;
            case "enemies":
                GoalChecker b = new EnemyChecker();
                g = new BasicGoal("Kill all enemies", b);
                break;
            case "boulders":
                GoalChecker c = new SwitchChecker();
                g = new BasicGoal("Active all switches", c);
                break;
            case "treasure":
                GoalChecker a = new TreasureChecker();
                g = new BasicGoal("Get all treasures", a);
                break;
            default :
                break;
        }
        if (g == null) {
            System.out.println("Error in GoalDetector");
        }
        return g;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
        	MovementManager pm = new MovementManager(dungeon, new PlayerMovementHandler());
            Player player = new Player(x,y,pm);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            exit.addObs(dungeon.getGoalDetector());;
            break;
        case "boulder":
        	MovementManager bm = new MovementManager(dungeon, new BoulderMovementHandler());
            Boulder boulder = new Boulder(x, y, bm);
            onLoad(boulder);
            entity = boulder;
            break;
        case "enemy":
        	MovementManager em = new MovementManager(dungeon, new EnemyMovementHandler());
            Enemy enemy = new Enemy(x, y, em);
            onLoad(enemy);
            entity = enemy;
            enemy.addObs(dungeon.getGoalDetector());
            break;
        case "treasure":
            Treasure t = new Treasure(x,y);
            onLoad(t);
            entity = t;
            t.addObs(dungeon.getGoalDetector());
            break;
        case "invincibility":
            InvinciblePotion p = new InvinciblePotion(x,y,5);
            onLoad(p);
            entity = p;
            break;
        case "sword":
        	Sword sword = new Sword(x,y);
            onLoad(sword);
            entity = sword;
            break;
        case "door":
            Door d = new Door(x,y,json.getInt("id"));
            onLoad(d);
            entity = d;
            break;
        case "key":
        	Key k = new Key(x,y,json.getInt("id"));
            onLoad(k);
            entity = k;
            break;
        case "portal":
        	Portal portal = new Portal(x,y,json.getInt("id"));
            onLoad(portal);
            entity = portal;
            break;
        case "switch":
        	Switch s = new Switch(x,y);
            onLoad(s);
            entity = s;
            s.addObs(dungeon.getGoalDetector());
            break;
        case "predictor" :
        	MovementManager em2 = new MovementManager(dungeon, new EnemyMovementHandler());
        	Predictor s2 = new Predictor(x,y,em2);
            onLoad(s2);
            entity = s2;
            s2.addObs(dungeon.getGoalDetector());
            break;
        }
        
        //
        
        if (entity != null) {
        	dungeon.addEntity(entity);
        }
        
        //
    }

    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(InvinciblePotion potion);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Switch s);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Predictor enemy);
    
    
    // TODO Create additional abstract methods for the other entities

}
