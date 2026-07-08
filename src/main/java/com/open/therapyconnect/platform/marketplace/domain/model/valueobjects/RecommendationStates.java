package com.open.therapyconnect.platform.marketplace.domain.model.valueobjects;

import java.util.Arrays;

public enum RecommendationStates {
    IN_PROGRESS(1),
    IMPLEMENTED(2),
    NOT_IMPLEMENTED(3);

    private final int id;

    RecommendationStates(int id) { this.id = id; }

    public int getId() { return id; }

    public static RecommendationStates fromValue(int id) {
        return Arrays.stream(RecommendationStates.values())
                .filter(dt -> dt.id == id)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[RecommendationStates] Invalid value for RecommendationStates: " + id));
    }
    public static RecommendationStates fromString(String name) {
        return  Arrays.stream(RecommendationStates.values())
                .filter(dt -> dt.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[RecommendationStates] Invalid string for RecommendationStates: " + name));
    }
}
