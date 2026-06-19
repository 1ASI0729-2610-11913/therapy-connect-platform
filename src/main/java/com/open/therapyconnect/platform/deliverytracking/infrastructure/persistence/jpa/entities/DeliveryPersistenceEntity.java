package com.open.therapyconnect.platform.deliverytracking.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.deliverytracking.domain.model.valueobjects.DeliveryStatus;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @Column(nullable = false)
    private Long responsibleId;

    @Column(nullable = false)
    private String responsibleName;

    @Column(nullable = false)
    private String vehiclePlate;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private String vehicleBrand;

    @Column(nullable = false)
    private Integer itemCount;

    @Column(nullable = false)
    private String scheduledTime;

    @Column(nullable = false)
    private String deliveryDate;
}
