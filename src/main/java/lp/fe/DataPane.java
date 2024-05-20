package lp.fe;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lp.Manager;
import lp.enums.Texts;

public class DataPane extends ScrollPane {

    private final Manager manager = Manager.getInstance();
    private GridPane authorPane;
    private GridPane approvalsPane;
    private GridPane requestPane;
    private VBox vBox = new VBox();

    public DataPane() {
        super();
        getStyleClass().add(Texts.DATA_PANE.getText());
        setPrefSize(4 * manager.getWindowWidth() / 5.0, 11 * manager.getWindowHeight() / 15.0);
        setLayoutX(manager.getWindowWidth() / 5.0);
        setLayoutY(manager.getWindowHeight() / 10.0);
        vBox.setPrefSize(getPrefWidth() / 2, getPrefHeight());
        vBox.getStyleClass().add(Texts.DATA_PANE.getText());
        setContent(vBox);
    }
}
