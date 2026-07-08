package com.open.therapyconnect.platform.marketplace.domain.model.valueobjects;

import java.util.Arrays;

public enum CatalogStates {
    ACTIVE(1),
    PRIVATE(2),
    FILED(3);

    private final int id;

    CatalogStates(int id) { this.id = id; }

    public int getId() { return id; }

    public static CatalogStates fromValue(int id) {
        return Arrays.stream(CatalogStates.values())
                .filter(dt -> dt.id == id)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[CatalogStates] Invalid value for CatalogStates: " + id));
    }
    public static CatalogStates fromString(String name) {
        return  Arrays.stream(CatalogStates.values())
                .filter(dt -> dt.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[CatalogStates] Invalid string for CatalogStates: " + name));
    }
}
