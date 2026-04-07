package com.quantity.service;

import com.quantity.dto.QuantityInputDTO;
import com.quantity.dto.QuantityMeasurementDTO;

import java.util.List;

public interface IQuantityMeasurementService {

	QuantityMeasurementDTO compare(QuantityInputDTO input);

	QuantityMeasurementDTO convert(QuantityInputDTO input);

	QuantityMeasurementDTO add(QuantityInputDTO input);

	QuantityMeasurementDTO subtract(QuantityInputDTO input);

	QuantityMeasurementDTO divide(QuantityInputDTO input);

	QuantityMeasurementDTO multiply(QuantityInputDTO input);

	List<QuantityMeasurementDTO> getHistoryByOperation(String operation);

	List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType);

	long getOperationCount(String operation);

	List<QuantityMeasurementDTO> getErrorHistory();
}