package App;

import App.Logica.AppObs;
import App.Logica.Data.AppData;
import App.UI.Gui.GUIApp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main  extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AppObs appObs = new AppObs();
        GUIApp GUI = new GUIApp(appObs);
        stage.setTitle("Room4You");
        stage.setScene(new Scene(GUI.obtemRootPane(), 1000, 650));
        stage.setResizable(false);
        stage.show();
    }
}
