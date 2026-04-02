package com.quantity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantity.core.IMeasurable;
import com.quantity.core.LengthUnit;
import com.quantity.core.Quantity;
import com.quantity.core.TemperatureUnit;
import com.quantity.core.VolumeUnit;
import com.quantity.core.WeightUnit;

import com.quantity.dto.QuantityDTO;
import com.quantity.dto.QuantityInputDTO;
import com.quantity.dto.QuantityMeasurementDTO;
import com.quantity.exception.QuantityMeasurementException;
import com.quantity.model.QuantityMeasurementEntity;
import com.quantity.repository.QuantityMeasurementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    // ===== Convert DTO -> Quantity object =====

    private Quantity<?> convertDtoToModel(QuantityDTO dto) {

        try {
            LengthUnit unit = LengthUnit.valueOf(dto.getUnit());
            return new Quantity<>(dto.getValue(), unit);
        } catch (Exception ignored) {}

        try {
            WeightUnit unit = WeightUnit.valueOf(dto.getUnit());
            return new Quantity<>(dto.getValue(), unit);
        } catch (Exception ignored) {}

        try {
            VolumeUnit unit = VolumeUnit.valueOf(dto.getUnit());
            return new Quantity<>(dto.getValue(), unit);
        } catch (Exception ignored) {}

        try {
            TemperatureUnit unit = TemperatureUnit.valueOf(dto.getUnit());
            return new Quantity<>(dto.getValue(), unit);
        } catch (Exception ignored) {}

        throw new QuantityMeasurementException("Unsupported Unit: " + dto.getUnit());
    }

    // ===== Get measurement type name =====

    private String getMeasurementType(Quantity<?> quantity) {
        IMeasurable unit = quantity.getUnit();
        if (unit instanceof LengthUnit)      return "LengthUnit";
        if (unit instanceof WeightUnit)      return "WeightUnit";
        if (unit instanceof VolumeUnit)      return "VolumeUnit";
        if (unit instanceof TemperatureUnit) return "TemperatureUnit";
        return "Unknown";
    }

    // ===== Build base DTO from input =====

    private QuantityMeasurementDTO buildBaseDTO(QuantityInputDTO input,
                                                 Quantity<?> q1,
                                                 Quantity<?> q2,
                                                 String operation) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(input.getQuantity1().getValue());
        dto.setThisUnit(input.getQuantity1().getUnit());
        dto.setThisMeasurementType(getMeasurementType(q1));
        dto.setThatValue(input.getQuantity2().getValue());
        dto.setThatUnit(input.getQuantity2().getUnit());
        dto.setThatMeasurementType(getMeasurementType(q2));
        dto.setOperation(operation);
        return dto;
    }

    // ===== Save to repository =====

    private void saveToRepository(QuantityMeasurementDTO dto) {
        repository.save(dto.toEntity());
    }

    // ================= COMPARE =================

    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getQuantity1());
        Quantity<?> q2 = convertDtoToModel(input.getQuantity2());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "compare");

        try {
            boolean result = q1.equals(q2);
            dto.setResultString(String.valueOf(result));
            dto.setError(false);
        } catch (Exception e) {
            dto.setErrorMessage(e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= CONVERT =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO convert(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getQuantity1());
        Quantity<?> q2 = convertDtoToModel(input.getQuantity2());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "convert");

        try {

            IMeasurable unit = q1.getUnit();

            if (unit instanceof LengthUnit) {
            	
                LengthUnit target = LengthUnit.valueOf(input.getQuantity2().getUnit());
                Quantity<LengthUnit> result = ((Quantity<LengthUnit>) q1).convertTo(target);
                dto.setResultValue(result.getValue());
                dto.setResultUnit(result.getUnit().getUnitName());
                dto.setResultMeasurementType("LengthUnit");

            } else if (unit instanceof WeightUnit) {
                WeightUnit target = WeightUnit.valueOf(input.getQuantity2().getUnit());
                Quantity<WeightUnit> result = ((Quantity<WeightUnit>) q1).convertTo(target);
                dto.setResultValue(result.getValue());
                dto.setResultUnit(result.getUnit().getUnitName());
                dto.setResultMeasurementType("WeightUnit");

            } else if (unit instanceof VolumeUnit) {
                VolumeUnit target = VolumeUnit.valueOf(input.getQuantity2().getUnit());
                Quantity<VolumeUnit> result = ((Quantity<VolumeUnit>) q1).convertTo(target);
                dto.setResultValue(result.getValue());
                dto.setResultUnit(result.getUnit().getUnitName());
                dto.setResultMeasurementType("VolumeUnit");

            } else if (unit instanceof TemperatureUnit) {
                TemperatureUnit target = TemperatureUnit.valueOf(input.getQuantity2().getUnit());
                Quantity<TemperatureUnit> result = ((Quantity<TemperatureUnit>) q1).convertTo(target);
                dto.setResultValue(result.getValue());
                dto.setResultUnit(result.getUnit().getUnitName());
                dto.setResultMeasurementType("TemperatureUnit");
            }

            dto.setError(false);

        } catch (Exception e) {
            dto.setErrorMessage(e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= ADD =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO add(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getQuantity1());
        Quantity<?> q2 = convertDtoToModel(input.getQuantity2());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "add");

        try {

            if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
                throw new QuantityMeasurementException(
                    "Cannot perform arithmetic between different measurement categories: "
                    + getMeasurementType(q1) + " and " + getMeasurementType(q2)
                );
            }

            Quantity result = ((Quantity) q1).add((Quantity) q2);

            dto.setResultValue(result.getValue());
            dto.setResultUnit(result.getUnit().getUnitName());
            dto.setResultMeasurementType(getMeasurementType(q1));
            dto.setError(false);

        } catch (QuantityMeasurementException e) {
            dto.setErrorMessage("add Error: " + e.getMessage());
            dto.setError(true);
        } catch (Exception e) {
            dto.setErrorMessage("add Error: " + e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= SUBTRACT =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO subtract(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getQuantity1());
        Quantity<?> q2 = convertDtoToModel(input.getQuantity2());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "subtract");

        try {

            if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
                throw new QuantityMeasurementException(
                    "Cannot perform arithmetic between different measurement categories: "
                    + getMeasurementType(q1) + " and " + getMeasurementType(q2)
                );
            }

            Quantity result = ((Quantity) q1).subtract((Quantity) q2);

            dto.setResultValue(result.getValue());
            dto.setResultUnit(result.getUnit().getUnitName());
            dto.setResultMeasurementType(getMeasurementType(q1));
            dto.setError(false);

        } catch (QuantityMeasurementException e) {
            dto.setErrorMessage("subtract Error: " + e.getMessage());
            dto.setError(true);
        } catch (Exception e) {
            dto.setErrorMessage("subtract Error: " + e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= DIVIDE =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO divide(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getQuantity1());
        Quantity<?> q2 = convertDtoToModel(input.getQuantity2());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "divide");

        try {

            if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
                throw new QuantityMeasurementException(
                    "Cannot perform arithmetic between different measurement categories: "
                    + getMeasurementType(q1) + " and " + getMeasurementType(q2)
                );
            }

            double result = ((Quantity) q1).divide((Quantity) q2);
            dto.setResultValue(result);
            dto.setResultMeasurementType(getMeasurementType(q1));
            dto.setError(false);

        } catch (QuantityMeasurementException e) {
            dto.setErrorMessage("Divide by zero");
            dto.setError(true);
        } catch (ArithmeticException e) {
            dto.setErrorMessage("Divide by zero");
            dto.setError(true);
        } catch (Exception e) {
            dto.setErrorMessage("divide Error: " + e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= HISTORY & REPORTING =================

    @Override
    public List<QuantityMeasurementDTO> getHistoryByOperation(String operation) {
        List<QuantityMeasurementEntity> entities =
                repository.findByOperation(operation.toLowerCase());
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType) {
        List<QuantityMeasurementEntity> entities =
                repository.findByThisMeasurementType(measurementType);
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toLowerCase());
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        List<QuantityMeasurementEntity> entities = repository.findByIsErrorTrue();
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

}