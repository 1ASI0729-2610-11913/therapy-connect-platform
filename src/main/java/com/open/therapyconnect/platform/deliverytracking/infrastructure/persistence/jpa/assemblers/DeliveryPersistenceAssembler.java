package com.open.therapyconnect.platform.deliverytracking.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.deliverytracking.domain.model.aggregates.Delivery;
import com.open.therapyconnect.platform.deliverytracking.infrastructure.persistence.jpa.entities.DeliveryPersistenceEntity;

public final class DeliveryPersistenceAssembler {

    private DeliveryPersistenceAssembler() {}

    public static Delivery toDomainFromPersistence(DeliveryPersistenceEntity entity) {
        if (entity == null) return null;
        var delivery = new Delivery();
        delivery.setId(entity.getId());
        delivery.setStatus(entity.getStatus());
        delivery.setResponsibleId(entity.getResponsibleId());
        delivery.setResponsibleName(entity.getResponsibleName());
        delivery.setVehiclePlate(entity.getVehiclePlate());
        delivery.setVehicleType(entity.getVehicleType());
        delivery.setVehicleBrand(entity.getVehicleBrand());
        delivery.setItemCount(entity.getItemCount());
        delivery.setScheduledTime(entity.getScheduledTime());
        delivery.setDeliveryDate(entity.getDeliveryDate());
        return delivery;
    }

    public static DeliveryPersistenceEntity toPersistenceFromDomain(Delivery delivery) {
        if (delivery == null) return null;
        var entity = new DeliveryPersistenceEntity();
        if (delivery.getId() != null) entity.setId(delivery.getId());
        entity.setStatus(delivery.getStatus());
        entity.setResponsibleId(delivery.getResponsibleId());
        entity.setResponsibleName(delivery.getResponsibleName());
        entity.setVehiclePlate(delivery.getVehiclePlate());
        entity.setVehicleType(delivery.getVehicleType());
        entity.setVehicleBrand(delivery.getVehicleBrand());
        entity.setItemCount(delivery.getItemCount());
        entity.setScheduledTime(delivery.getScheduledTime());
        entity.setDeliveryDate(delivery.getDeliveryDate());
        return entity;
    }
}
