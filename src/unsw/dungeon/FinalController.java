package unsw.dungeon;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FinalController extends PageController {


    @FXML
    private ImageView exit;
    
	private Image e;
	private Scene menuScene;
	
	public FinalController() {
		e = new Image("/exitname.png");
	}
	
	@FXML
	public void initialize() {
		exit.setImage(e);
	}
	
	@FXML
	public void exitHandler(MouseEvent event) {
		exit.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 exit.setOpacity(0.5);
		     }
		});
		exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(menuScene);
		    	 stage.show();
		    	 stage.setWidth(535);
		    	 stage.setHeight(420);

		     }
		});
		exit.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 exit.setOpacity(1);
		     }
		});
	}

	public void setMenuPag(Scene scene1) {
		this.menuScene = scene1;
	}
	

}

