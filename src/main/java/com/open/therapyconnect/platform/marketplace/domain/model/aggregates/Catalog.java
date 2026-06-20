package com.open.therapyconnect.platform.marketplace.domain.model.aggregates;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateCatalogCommand;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.*;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Catalog extends AbstractDomainAggregateRoot {
    private Long id;
    private String catalogName;
    private ProductId productId;
    private CatalogStates catalogState;
    private String dateUpdated;

    public Catalog() {}

    public Catalog(CreateCatalogCommand command) {
        this.catalogName = command.catalogName();
        this.productId = new ProductId(command.productId());
        this.catalogState = CatalogStates.valueOf(command.catalogState());
        this.dateUpdated = command.dateUpdated();
    }
}
