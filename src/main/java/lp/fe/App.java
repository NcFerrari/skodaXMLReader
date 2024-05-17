package lp.fe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private Pane draggedPane;

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
        settingDragAndDropPane();
        addDragAndDropListeners();
    }

    private void settingDragAndDropPane() {
        draggedPane = new Pane();
        draggedPane.getStyleClass().add(Texts.DRAGGED_PANE.getText());
        draggedPane.setPrefSize(mainPane.getWidth() / 2, mainPane.getHeight() / 2);
        draggedPane.setLayoutX(mainPane.getWidth() / 2 - mainPane.getWidth() / 4);
        draggedPane.setLayoutY(mainPane.getHeight() / 2 - mainPane.getHeight() / 4);
        mainPane.getChildren().add(draggedPane);

        Label label = new Label(Texts.INSERT_XML.getText());
        label.setLayoutX(draggedPane.getWidth() / 2 - label.getWidth() / 2);
        label.setLayoutX(draggedPane.getHeight() / 2 - label.getHeight() / 2);
        draggedPane.getChildren().add(label);
    }

    private void addDragAndDropListeners() {

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
        Platform.runLater(() -> {
            Thread t = new Thread(() -> {
                while (true) {
                    scene.getStylesheets().remove("file:///C:/temp/temp.css");
                    scene.getStylesheets().add("file:///C:/temp/temp.css");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            });
            t.setDaemon(true);
            t.start();
        });
    }
}
