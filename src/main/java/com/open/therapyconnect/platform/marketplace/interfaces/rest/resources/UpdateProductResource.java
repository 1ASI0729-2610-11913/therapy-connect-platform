package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

public record UpdateProductResource(
        String name,
        String description,
        Double price,
        String imageUrl,
        String recommendedFor
) {}
