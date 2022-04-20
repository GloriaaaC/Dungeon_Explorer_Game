package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PauseController extends PageController {

    @FXML
    private ImageView con;

    @FXML
    private ImageView restart;

    @FXML
    private ImageView exit;
    
	private Image c;
	private Image c11;
	private Image r;
	private Image r11;
	private Image e;


	private Image e11;
	private Scene continueScene;
	private Scene menuScene;
	private String file;

	private int difficulty;

	private String story;
	
	public PauseController() {
		c = new Image("/continue.png");
		c11 = new Image("/continue11.png");
		r = new Image("/restart.png");
		r11 = new Image("/restart11.png");
		e = new Image("/exitname.png");
		e11 = new Image("/exit11.png");
		menuScene = null;
		continueScene = null;
		file = "";
		difficulty = 2;
		story = "";
	}
	
	public void setContinueScene(Scene continueScene) {
		this.continueScene = continueScene;
	}
	
	public void setMenuPage(Scene s) {
		this.menuScene = s;
	}
	
	@FXML
	public void initialize() {
		con.setImage(c);
		restart.setImage(r);
		exit.setImage(e);
	}
	
	@FXML
	public void conEnter(MouseEvent event) {
		con.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 con.setImage(c11);
		     }
		});
		con.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(continueScene);
		    	 stage.show();
		    	 stage.setWidth(725);
		    	 stage.setHeight(540);
		     }
		});
	}
	
	@FXML
	public void conExit(MouseEvent event) {
		con.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 con.setImage(c);
		     }
		});
	}
	
	@FXML
	public void restartEnter(MouseEvent event) {
		restart.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 restart.setImage(r11);
		     }
		});
		restart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		 		DungeonControllerLoader dungeonLoader = null;
				try {
					dungeonLoader = new DungeonControllerLoader(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		         DungeonController controller = null;
				try {
					controller = dungeonLoader.loadController();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				controller.setStory(story);
				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("pause.fxml"));
				PauseController pc = new PauseController();
				pc.setFile(file);
				pc.setMenuPage(menuScene);
				pc.setStage(stage);
				pc.setStory(story);
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
				controller.setDifficulty(difficulty);
				FinalController win = new FinalController();
		        win.setMenuPag(menuScene);
		        win.setStage(stage);
		        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("win_final.fxml"));
		        loader4.setController(win);
		        Parent root4 = null;
				try {
					root4 = loader4.load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Scene winScene = new Scene(root4);    
		        
		        FinalController lose = new FinalController();
		        lose.setMenuPag(menuScene);
		        lose.setStage(stage);
		        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("lose_final.fxml"));
		        loader5.setController(lose);
		        Parent root5 = null;
				try {
					root5 = loader5.load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Scene loseScene = new Scene(root5);  
		        
		        controller.setWinScene(winScene);
		        
		        controller.setLoseScene(loseScene);
			    root1.requestFocus();

		         FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		         loader.setController(controller);
		    	 Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
		         Scene scene1 = new Scene(root);
		         pc.setContinueScene(scene1);
		         root.requestFocus();
		         stage.setScene(scene1);
		    	 stage.show();
		    	 stage.setWidth(725);
		    	 stage.setHeight(540);
		     }
		});
	}
	
	@FXML
	public void restartExit(MouseEvent event) {
		restart.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 restart.setImage(r);
		     }
		});
	}
	
	@FXML
	public void exitEnter(MouseEvent event) {
		exit.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 exit.setImage(e11);
		     }
		});
	}
	
	@FXML
	public void exitExit(MouseEvent event) {
		exit.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 exit.setImage(e);
		     }
		});
		exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(menuScene);
		    	 stage.show();
		     }
		});
	}

	public void setFile(String f) {
		this.file = f;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public void setStory(String story) {
		this.story = story;
	}
	

}
