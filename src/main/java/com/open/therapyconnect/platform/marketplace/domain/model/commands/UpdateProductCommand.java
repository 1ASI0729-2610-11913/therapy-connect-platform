package com.open.therapyconnect.platform.marketplace.domain.model.commands;

public record UpdateProductCommand(
        Long productId,
        String name,
        String description,
        Double price,
        String imageUrl,
        String recommendedFor
) {}
