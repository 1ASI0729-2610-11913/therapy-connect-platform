package com.open.therapyconnect.platform.marketplace.domain.model.commands;

public record UpdateCatalogCommand(
        Long catalogId,
        String name,
        String description
) {}
