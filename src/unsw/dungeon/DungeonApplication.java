package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        MenuPageController menuController = new MenuPageController();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("MenuPage.fxml"));
        loader1.setController(menuController);
        Parent root1 = loader1.load();
        Scene scene1 = new Scene(root1);     
        
        CreditController creditController = new CreditController();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("credit.fxml"));
        loader2.setController(creditController);
        Parent root2 = loader2.load();
        Scene creditScene = new Scene(root2);    
        
        SelectionController selectionController = new SelectionController();
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("selection.fxml"));
        loader3.setController(selectionController);
        Parent root3 = loader3.load();
        

        SettingController settingController = new SettingController(selectionController);
        settingController.setStage(primaryStage);
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("setting.fxml"));
        loader5.setController(settingController);
        Parent root5 = loader5.load();
        Scene settingScene = new Scene(root5); 

        Scene selectScene = new Scene(root3);    
        
        settingController.setMenuScene(scene1);

        menuController.setStage(primaryStage);
        menuController.setCreditScene(creditScene);
        menuController.setSingleScene(selectScene);
        menuController.setSettingScene(settingScene);
        menuController.setSelfScene(scene1);
        
        creditController.setStage(primaryStage);
        creditController.setMenuScene(scene1);
        
        selectionController.setMenuPage(scene1);
        selectionController.setStage(primaryStage);
        
        primaryStage.setScene(scene1);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
