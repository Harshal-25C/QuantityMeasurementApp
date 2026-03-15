package com.quantity.repository;

import java.util.List;
import com.quantity.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

    void saveMeasurement(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> findAll();

}