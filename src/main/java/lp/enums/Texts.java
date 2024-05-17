package lp.enums;

import lombok.Getter;

@Getter
public enum Texts {

    APPLICATION_STARTED("application started"),
    APPLICATION_NAME("XML loader"),
    WINDOW_WIDTH("window.width"),
    WINDOW_HEIGHT("window.height"),
    CONFIG_FILE_NAME("config.properties"),
    CSS_BASIC("css/basicFrame.css"),
    ICON_IMAGE("images/skoda_image.png"),
    FX_INIT("init FX"),
    ADDING_LOGO("added LOGO"),
    DRAGGED_PANE("dragged-pane"),
    INSERT_XML("Přetáhněte XML soubor(y) do tohoto pole"),
    FILE_NOT_EXISTS("Soubor neexistuje!\n"),
    FILE_TEXT("Soubor "),
    FILE_WITHOUT_SUFFIX(" nemá žádnou příponu!\n"),
    FILE_SUFFIX("Přípona souboru "),
    NOT_XML(" není XML!\n");

    private final String text;

    Texts(String text) {
        this.text = text;
    }
}
