package com.open.therapyconnect.platform.marketplace.domain.model.valueobjects;

import java.util.Arrays;

public enum AvailabilityStates {
    IN_STOCK(1),
    STOCK_ON_DEMAND(2),
    OUT_OF_STOCK(3);

    private final int id;

    AvailabilityStates(int id) { this.id = id; }

    public int getId() { return id; }

    public static AvailabilityStates fromValue(int id) {
        return Arrays.stream(AvailabilityStates.values())
                .filter(dt -> dt.id == id)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[AvailabilityStates] Invalid value for AvailabilityStates: " + id));
    }
    public static AvailabilityStates fromString(String name) {
        return  Arrays.stream(AvailabilityStates.values())
                .filter(dt -> dt.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[AvailabilityStates] Invalid string for AvailabilityStates: " + name));
    }
}
