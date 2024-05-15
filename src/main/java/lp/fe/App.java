package lp.fe;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lp.Manager;

public class App extends Application {

    private final Manager manager = Manager.getInstance();

    @Override
    public void start(Stage stage) {
        Group group = new Group();
        Scene scene = new Scene(group, manager.getWindowWidth(), manager.getWindowHeight());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
