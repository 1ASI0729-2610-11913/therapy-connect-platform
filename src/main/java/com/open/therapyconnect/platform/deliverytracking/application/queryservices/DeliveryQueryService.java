package com.open.therapyconnect.platform.deliverytracking.application.queryservices;

import com.open.therapyconnect.platform.deliverytracking.domain.model.aggregates.Delivery;
import com.open.therapyconnect.platform.deliverytracking.domain.model.queries.GetDeliveriesByDateAndStatusQuery;
import com.open.therapyconnect.platform.deliverytracking.domain.model.queries.GetDeliveryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DeliveryQueryService {
    Optional<Delivery> handle(GetDeliveryByIdQuery query);
    List<Delivery> handle(GetDeliveriesByDateAndStatusQuery query);
}
