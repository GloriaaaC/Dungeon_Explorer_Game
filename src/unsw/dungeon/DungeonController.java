package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends PageController {

    @FXML
    private GridPane squares;
    
    @FXML
    private ImageView key;

    @FXML
    private ImageView weapon;

    @FXML
    private ImageView treasure;

    @FXML
    private ImageView potion;

    @FXML
    private ImageView count_treasure;

    @FXML
    private ImageView count_wearbility;

    @FXML
    private ImageView pause;

    @FXML
    private ImageView mission;
    
    @FXML
    private ImageView rules;


    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private boolean isStart;
    
    private Timeline t;
    private Timeline t2;
    
	private Image k;
	private Image k11;
	private Image w;
	private Image w11;
	private Image p;
	private Image p11;
	private Image c0;
	private Image c1;
	private Image c2;
	private Image c3;
	private Image c4;
	private Image c5;
	
	private boolean selected;

	private Scene pScene;

	private Scene winScene;

	private Scene loseScene;

	private String story;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities)  {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.isStart = false;
        this.selected = false;
	    EventHandler<ActionEvent> in3 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		dungeon.next();
	    	}
	    };	
	    this.t = new Timeline();
	    t.getKeyFrames().add(new KeyFrame(Duration.millis(500), in3));
	    t.setCycleCount(Timeline.INDEFINITE);
	    
	    EventHandler<ActionEvent> in5 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		selected = false;
	    	}
	    };
	    this.t2 = new Timeline();
	    t2.getKeyFrames().add(new KeyFrame(Duration.millis(1000), in5));
	    t2.setCycleCount(1);
	    
	    this.pScene = null;
	    this.winScene = null;
	    this.loseScene = null;
	    
		k = new Image("/key.png");
		k11 = new Image("/nokey.png");
		w = new Image("/greatsword_1_new.png");
		w11 = new Image("/noweapon.png");
		p = new Image("/brilliant_blue_new.png");
		p11 = new Image("/nopotion.png");
		c0 = new Image("/x0.png");
		c1 = new Image("/x1.png");
		c2 = new Image("/x2.png");
		c3 = new Image("/x3.png");
		c4 = new Image("/x4.png");
		c5 = new Image("/x5.png");
		
    }
    
    public String getGoal() {
    	return dungeon.getGoal();   			
    }
    
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }
        
        key.setImage(k11);
    	weapon.setImage(w11);
    	potion.setImage(p11);
    	count_treasure.setImage(c0);
    	
    	player.getWearability().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	int num = player.getWearability().getValue();
                if (num == 0) {
                	count_wearbility.setOpacity(0);
                	weapon.setImage(w11);
                } else if (num == 1) {
                	count_wearbility.setOpacity(1);
                	count_wearbility.setImage(c1);
                	weapon.setImage(w);
                } else if (num == 2) {
                	count_wearbility.setOpacity(1);
                	count_wearbility.setImage(c2);
                	weapon.setImage(w);
                } else if (num == 3) {
                	count_wearbility.setOpacity(1);
                	count_wearbility.setImage(c3);
                	weapon.setImage(w);
                } else if (num == 4) {
                	count_wearbility.setOpacity(1);
                	count_wearbility.setImage(c4);
                	weapon.setImage(w);
                } else if (num == 5) {
                	count_wearbility.setOpacity(1);
                	count_wearbility.setImage(c5);
                	weapon.setImage(w);
                }
            }
        });
    	
    	player.getNumTreasure().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	int num = player.getNumTreasure().getValue();
                if (num == 1) {
                	count_treasure.setImage(c1);
                } else if (num == 2) {
                	count_treasure.setImage(c2);
                } else if (num == 3) {
                	count_treasure.setImage(c3);
                } else if (num == 4) {
                	count_treasure.setImage(c4);
                } else if (num >= 5) {
                	count_treasure.setImage(c5);
                }
            }
        });
    	
    	player.hasKey().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
            		Boolean oldValue, Boolean newValue) {
            	if (player.hasKey().getValue()) {
            		key.setImage(k);
            	} else {
            		key.setImage(k11);
            	}
            }
        });
    	
    	player.invincible().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
            		Boolean oldValue, Boolean newValue) {
            	if (player.invincible().getValue()) {
            		potion.setImage(p);
            	} else {
            		potion.setImage(p11);
            	}
            }
        });
    	
    	player.alive().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
            		Boolean oldValue, Boolean newValue) {
            	if (!player.alive().getValue()) {
            		stage.setScene(loseScene);
            		stage.show();
            		stage.setWidth(645);
            		stage.setHeight(355);
            	}
            }
        });
    	
    	dungeon.status().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
            		Boolean oldValue, Boolean newValue) {
            	if (dungeon.status().getValue() == true) {
            		stage.setScene(winScene);
            		stage.show();
            		stage.setWidth(645);
            		stage.setHeight(355);
            	}
            }
        });
    	
    	
    }
    
    @FXML void missionHandler() {
    	mission.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 if (!selected) {
			    	 Stage dialog = new Stage(); // new stage
			    	 selected = true;
			    	 dialog.initModality(Modality.APPLICATION_MODAL);
		                MissionPageController missionPageController = new MissionPageController();
		                missionPageController.setStroy(story);
		                missionPageController.setMission(dungeon.getGoal());
		                FXMLLoader loader5 = new FXMLLoader(getClass().getResource("mission.fxml"));
		                loader5.setController(missionPageController);
		                Parent root5 = null;
						try {
							root5 = loader5.load();
						} catch (IOException e) {
							e.printStackTrace();
						}
		                Scene dialogScene = new Scene(root5);    
		                dialog.setScene(dialogScene);
		                dialog.show();
		                t2.play();
		    	 }
		    	 t.stop();
		    	 isStart = false;
		     }
		});
    	mission.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 mission.setFitHeight(42);
		    	 mission.setFitWidth(88);
		     }
		});
    	mission.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 mission.setFitHeight(37);
		    	 mission.setFitWidth(82);
		     }
		});
    }
    
    @FXML void rulesHandler() {
    	rules.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 if (!selected) {
			    	 Stage dialog = new Stage(); // new stage
			    	 selected = true;
			    	 dialog.initModality(Modality.APPLICATION_MODAL);
			    	 FXMLLoader loader1 = new FXMLLoader(getClass().getResource("rules.fxml"));
			    	 Scene dialogScene = null;
					try {
						Parent root3 = loader1.load();
						dialogScene = new Scene(root3); 
					} catch (IOException e) {
						e.printStackTrace();
					}
		                dialog.setScene(dialogScene);
		                dialog.show();
		                t2.play();
		    	 }
		    	 t.stop();
		    	 isStart = false;
		     }
		});
    	rules.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 rules.setFitHeight(42);
		    	 rules.setFitWidth(88);
		     }
		});
    	rules.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 rules.setFitHeight(37);
		    	 rules.setFitWidth(82);
		     }
		});
    }

    @FXML void pauseHandler() {
    	pause.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(pScene);
		    	 stage.setWidth(535);
		    	 stage.setHeight(405);
		    	 t.stop();
		    	 isStart = false;
		     }
		});
    	pause.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 pause.setFitHeight(42);
		    	 pause.setFitWidth(88);
		     }
		});
    	pause.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 pause.setFitHeight(37);
		    	 pause.setFitWidth(82);
		     }
		});
    }
    @FXML
    public void handleKeyPress(KeyEvent event) {
    	
    	// Just for Demo
    	if (event.getCode().isDigitKey()) {
    		dungeon.moveEnemy();
    	}
    	//
    	
        switch (event.getCode()) {
        case UP:
        	if (!isStart) {
        		isStart = true;
        		t.play();
        	}
            player.moveUp();
            break;
        case DOWN:
        	if (!isStart) {
        		isStart = true;
        		t.play();
        	}
            player.moveDown();
            break;
        case LEFT:
        	if (!isStart) {
        		isStart = true;
        		t.play();
        	}
            player.moveLeft();
            break;
        case RIGHT:
        	if (!isStart) {
        		isStart = true;
        		t.play();
        	}
            player.moveRight();
            break;
        default:
            break;
        }
    }

	public void setPauseScene(Scene scene) {
		this.pScene = scene;
	}

	public void setWinScene(Scene s) {
		this.winScene = s;
		
	}
	
	public void setLoseScene(Scene s) {
		this.loseScene = s;
	}

	public void setDifficulty(int difficulty) {
		dungeon.setDifficulty(difficulty);
	}

	public void setStory(String story) {
		this.story = story;
	}

}

