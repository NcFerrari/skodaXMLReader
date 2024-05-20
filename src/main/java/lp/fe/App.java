package lp.fe;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.enums.Texts;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class App extends Application {

    private final LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
    private final Logger log = loggerService.getLog();
    private final Manager manager = Manager.getInstance();
    private Pane mainPane;
    private Pane draggedPane;
    private ListView<String> xmlListView;

    @Override
    public void start(Stage stage) {
        log.debug(Texts.FX_INIT.getText());
        mainPane = new Pane();
        Scene scene = new Scene(mainPane, manager.getWindowWidth(), manager.getWindowHeight());
        loadCss(scene);
        stage.setScene(scene);
        stage.getIcons().add(new Image(manager.getIconImage()));
        stage.setTitle(Texts.APPLICATION_NAME.getText());
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.show();

        addLogo();
        settingDragAndDropPane();
    }

    private void settingDragAndDropPane() {
        if (draggedPane == null) {
            draggedPane = new Pane();
            draggedPane.getStyleClass().add(Texts.DRAGGED_PANE.getText());
            draggedPane.setPrefSize(mainPane.getWidth() / 2, mainPane.getHeight() / 2);
            draggedPane.setLayoutX(mainPane.getWidth() / 2 - mainPane.getWidth() / 4);
            draggedPane.setLayoutY(mainPane.getHeight() / 2 - mainPane.getHeight() / 4);

            Label label = new Label(Texts.INSERT_XML.getText());
            label.setPrefSize(200, 300);
            label.setLayoutX(mainPane.getWidth() / 4 - label.getPrefWidth() / 2);
            label.setLayoutY(mainPane.getHeight() / 4 - label.getPrefHeight() / 2);
            label.setWrapText(true);
            setDraggedPaneListeners();
            draggedPane.getChildren().add(label);
        }
        mainPane.getChildren().add(draggedPane);
    }

    private void setDraggedPaneListeners() {
        addDragAndDropListeners(draggedPane);
        draggedPane.setOnDragExited(evt -> draggedPane.setStyle("-fx-background-color: linear-gradient(to top, #14dcb7, #618844);"));
    }

    private void addDragAndDropListeners(Region region) {
        region.setOnDragOver(evt -> {
            region.setStyle("-fx-background-color: linear-gradient(to top, #618844, #14dcb7);");
            if (evt.getGestureSource() != region && evt.getDragboard().hasFiles()) {
                evt.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            evt.consume();
        });

        region.setOnDragDropped(evt -> {
            Dragboard dragboard = evt.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                manager.addToList(dragboard.getFiles());
                success = manager.validateFiles();
            }
            evt.setDropCompleted(success);
            evt.consume();
            if (success) {
                showLoadedFiles();
                StringBuilder stringBuilder = new StringBuilder();
                manager.getListOfFiles().forEach(file -> stringBuilder.append(file).append(Texts.COMMA.getText()));
                log.debug("{}{}", Texts.LOADED_XML.getText(), stringBuilder);
            }
        });
    }

    private void showLoadedFiles() {
        mainPane.getChildren().remove(draggedPane);
        if (xmlListView == null) {
            xmlListView = new ListView<>();
            xmlListView.setPrefSize((double) manager.getWindowWidth() / 5, manager.getWindowHeight());
            addXMLListViewListener();
        }
        mainPane.getChildren().add(xmlListView);
        xmlListView.getItems().clear();
        xmlListView.getItems().addAll(manager.getListOfFiles().stream().map(File::getName).sorted().toList());
    }

    private void addXMLListViewListener() {
        xmlListView.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                manager.removeItemFromListOfFiles(xmlListView.getSelectionModel().getSelectedItem());
                xmlListView.getItems().remove(xmlListView.getSelectionModel().getSelectedItem());
                possibleSwapPanes();
            }
        });
        addDragAndDropListeners(xmlListView);
    }

    private void possibleSwapPanes() {
        if (xmlListView.getItems().isEmpty()) {
            mainPane.getChildren().remove(xmlListView);
            settingDragAndDropPane();
        }
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
//        Platform.runLater(() -> {
//            Thread t = new Thread(() -> {
//                while (true) {
//                    scene.getStylesheets().remove("file:///C:/temp/temp.css");
//                    scene.getStylesheets().add("file:///C:/temp/temp.css");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        log.error(e);
//                    }
//                }
//            });
//            t.setDaemon(true);
//            t.start();
//        });
    }
}
