package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image wallImage;
    private Image boulderImage;
    private Image closedDoorImage;
    private Image openedDoorImage;
    private Image exitImage;
    private Image potionImage;
    private Image enemyImage;
    private Image treasureImage;
    private Image keyImage;
    private Image portalImage;
    private Image swordImage;
    private Image switchImage;
    private Image smartManImage;
    private Image walk1;
    private Image walk2;
    private Image walk3;
    private Image walk4;
    private Image walk1U;
    private Image walk2U;
    private Image walk3U;
    private Image walk4U;
    private Timeline t;
    private boolean tIsPlaying;
    private Timeline t1;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        wallImage = new Image("/brick_brown_0.png");
        boulderImage = new Image("/boulder.png");
        closedDoorImage = new Image("/closed_door.png");
        openedDoorImage = new Image("/open_door.png");
        exitImage = new Image("/exit.png");
        potionImage = new Image("/brilliant_blue_new.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        treasureImage = new Image("/gold_pile.png");
        keyImage = new Image("/key.png");
        portalImage = new Image("/portal.png");
        swordImage = new Image("/greatsword_1_new.png");
        switchImage = new Image("/pressure_plate.png");
        smartManImage = new Image("/gnome.png");
        walk1 = new Image("/walk1.png");
        walk2 = new Image("/walk2.png");
        walk3 = new Image("/walk3.png");
        walk4 = new Image("/walk4.png");
        walk1U = new Image("/walk1-upgraded.png");
        walk2U = new Image("/walk2-upgraded.png");
        walk3U = new Image("/walk3-upgraded.png");
        walk4U = new Image("/walk4-upgraded.png");
        t = new Timeline();
        t1 = new Timeline();
        tIsPlaying = false;
        
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(walk1);
        addEntity(player, view);
        visibleListener(player, view);
        playerAnimation(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
        visibleListener(sword,view);
	}

	@Override
	public void onLoad(Portal portal) {
		ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
	}

	@Override
	public void onLoad(Key key) {
		ImageView view = new ImageView(keyImage);
        addEntity(key, view);
        visibleListener(key,view);
	}

	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
        visibleListener(enemy,view);
	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
        visibleListener(treasure,view);
	}

	@Override
	public void onLoad(InvinciblePotion potion) {
		ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
        visibleListener(potion,view);
	}

	@Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
	}

	@Override
	public void onLoad(Switch s) {
		ImageView view = new ImageView(switchImage);
        addEntity(s, view);
	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(closedDoorImage);
        addEntity(door, view);
        doorListener(door, view);
	}
	
	@Override
	public void onLoad(Predictor enemy) {
		ImageView view = new ImageView(smartManImage);
        addEntity(enemy, view);
        visibleListener(enemy,view);
	}
	
	private void doorListener(Door entity, ImageView view) {
        entity.open().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				view.setImage(openedDoorImage);
			}

        });
    }
	
	private Timeline setTimeline(Player p, ImageView view) {
	    Timeline t1 = new Timeline();
	    t1.setCycleCount(1);
	    
  	    EventHandler<ActionEvent> in1 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		view.setOpacity(0.5);
	    	}
	    };	
	    
  	    EventHandler<ActionEvent> in2 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		view.setOpacity(1);
	    	}
	    };	
	    
	    EventHandler<ActionEvent> in3 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		p.decrementor();
	    	}
	    };	
	    
	    EventHandler<ActionEvent> in5 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		tIsPlaying = false;
	    	}
	    };	
	    
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(5), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(250), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(500), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(750), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(1000), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(1000), in3));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(1250), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(1500), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(1750), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(2000), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(2000), in3));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(2250), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(2500), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(2750), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3000), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3000), in3));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3250), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3350), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3500), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3600), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3750), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3800), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(3900), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4000), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4000), in3));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4100), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4200), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4350), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4500), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4700), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4800), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4900), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(4950), in1));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(5000), in2));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(5000), in3));
  	    t1.getKeyFrames().add(new KeyFrame(Duration.millis(5000), in5));
  	    
  	    return t1;
	}
	
	private void playerAnimation(Player p, ImageView view) {
     	t.setCycleCount(Timeline.INDEFINITE);
     	EventHandler<ActionEvent> en = new EventHandler<ActionEvent>() {
  	    	public void handle(ActionEvent e) {
  	    		if (!p.hasSword()) {
  	    			view.setImage(walk1);
  	    		} else {
  	    			view.setImage(walk1U);
  	    		}
  	    	}
  	    };	 
  	    t.getKeyFrames().add(new KeyFrame(Duration.millis(200), en));
  	    
  	    EventHandler<ActionEvent> en1 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		if (!p.hasSword()) {
  	    			view.setImage(walk2);
  	    		} else {
  	    			view.setImage(walk2U);
  	    		}
	    	}
	    };	 
	    t.getKeyFrames().add(new KeyFrame(Duration.millis(400), en1));
	    
	    EventHandler<ActionEvent> en3 = new EventHandler<ActionEvent>() {
  	    	public void handle(ActionEvent e) {
  	    		if (!p.hasSword()) {
  	    			view.setImage(walk3);
  	    		} else {
  	    			view.setImage(walk3U);
  	    		}
  	    	}
  	    };	 
  	    t.getKeyFrames().add(new KeyFrame(Duration.millis(600), en3));
  	    
  	    EventHandler<ActionEvent> en4 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		if (!p.hasSword()) {
  	    			view.setImage(walk4);
  	    		} else {
  	    			view.setImage(walk4U);
  	    		}
	    	}
	    };	 
	    t.getKeyFrames().add(new KeyFrame(Duration.millis(800), en4));
	    t.play();
	    
        t1 = setTimeline(p,view);
        p.IsUpdate().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (p.IsUpdate().getValue()) {
					p.IsUpdate().setValue(false);
					if (tIsPlaying) {
						t1.stop();
					}
					t1 = setTimeline(p,view);
					if (p.invincible().getValue()) {
						t1.play();
						tIsPlaying = true;
					}
				}
			}
        });
  	   
    }
	
	private void visibleListener(Item entity, ImageView view) {
    	Timeline t = new Timeline();
     	t.setCycleCount(2);
     	EventHandler<ActionEvent> en = new EventHandler<ActionEvent>() {
  	    	public void handle(ActionEvent e) {
  	    		view.setOpacity(1);
  	    	}
  	    };	 
  	    t.getKeyFrames().add(new KeyFrame(Duration.millis(75), en));
  	    
  	    EventHandler<ActionEvent> en1 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		view.setOpacity(0);
	    	}
	    };	 
	    t.getKeyFrames().add(new KeyFrame(Duration.millis(150), en1));
        entity.visible().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (entity.visible().getValue()) {
					view.setOpacity(1);
				} else {
					view.setOpacity(0);
					t.play();
				}
			}
        });
	}
    
    private void visibleListener(Player entity, ImageView view) {
        entity.alive().addListener(new ChangeListener<Boolean>() {

        	@Override
        	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	    		// TODO Auto-generated method stub
	    		if (entity.alive().getValue()) {
	    			view.setOpacity(1);
	    		} else {
	    			view.setOpacity(0);
	    		}
        	}
        });    
    }
    
    private void visibleListener(Enemy entity, ImageView view) {
    	Timeline t = new Timeline();
     	t.setCycleCount(2);
     	EventHandler<ActionEvent> en = new EventHandler<ActionEvent>() {
  	    	public void handle(ActionEvent e) {
  	    		view.setOpacity(1);
  	    	}
  	    };	 
  	    t.getKeyFrames().add(new KeyFrame(Duration.millis(100), en));
  	    
  	    EventHandler<ActionEvent> en1 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		view.setOpacity(0);
	    	}
	    };	 
	    t.getKeyFrames().add(new KeyFrame(Duration.millis(200), en1));
  	    
        entity.alive().addListener(new ChangeListener<Boolean>() {
          	 

        	@Override
        	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	    		if (entity.alive().getValue()) {
	    			view.setOpacity(1);
	    		} else {
	    			view.setOpacity(0);
	    			t.play();
	    		}
        	}
        });    
    }

}

