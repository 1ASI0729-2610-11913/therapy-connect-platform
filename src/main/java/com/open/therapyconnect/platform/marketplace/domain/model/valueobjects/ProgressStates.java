package com.open.therapyconnect.platform.marketplace.domain.model.valueobjects;

import java.util.Arrays;

public enum ProgressStates {
    TO_START(1),
    IN_PROGRESS(2),
    ON_PAUSE(3);

    private final int id;

    ProgressStates(int id) { this.id = id; }

    public int getId() { return id; }

    public static ProgressStates fromValue(int id) {
        return Arrays.stream(ProgressStates.values())
                .filter(dt -> dt.id == id)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ProgressStates] Invalid value for ProgressStates: " + id));
    }
    public static ProgressStates fromString(String name) {
        return  Arrays.stream(ProgressStates.values())
                .filter(dt -> dt.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ProgressStates] Invalid string for ProgressStates: " + name));
    }
}
