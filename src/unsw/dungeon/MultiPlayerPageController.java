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

public class MultiPlayerPageController extends PageController {

	
	
    @FXML
    private ImageView playerImage;

    @FXML
    private ImageView exit;
    
    @FXML
    private ImageView rules;

    @FXML
    private GridPane squares;

    @FXML
    private ImageView enemyImage;
    
    private boolean selected;
    
    private Scene menuPage;
    
    private Timeline t2;
    private Timeline tp;
    private Timeline te;

	private Dungeon dungeon;

	private Player player;

	private ArrayList<ImageView> initialEntities;

	private boolean isStart;
	
	private Scene loseScene;
	
	private Scene winScene;
    
	public void setMenuPage(Scene menuPage) {
		this.menuPage = menuPage;
	}
	
	public MultiPlayerPageController(Dungeon dungeon, List<ImageView> initialEntities) {
    	super();
    	this.selected = false;
    	this.menuPage = null;
    	this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.isStart = false;
        loseScene = null;
        winScene= null;
    }

    @FXML
    void initialize() {
    	this.tp = new Timeline();
    	this.te = new Timeline();
    	tp.setCycleCount(Timeline.INDEFINITE);
    	te.setCycleCount(Timeline.INDEFINITE);
    	EventHandler<ActionEvent> in1 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		playerImage.setY(playerImage.getY()+5);
	    	}
	    };
	    EventHandler<ActionEvent> in2 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		playerImage.setY(playerImage.getY()-5);
	    	}
	    };
	    
	    EventHandler<ActionEvent> in3 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		enemyImage.setY(enemyImage.getY()+5);
	    	}
	    };
	    EventHandler<ActionEvent> in4 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		enemyImage.setY(enemyImage.getY()-5);
	    	}
	    };
	    
	    tp.getKeyFrames().add(new KeyFrame(Duration.millis(750), in1));
	    tp.getKeyFrames().add(new KeyFrame(Duration.millis(1500), in2));
	    
	    te.getKeyFrames().add(new KeyFrame(Duration.millis(750), in3));
	    te.getKeyFrames().add(new KeyFrame(Duration.millis(1500), in4));
    	
    	
    	EventHandler<ActionEvent> in5 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		selected = false;
	    	}
	    };
	    this.t2 = new Timeline();
	    t2.getKeyFrames().add(new KeyFrame(Duration.millis(1000), in5));
	    t2.setCycleCount(1);
	    
	    tp.play();
	    te.play();
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
    
    @FXML
    void rulesHandler(MouseEvent event) {
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

		     }
		});
    	rules.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 rules.setFitHeight(72);
		    	 rules.setFitWidth(119);
		     }
		});
   		rules.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 rules.setFitHeight(67);
		    	 rules.setFitWidth(109);
		     }
		});
    }
    
    @FXML
    void exitHandler(MouseEvent event) {
		exit.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 exit.setOpacity(0.5);
		     }
		});
		exit.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 exit.setOpacity(1);
		     }
		});
		exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(menuPage);
		    	 stage.show();
		    	 stage.setWidth(535);
		    	 stage.setHeight(420);
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
        	}
            player.moveUp();
            break;
        case DOWN:
        	if (!isStart) {
        		isStart = true;
        	}
            player.moveDown();
            break;
        case LEFT:
        	if (!isStart) {
        		isStart = true;
        	}
            player.moveLeft();
            break;
        case RIGHT:
        	if (!isStart) {
        		isStart = true;
        	}
            player.moveRight();
            break;
        case W:
        	if (!isStart) {
        		isStart = true;
        	}
            dungeon.enemyUp();
            break;
        case S:
        	if (!isStart) {
        		isStart = true;
        	}
        	dungeon.enemyDown();
            break;
        case A:
        	if (!isStart) {
        		isStart = true;
        	}
        	dungeon.enemyLeft();
            break;
        case D:
        	if (!isStart) {
        		isStart = true;
        	}
        	dungeon.enemyRight();
            break;
        default:
            break;
        }
    }
    
	public void setWinScene(Scene s) {
		this.winScene = s;
		
	}
	
	public void setLoseScene(Scene s) {
		this.loseScene = s;
	}

}
