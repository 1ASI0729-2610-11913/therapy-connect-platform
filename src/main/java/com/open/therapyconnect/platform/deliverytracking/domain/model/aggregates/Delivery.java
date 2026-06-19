package com.open.therapyconnect.platform.deliverytracking.domain.model.aggregates;

import com.open.therapyconnect.platform.deliverytracking.domain.model.valueobjects.DeliveryStatus;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class Delivery extends AbstractDomainAggregateRoot<Delivery> {

    @Setter private Long id;
    @Setter private DeliveryStatus status;
    @Setter private Long responsibleId;
    @Setter private String responsibleName;
    @Setter private String vehiclePlate;
    @Setter private String vehicleType;
    @Setter private String vehicleBrand;
    @Setter private Integer itemCount;
    @Setter private String scheduledTime;
    @Setter private String deliveryDate;

    public Delivery() {
        this.status = DeliveryStatus.PENDING;
        this.responsibleName = Strings.EMPTY;
        this.vehiclePlate = Strings.EMPTY;
        this.vehicleType = Strings.EMPTY;
        this.vehicleBrand = Strings.EMPTY;
        this.scheduledTime = Strings.EMPTY;
        this.deliveryDate = Strings.EMPTY;
        this.itemCount = 0;
    }
}
