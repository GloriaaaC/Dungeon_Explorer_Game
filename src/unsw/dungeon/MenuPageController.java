package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;



public class MenuPageController extends PageController{
	
	@FXML
	private ImageView single;
	
	@FXML
	private ImageView doubl;
	
	@FXML
	private ImageView setting;
	
	@FXML
	private ImageView credit;
	
	private Image s;
	private Image s11;
	private Image d;
	private Image d11;
	private Image t;
	private Image t11;
	private Image c;
	private Image c11;
	private Scene sceneC;
	private Scene sceneS;

	private Scene selfScene;

	private Scene SceneSetting;
	
	
	public MenuPageController() {
		s = new Image("/single.png");
		s11 = new Image("/single11.png");
		d = new Image("/double.png");
		d11 = new Image("/double11.png");
		t = new Image("/setting.png");
		t11 = new Image("/setting11.png");
		c = new Image("/credit.png");
		c11 = new Image("/credit11.png");
		this.stage = null;
		sceneS = null;
		selfScene = null;
		sceneC = null;
		SceneSetting = null;
	}

	
	public void setCreditScene(Scene s) {
		this.sceneC = s;
	}
	
	public void setSingleScene(Scene s) {
		this.sceneS = s;
	}
	
	@FXML
	public void initialize() {
		single.setImage(s);
		doubl.setImage(d);
		setting.setImage(t);
		credit.setImage(c);
	}
	
	
	@FXML
	public void singleEnter(MouseEvent event) {
		single.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 single.setImage(s11);
		     }
		});
		single.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(sceneS);
		    	 stage.show();
		     }
		});
	}
	
	@FXML
	public void singleExit(MouseEvent event) {
		single.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 single.setImage(s);
		     }
		});
	}
	
	@FXML
	public void doubleEnter(MouseEvent event) {
		doubl.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 doubl.setImage(d11);
		     }
		});
		doubl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 MultipleControllerLoader dungeonLoader = null;
					try {
						dungeonLoader = new MultipleControllerLoader("double.json");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
			         MultiPlayerPageController multiPlayerPageController = null;
					try {
						multiPlayerPageController = dungeonLoader.loadController();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
		    	 multiPlayerPageController.setMenuPage(selfScene);
		    	 multiPlayerPageController.setStage(stage);
		         FXMLLoader loader3 = new FXMLLoader(getClass().getResource("double.fxml"));
		         
		         FinalController win = new FinalController();
		         win.setMenuPag(selfScene);
		         win.setStage(stage);
		         FXMLLoader loader4 = new FXMLLoader(getClass().getResource("player_win.fxml"));
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
		         lose.setMenuPag(selfScene);
		         lose.setStage(stage);
		         FXMLLoader loader5 = new FXMLLoader(getClass().getResource("enemy_win.fxml"));
		         loader5.setController(lose);
		         Parent root5 = null;
				try {
					root5 = loader5.load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		         Scene loseScene = new Scene(root5);  
		         
		         multiPlayerPageController.setWinScene(winScene);
		         
		         multiPlayerPageController.setLoseScene(loseScene);
		         
		         loader3.setController(multiPlayerPageController);
		         Parent root3 = null;
				try {
					root3 = loader3.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		         Scene playScene = new Scene(root3);
		         root3.requestFocus();
		         stage.setScene(playScene);
		         stage.show();
		         stage.setWidth(880);
		         stage.setHeight(550);
		     }
		});
	}
	
	@FXML
	public void doubleExit(MouseEvent event) {
		doubl.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 doubl.setImage(d);
		     }
		});
	}
	
	@FXML
	public void settingEnter(MouseEvent event) {
		setting.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 setting.setImage(t11);
		     }
		});
		setting.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(SceneSetting);
		    	 stage.show();
		     }
		});
	}
	
	@FXML
	public void settingExit(MouseEvent event) {
		setting.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 setting.setImage(t);
		     }
		});
	}
	
	@FXML
	public void creditEnter(MouseEvent event) {
		credit.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 credit.setImage(c11);
		     }
		});
		credit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(sceneC);
		    	 stage.show();
		     }
		});
	}
	
	@FXML
	public void creditExit(MouseEvent event) {
		credit.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 credit.setImage(c);
		     }
		});
	}

	public void setSelfScene(Scene scene1) {
		this.selfScene = scene1;
	}


	public void setSettingScene(Scene settingScene) {
		this.SceneSetting = settingScene;
	}
	
}
