package unsw.dungeon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class CreditController extends PageController {

	@FXML
	private ImageView back;
	private Scene menuPage;

	public CreditController() {
		this.stage = null;
		this.menuPage = null;
	}
	
	@FXML
	public void back(MouseEvent event) {
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 stage.setScene(menuPage);
		    	 stage.show();
		     }
		});
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
	}


	public void setMenuScene(Scene scene1) {
		this.menuPage = scene1;
	}


}
