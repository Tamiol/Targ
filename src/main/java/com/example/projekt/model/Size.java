package com.example.projekt.model;

public enum Size {
    SMALL("small"),
    MEDIUM("medium"),
    HUGE("huge");

    private final String displayName;

    Size(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}