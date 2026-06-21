package com.open.therapyconnect.platform.marketplace.domain.model.commands;

public record CreateCatalogCommand(
        String name,
        String description
) {}
