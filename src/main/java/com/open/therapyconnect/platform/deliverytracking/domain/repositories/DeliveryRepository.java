package com.open.therapyconnect.platform.deliverytracking.domain.repositories;

import com.open.therapyconnect.platform.deliverytracking.domain.model.aggregates.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository {
    Optional<Delivery> findById(Long id);
    List<Delivery> findByDeliveryDateAndStatus(String date, String status);
    Delivery save(Delivery delivery);
}
