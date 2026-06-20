package com.open.therapyconnect.platform.marketplace.domain.model.aggregates;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateProductCommand;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.*;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends AbstractDomainAggregateRoot {
    private Long id;
    private String productName;
    private String productCategory;
    private String productType;
    private AvailabilityStates availabilityState;
    private Number availableQuantity;
    private RecommendationStates recommendationState;
    private Priority priority;
    private String expirationDate;
    private String groupType;
    private Double price;

    public Product() {}

    public Product(CreateProductCommand command) {
        this.productName = command.productName();
        this.productCategory = command.productCategory();
        this.productType = command.productType();
        this.availabilityState = AvailabilityStates.valueOf(command.availabilityState());
        this.availableQuantity = command.availableQuantity();
        this.recommendationState = RecommendationStates.valueOf(command.recommendationState());
        this.priority = Priority.valueOf(command.priority());
        this.expirationDate = command.expirationDate();
        this.groupType = command.groupType();
        this.price = command.price();
    }
}
