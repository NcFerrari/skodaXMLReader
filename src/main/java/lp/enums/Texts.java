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
    INFO_PANE("info-pane"),
    INFO_LABELS("info-labels"),
    INSERT_XML("Přetáhněte XML soubor(y) do tohoto pole"),
    FILE_NOT_EXISTS("Soubor neexistuje!\n"),
    FILE_TEXT("Soubor "),
    FILE_WITHOUT_SUFFIX(" nemá žádnou příponu!\n"),
    FILE_SUFFIX("Přípona souboru "),
    NOT_XML(" není XML!\n"),
    COMMA(", "),
    LOADED_XML("Načtena tato XMLka: "),
    DELETE_INFO("Pro odstranění XMLka ze seznamu klikněte na dané XML a zmačkněte klávesu delete"),
    ADD_INFO("Pokud chcete přidat další XMLko, tak stačí přetáhnout XMLko do seznamu vlevo");

    private final String text;

    Texts(String text) {
        this.text = text;
    }
}
