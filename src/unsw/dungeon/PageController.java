package unsw.dungeon;

import javafx.stage.Stage;

public abstract class PageController {

	protected Stage stage;
	
	public PageController() {
		super();
	}
	
	public void setStage(Stage s) {
		this.stage = s;
	}
}
