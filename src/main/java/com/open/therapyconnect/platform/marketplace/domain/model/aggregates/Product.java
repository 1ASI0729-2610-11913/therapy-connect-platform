package com.open.therapyconnect.platform.marketplace.domain.model.aggregates;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateProductCommand;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Product aggregate root.
 */
@Getter
public class Product extends AbstractDomainAggregateRoot<Product> {

    @Setter private Long id;
    @Setter private Long catalogId;
    @Setter private String name;
    @Setter private String description;
    @Setter private Double price;
    @Setter private String imageUrl;
    @Setter private String recommendedFor;

    public Product() {
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.price = 0.0;
        this.imageUrl = Strings.EMPTY;
        this.recommendedFor = Strings.EMPTY;
    }

    public Product(CreateProductCommand command) {
        this.catalogId = command.catalogId();
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.imageUrl = command.imageUrl();
        this.recommendedFor = command.recommendedFor();
    }

    public Product updateInformation(String name, String description, Double price,
                                     String imageUrl, String recommendedFor) {
        if (name != null) this.name = name;
        if (description != null) this.description = description;
        if (price != null) this.price = price;
        if (imageUrl != null) this.imageUrl = imageUrl;
        if (recommendedFor != null) this.recommendedFor = recommendedFor;
        return this;
    }
}
