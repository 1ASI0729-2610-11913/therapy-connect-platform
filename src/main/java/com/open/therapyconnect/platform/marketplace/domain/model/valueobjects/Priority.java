package com.open.therapyconnect.platform.marketplace.domain.model.valueobjects;

import java.util.Arrays;

public enum Priority {
    HIGH(1),
    LOW(2);

    private final int id;

    Priority(int id) { this.id = id; }

    public int getId() { return id; }

    public static Priority fromValue(int id) {
        return Arrays.stream(Priority.values())
                .filter(dt -> dt.id == id)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[Priority] Invalid value for Priority: " + id));
    }
    public static Priority fromString(String name) {
        return  Arrays.stream(Priority.values())
                .filter(dt -> dt.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[Priority] Invalid string for Priority: " + name));
    }
}
