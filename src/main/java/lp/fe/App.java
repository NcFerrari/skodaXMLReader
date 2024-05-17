package lp.fe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.enums.Texts;
import org.apache.logging.log4j.Logger;

public class App extends Application {

    private final LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
    private final Logger log = loggerService.getLog();
    private final Manager manager = Manager.getInstance();
    private Pane mainPane;

    @Override
    public void start(Stage stage) {
        log.debug(Texts.FX_INIT.getText());
        mainPane = new Pane();
        Scene scene = new Scene(mainPane, manager.getWindowWidth(), manager.getWindowHeight());
        loadCss(scene);
        stage.setScene(scene);
        stage.getIcons().add(new Image(manager.getIconImage()));
        stage.setTitle(Texts.APPLICATION_NAME.getText());
        stage.show();

        addLogo();
    }

    private void addLogo() {
        log.debug(Texts.ADDING_LOGO.getText());
        ImageView logoView = new ImageView();
        logoView.setFitWidth((double) manager.getWindowWidth() / 4);
        logoView.setFitHeight((double) manager.getWindowHeight() / 6);
        logoView.setX(manager.getWindowWidth() - logoView.getFitWidth());
        logoView.setY(manager.getWindowHeight() - logoView.getFitHeight());
        logoView.setImage(new Image(manager.getIconImage()));
        mainPane.getChildren().add(logoView);
    }

    private void loadCss(Scene scene) {
        scene.getStylesheets().add(Texts.CSS_BASIC.getText());
    }
}
