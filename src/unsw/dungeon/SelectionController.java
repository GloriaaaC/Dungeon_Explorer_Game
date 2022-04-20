package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SelectionController extends PageController {

    @FXML
    private ImageView level1;

    @FXML
    private ImageView level2;

    @FXML
    private ImageView level3;

    @FXML
    private ImageView back;
    
	private Image l1;
	private Image l2;
	private Image l3;
	private Image l11;
	private Image l12;
	private Image l13;
	
	
	private Scene menuPage;
	private String mission;
	
	private boolean selected;
	
	private int difficulty;

	private Timeline t2;

    public SelectionController() {
    	l1 = new Image("/level1.png");
    	l2 = new Image("/level2.png");
    	l3 = new Image("/level3.png");
    	l11 = new Image("/l3.png");
    	l12 = new Image("/l1.jpg");
    	l13 = new Image("/l2.jpg");
    	menuPage = null;
    	selected = false;
    	t2 = new Timeline();
    	mission = "";
    	difficulty = 2;
    }
    
    @FXML
    public void initialize() {
    	level1.setImage(l1);
    	level2.setImage(l2);
    	level3.setImage(l3);
    	
	    EventHandler<ActionEvent> in5 = new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent e) {
	    		selected = false;
	    	}
	    };
	    this.t2 = new Timeline();
	    t2.getKeyFrames().add(new KeyFrame(Duration.millis(1000), in5));
	    t2.setCycleCount(1);
    	
    }
    
    public void setMenuPage(Scene s) {
    	this.menuPage = s;
    }
    
    @FXML
	public void backToMenu(MouseEvent event) {
    	back.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 back.setOpacity(0.5);
		     }
		});
    	back.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 back.setOpacity(1);
		     }
		});
    	back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(menuPage);
		    	 stage.show();
		     }
		});
	}
    
	@FXML
	public void setLevel1(MouseEvent event) {
		level1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 level1.setImage(l11);
		     }
		});
	}
	
	@FXML
	public void backLevel1(MouseEvent event) {
		level1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 level1.setImage(l1);
		     }
		});
		level1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 PauseController pc = new PauseController();
		    	 FXMLLoader loader = null;
				try {
					loader = newPlayPage("level3.json", pc, "Story:\n    Your friends and you were playing hide and seek.\n"
	                		+ "When it's your term , you count to ten and guess\nwhat? They all disappeared and leave you in this\ndark place"
	                		+ ". Now it's your turn to figure a way out!!!\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	 Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	 pc.setFile("level3.json");
		    	 pc.setMenuPage(menuPage);
		    	 pc.setStage(stage);
		    	 pc.setStory("Story:\n    Your friends and you were playing hide and seek.\n"
	                		+ "When it's your term , you count to ten and guess\nwhat? They all disappeared and leave you in this\ndark place"
	                		+ ". Now it's your turn to figure a way out!!!\n");
		    	 if (!selected) {
			    	 Stage dialog = new Stage(); // new stage
			    	 selected = true;
			    	 t2.play();
			    	 dialog.initModality(Modality.APPLICATION_MODAL);
		                MissionPageController missionPageController = new MissionPageController();
		                missionPageController.setStroy("Story:\n    Your friends and you were playing hide and seek.\n"
		                		+ "When it's your term , you count to ten and guess\nwhat? They all disappeared and leave you in this\ndark place"
		                		+ ". Now it's your turn to figure a way out!!!\n");
		                missionPageController.setMission(mission);
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
		    	 }
		         Scene scene = new Scene(root);
		         pc.setContinueScene(scene);
		         pc.setDifficulty(difficulty);
		         root.requestFocus();
		         stage.setScene(scene);
		    	 stage.show();
		    	 stage.setWidth(725);
		    	 stage.setHeight(540);
		     }
		});
	}
	
	@FXML
	public void setLevel2(MouseEvent event) {
		level2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 level2.setImage(l12);
		     }
		});
	}
	
	@FXML
	public void backLevel2(MouseEvent event) {
		level2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 level2.setImage(l2);
		     }
		});
		level2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 PauseController pc = new PauseController();
		    	 FXMLLoader loader = null;
				try {
					loader = newPlayPage("advanced.json", pc,"Story:\n    Last second you just walked through your DIY portal\n and here it goes, Portal, Portal and Portals!!! "
	                		+ "You don't \n know  where those portals in front of you gonna take \n you to..... Treasure? Weapon? Danger? or Maybe \n Directly to the Exit?"
	                		+ "Your turn, In Order to not leave\nregret this advanture, take all the treasures with you!!!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	 Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	 pc.setFile("advanced.json");
		    	 pc.setMenuPage(menuPage);
		    	 pc.setStage(stage);
		    	 pc.setStory("Story:\n    Last second you just walked through your DIY portal\n and here it goes, Portal, Portal and Portals!!! "
	                		+ "You don't \n know  where those portals in front of you gonna take \n you to..... Treasure? Weapon? Danger? or Maybe \n Directly to the Exit?"
	                		+ "Your turn, In Order to not leave\nregret this advanture, take all the treasures with you!!!");
		    	 if (!selected) {
			    	 Stage dialog = new Stage(); // new stage
			    	 selected = true;
			    	 t2.play();
			    	 dialog.initModality(Modality.APPLICATION_MODAL);
		                MissionPageController missionPageController = new MissionPageController();
		                missionPageController.setStroy("Story:\n    Last second you just walked through your DIY portal\n and here it goes, Portal, Portal and Portals!!! "
		                		+ "You don't \n know  where those portals in front of you gonna take \n you to..... Treasure? Weapon? Danger? or Maybe \n Directly to the Exit?"
		                		+ "Your turn, In Order to not leave\nregret this advanture, take all the treasures with you!!!");
		                missionPageController.setMission(mission);
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
		    	 }
		         Scene scene = new Scene(root);
		         pc.setDifficulty(difficulty);
		         pc.setContinueScene(scene);
		         root.requestFocus();
		         stage.setScene(scene);
		    	 stage.show();
		    	 stage.setWidth(725);
		    	 stage.setHeight(540);
		     }
		});
	}
	
	@FXML
	public void setLevel3(MouseEvent event) {
		level3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 level3.setImage(l13);
		     }
		});
		level3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 PauseController pc = new PauseController();
		    	 FXMLLoader loader = null;
				try {
					loader = newPlayPage("level2.json", pc,"Story:\n    The villager are suffering from the witches at \n dungeon,they beg you to kill those bad guys "
	                		+ "in \n order to save their peaceful life However you\nare too"
	                		+ " broken to buy any equipments and sadly you\n don't have any friends. So you need to kill all enemies \nusing materials in "
	                		+ "Dungeon!! And you also heard that\na secret weapon is hidden here  which can blow up all\n enemies immediately if actived ......\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	 Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	 pc.setFile("level2.json");
		    	 pc.setStory("Story:\n    The villager are suffering from the witches at \n dungeon,they beg you to kill those bad guys "
	                		+ "in \n order to save their peaceful life However you\nare too"
	                		+ " broken to buy any equipments and sadly you\n don't have any friends. So you need to kill all enemies \nusing materials in "
	                		+ "Dungeon!! And you also heard that\na secret weapon is hidden here  which can blow up all\n enemies immediately if actived ......\n");
		    	 pc.setMenuPage(menuPage);
		    	 pc.setStage(stage);
		    	 if (!selected) {
			    	 Stage dialog = new Stage(); // new stage
			    	 selected = true;
			    	 t2.play();
			    	 dialog.initModality(Modality.APPLICATION_MODAL);
		                MissionPageController missionPageController = new MissionPageController();
		                missionPageController.setStroy("Story:\n    The villager are suffering from the witches at \n dungeon,they beg you to kill those bad guys "
		                		+ "in \n order to save their peaceful life However you\nare too"
		                		+ " broken to buy any equipments and sadly you\n don't have any friends. So you need to kill all enemies \nusing materials in "
		                		+ "Dungeon!! And you also heard that\na secret weapon is hidden here  which can blow up all\n enemies immediately if actived ......\n");
		                missionPageController.setMission(mission);
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
		    	 }
		         Scene scene = new Scene(root);
		         pc.setContinueScene(scene);
		         pc.setDifficulty(difficulty);
		         root.requestFocus();
		         stage.setScene(scene);
		    	 stage.show();
		    	 stage.setWidth(725);
		    	 stage.setHeight(540);
		     }
		});
	}
	
	@FXML
	public void backLevel3(MouseEvent event) {
		level3.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 level3.setImage(l3);
		     }
		});
	}
	
	private FXMLLoader newPlayPage(String name, PauseController pc,String story) throws IOException {
		DungeonControllerLoader dungeonLoader = null;
		try {
			dungeonLoader = new DungeonControllerLoader(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
         DungeonController controller = null;
		try {
			controller = dungeonLoader.loadController();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("pause.fxml"));
		loader1.setController(pc);
        Parent root1 = null;
			try {
				root1 = loader1.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		Scene scene = new Scene(root1);
		controller.setStage(stage);
		controller.setPauseScene(scene);
	    root1.requestFocus();

	    FinalController win = new FinalController();
        win.setMenuPag(menuPage);
        win.setStage(stage);
        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("win_final.fxml"));
        loader4.setController(win);
        Parent root4 = loader4.load();
        Scene winScene = new Scene(root4);    
        
        FinalController lose = new FinalController();
        lose.setMenuPag(menuPage);
        lose.setStage(stage);
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("lose_final.fxml"));
        loader5.setController(lose);
        Parent root5 = loader5.load();
        Scene loseScene = new Scene(root5);  
        
        controller.setWinScene(winScene);
        
        controller.setLoseScene(loseScene);
        
        controller.setDifficulty(difficulty);
        
        controller.setStory(story);
        
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
         loader.setController(controller);
         mission = controller.getGoal();
		
		return loader;
	}
	
	public void setDifficulty(int di) {
		this.difficulty = di;
	}
    
}

