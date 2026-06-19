package com.open.therapyconnect.platform.deliverytracking.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.deliverytracking.domain.model.aggregates.Delivery;
import com.open.therapyconnect.platform.deliverytracking.domain.model.valueobjects.DeliveryStatus;
import com.open.therapyconnect.platform.deliverytracking.domain.repositories.DeliveryRepository;
import com.open.therapyconnect.platform.deliverytracking.infrastructure.persistence.jpa.assemblers.DeliveryPersistenceAssembler;
import com.open.therapyconnect.platform.deliverytracking.infrastructure.persistence.jpa.repositories.DeliveryPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final DeliveryPersistenceRepository persistenceRepository;

    public DeliveryRepositoryImpl(DeliveryPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<Delivery> findById(Long id) {
        return persistenceRepository.findById(id)
                .map(DeliveryPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Delivery> findByDeliveryDateAndStatus(String date, String status) {
        return persistenceRepository.findByDeliveryDateAndStatus(date, DeliveryStatus.valueOf(status.toUpperCase()))
                .stream()
                .map(DeliveryPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Delivery save(Delivery delivery) {
        var saved = persistenceRepository.save(DeliveryPersistenceAssembler.toPersistenceFromDomain(delivery));
        return DeliveryPersistenceAssembler.toDomainFromPersistence(saved);
    }
}
