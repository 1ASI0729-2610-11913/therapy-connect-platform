package com.open.therapyconnect.platform.alertmonitoring.domain.model.aggregates;

import com.open.therapyconnect.platform.alertmonitoring.domain.model.valueobjects.AlertCriticality;
import com.open.therapyconnect.platform.alertmonitoring.domain.model.valueobjects.AlertStatus;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class Alert extends AbstractDomainAggregateRoot<Alert> {

    @Setter private Long id;
    @Setter private String zone;
    @Setter private Double ppmLevel;
    @Setter private AlertCriticality criticality;
    @Setter private AlertStatus status;
    @Setter private String detectedAt;

    public Alert() {
        this.zone = Strings.EMPTY;
        this.ppmLevel = 0.0;
        this.criticality = AlertCriticality.LOW;
        this.status = AlertStatus.PENDING;
        this.detectedAt = Strings.EMPTY;
    }
}
