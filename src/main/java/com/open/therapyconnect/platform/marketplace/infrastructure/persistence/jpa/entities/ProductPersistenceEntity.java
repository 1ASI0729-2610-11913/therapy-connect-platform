package com.open.therapyconnect.platform.marketplace.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.AvailabilityStates;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.Priority;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.RecommendationStates;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_category", nullable = false)
    private String productCategory;

    @Column(name = "product_type", nullable = false)
    private String productType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "availability_state", nullable = false)
    private AvailabilityStates availabilityState;

    @Column(name = "available_quantity", nullable = false)
    private Number availableQuantity;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "recommendation_state", nullable = false)
    private RecommendationStates recommendationState;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "priority", nullable = false)
    private Priority priority;

    @Column(name = "expiration_date", nullable = false)
    private String expirationDate;

    @Column(name = "group_type", nullable = false)
    private String groupType;

    @Column(name = "product_price", nullable = false)
    private Double price;
}
