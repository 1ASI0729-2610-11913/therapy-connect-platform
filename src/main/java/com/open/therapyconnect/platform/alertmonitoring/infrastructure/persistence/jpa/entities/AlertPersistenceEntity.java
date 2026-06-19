package com.open.therapyconnect.platform.alertmonitoring.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.alertmonitoring.domain.model.valueobjects.AlertCriticality;
import com.open.therapyconnect.platform.alertmonitoring.domain.model.valueobjects.AlertStatus;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
public class AlertPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private String zone;

    @Column(nullable = false)
    private Double ppmLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertCriticality criticality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertStatus status;

    @Column(nullable = false)
    private String detectedAt;
}
