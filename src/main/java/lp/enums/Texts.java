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
    ICON_IMAGE("images/skoda_image.png");

    private final String text;

    Texts(String text) {
        this.text = text;
    }
}
