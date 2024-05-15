package lp.enums;

import lombok.Getter;

@Getter
public enum Texts {

    APPLICATION_STARTED("application started"),
    WINDOW_WIDTH("window.width"),
    WINDOW_HEIGHT("window.height"),
    CONFIG_FILE_NAME("config.properties");

    private final String text;

    Texts(String text) {
        this.text = text;
    }
}
