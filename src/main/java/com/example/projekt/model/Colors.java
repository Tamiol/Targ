package com.example.projekt.model;

public enum Colors {
    GREEN("green"),
    YELLOW("yellow"),
    BROWN("brown"),
    RED("red"),
    VIOLET("violet");

    private final String displayName;

    Colors(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
