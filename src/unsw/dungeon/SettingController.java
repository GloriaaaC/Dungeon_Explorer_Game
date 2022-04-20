package unsw.dungeon;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SettingController extends PageController {

    @FXML
    private ImageView easy;

    @FXML
    private ImageView hard;

    @FXML
    private ImageView medium;

    @FXML
    private ImageView back;

    @FXML
    private ImageView on;

    @FXML
    private ImageView off;
    
    private Image e;
    private Image e11;
    private Image m;
    private Image m11;
    private Image h;
    private Image h11;
    private Image o;
    private Image o11;
    private Image f;
    private Image f11;
    private SelectionController selectionController;
	private Scene MenuScene;
    
    public SettingController(SelectionController s) {
    	super();
    	e = new Image("/easy.png");
    	e11 = new Image("/easy11.png");
    	m = new Image("/medium.png");
    	m11 = new Image("/medium11.png");
    	h = new Image("/hard.png");
    	h11 = new Image("/hard11.png");
    	o = new Image("/on.png");
    	o11 = new Image("/on11.png");
    	f = new Image("/off.png");
    	f11 = new Image("/off11.png");
    	selectionController = s;
    }

    
    @FXML
    public void initialize() {
    	easy.setImage(e);
    	medium.setImage(m);
    	hard.setImage(h11);
    	on.setImage(o11);
    	off.setImage(f);
    }
    
	@FXML
	public void setEasy(MouseEvent event) {
		easy.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 easy.setImage(e11);
		    	 medium.setImage(m);
		    	 hard.setImage(h);
		    	 selectionController.setDifficulty(0);
		     }
		});
	}
	
	@FXML
	public void setMedium(MouseEvent event) {
		medium.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 easy.setImage(e);
		    	 medium.setImage(m11);
		    	 hard.setImage(h);
		    	 selectionController.setDifficulty(1);
		     }
		});
	}
	
	@FXML
	public void setHard(MouseEvent event) {
		hard.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 easy.setImage(e);
		    	 medium.setImage(m);
		    	 hard.setImage(h11);
		    	 selectionController.setDifficulty(2);
		     }
		});
	}
	
	@FXML
	public void setOn(MouseEvent event) {
		on.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 on.setImage(o11);
		    	 off.setImage(f);
		     }
		});
	}
	@FXML
	public void setOff(MouseEvent event) {
		off.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 on.setImage(o);
		    	 off.setImage(f11);
		     }
		});
	}
	
	@FXML
	public void back(MouseEvent event) {
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
		    	 stage.setScene(MenuScene);
		    	 stage.show();
		     }
		});
	}


	public void setMenuScene(Scene scene1) {
		this.MenuScene = scene1;
	}
}

