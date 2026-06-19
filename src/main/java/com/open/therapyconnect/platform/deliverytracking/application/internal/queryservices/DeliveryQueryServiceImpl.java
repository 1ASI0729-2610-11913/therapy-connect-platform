package com.open.therapyconnect.platform.deliverytracking.application.internal.queryservices;

import com.open.therapyconnect.platform.deliverytracking.application.queryservices.DeliveryQueryService;
import com.open.therapyconnect.platform.deliverytracking.domain.model.aggregates.Delivery;
import com.open.therapyconnect.platform.deliverytracking.domain.model.queries.GetDeliveriesByDateAndStatusQuery;
import com.open.therapyconnect.platform.deliverytracking.domain.model.queries.GetDeliveryByIdQuery;
import com.open.therapyconnect.platform.deliverytracking.domain.repositories.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryQueryServiceImpl implements DeliveryQueryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryQueryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Optional<Delivery> handle(GetDeliveryByIdQuery query) {
        return deliveryRepository.findById(query.id());
    }

    @Override
    public List<Delivery> handle(GetDeliveriesByDateAndStatusQuery query) {
        return deliveryRepository.findByDeliveryDateAndStatus(query.date(), query.status());
    }
}
