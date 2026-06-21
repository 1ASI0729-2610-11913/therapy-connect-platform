package com.open.therapyconnect.platform.marketplace.domain.model.aggregates;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateCatalogCommand;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Catalog aggregate root.
 */
@Getter
public class Catalog extends AbstractDomainAggregateRoot<Catalog> {

    @Setter private Long id;
    @Setter private String name;
    @Setter private String description;

    public Catalog() {
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
    }

    public Catalog(CreateCatalogCommand command) {
        this.name = command.name();
        this.description = command.description();
    }

    public Catalog updateInformation(String name, String description) {
        if (name != null) this.name = name;
        if (description != null) this.description = description;
        return this;
    }
}
