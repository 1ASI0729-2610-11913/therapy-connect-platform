package com.open.therapyconnect.platform.marketplace.domain.model.commands;

public record CreateProductCommand(
        Long catalogId,
        String name,
        String description,
        Double price,
        String imageUrl,
        String recommendedFor
) {}
