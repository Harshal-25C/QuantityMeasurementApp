package com.quantity.repository;

import com.quantity.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

    // Find all measurements by operation type
    List<QuantityMeasurementEntity> findByOperation(String operation);

    // Find all measurements by measurement type
    List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);

    // Find all measurements created after a specific date
    List<QuantityMeasurementEntity> findByCreatedAtAfter(LocalDateTime date);

    // Custom JPQL query - find successful operations by type
    @Query("SELECT q FROM QuantityMeasurementEntity q WHERE q.operation = :operation AND q.isError = false")
    List<QuantityMeasurementEntity> findSuccessfulByOperation(@Param("operation") String operation);

    // Count successful operations by type
    long countByOperationAndIsErrorFalse(String operation);

    // Find all errored measurements
    List<QuantityMeasurementEntity> findByIsErrorTrue();

}