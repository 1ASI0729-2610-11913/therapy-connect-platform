package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

public record CreateProductResource(
        Long catalogId,
        String name,
        String description,
        Double price,
        String imageUrl,
        String recommendedFor
) {}
