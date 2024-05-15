package lp.fe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lp.Manager;
import lp.enums.Texts;

public class App extends Application {

    private final Manager manager = Manager.getInstance();

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, manager.getWindowWidth(), manager.getWindowHeight());
        loadCss(scene);
        stage.setScene(scene);
        stage.getIcons().add(new Image(manager.getIconImage()));
        stage.setTitle(Texts.APPLICATION_NAME.getText());
        stage.show();
    }

    private void loadCss(Scene scene) {
        scene.getStylesheets().add(Texts.CSS_BASIC.getText());
    }
}
