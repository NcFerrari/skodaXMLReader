package lp.enums;

import lombok.Getter;

@Getter
public enum Texts {

    APPLICATION_STARTED("application started");

    private final String text;

    Texts(String text) {
        this.text = text;
    }
}
