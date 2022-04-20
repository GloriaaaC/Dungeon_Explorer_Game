package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class MissionPageController extends PageController {

    @FXML
    private Label missionField;

    @FXML
    private Label storyField;
    
    
    private String story;
    
    private String mission;
    
    public MissionPageController() {
    	super();
    	story = "Story:\n    Bravo!!! Try to break this Dungeon \nby achieveing the following missions!!";
    	mission = "";
    }
    
    public void setStroy(String s) {
    	this.story = s;
    }
    
    public void setMission(String s) {
    	mission = "Misson:\n";
    	mission += s;
    }
    
    public void appendMission(String s) {
    	mission = mission + s;
    }
    
    
    @FXML
    public void initialize() {
    	missionField.setText(mission);
		Font myFont = new Font("Serif", 12);
		storyField.setFont(myFont);
		storyField.setStyle("-fx-text-fill: white;");
    	storyField.setText(story);
    	Font myFont2 = new Font("Serif", 15);
    	missionField.setFont(myFont2);
    	missionField.setStyle("-fx-text-fill: white;");
    	missionField.setText(mission);
    }

}
