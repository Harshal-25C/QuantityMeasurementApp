package com.quantity.service;

import com.quantity.dto.QuantityDTO;
import com.quantity.entity.QuantityMeasurementEntity;
import com.quantity.repository.IQuantityMeasurementRepository;
import com.quantity.unit.LengthUnit;
import com.quantity.unit.Quantity;
import com.quantity.unit.TemperatureUnit;
import com.quantity.unit.VolumeUnit;
import com.quantity.unit.WeightUnit;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // Convert DTO -> Quantity object
    private Quantity<?> createQuantity(QuantityDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("QuantityDTO cannot be null");
        }

        if (dto.getUnit() == null || dto.getUnit().trim().isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be null or empty");
        }

        String unitName = dto.getUnit().trim().toUpperCase();

        try {
            LengthUnit unit = LengthUnit.valueOf(unitName);
            return new Quantity<LengthUnit>(dto.getValue(), unit);
        } catch (IllegalArgumentException ignored) {}

        try {
            WeightUnit unit = WeightUnit.valueOf(unitName);
            return new Quantity<WeightUnit>(dto.getValue(), unit);
        } catch (IllegalArgumentException ignored) {}

        try {
            VolumeUnit unit = VolumeUnit.valueOf(unitName);
            return new Quantity<VolumeUnit>(dto.getValue(), unit);
        } catch (IllegalArgumentException ignored) {}

        try {
            TemperatureUnit unit = TemperatureUnit.valueOf(unitName);
            return new Quantity<TemperatureUnit>(dto.getValue(), unit);
        } catch (IllegalArgumentException ignored) {}

        throw new IllegalArgumentException("Unsupported Unit: " + unitName);
    }

    // ================= COMPARE =================
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<?> quantity1 = createQuantity(q1);
        Quantity<?> quantity2 = createQuantity(q2);

        return quantity1.equals(quantity2);
    }

    // ================= CONVERT =================

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {

        Quantity<?> quantity = createQuantity(input);

        Object unit = quantity.getUnit();

        if (unit instanceof LengthUnit) {

            LengthUnit target = LengthUnit.valueOf(targetUnit);
            Quantity<LengthUnit> result =
                    ((Quantity<LengthUnit>) quantity).convertTo(target);

            return new QuantityDTO(result.getValue(), result.getUnit().name());
        }

        if (unit instanceof WeightUnit) {

            WeightUnit target = WeightUnit.valueOf(targetUnit);
            Quantity<WeightUnit> result =
                    ((Quantity<WeightUnit>) quantity).convertTo(target);

            return new QuantityDTO(result.getValue(), result.getUnit().name());
        }

        if (unit instanceof VolumeUnit) {

            VolumeUnit target = VolumeUnit.valueOf(targetUnit);
            Quantity<VolumeUnit> result =
                    ((Quantity<VolumeUnit>) quantity).convertTo(target);

            return new QuantityDTO(result.getValue(), result.getUnit().name());
        }

        if (unit instanceof TemperatureUnit) {

            TemperatureUnit target = TemperatureUnit.valueOf(targetUnit);
            Quantity<TemperatureUnit> result =
                    ((Quantity<TemperatureUnit>) quantity).convertTo(target);

            return new QuantityDTO(result.getValue(), result.getUnit().name());
        }

        throw new IllegalArgumentException("Unsupported unit type");
    }

    // ================= ADD =================

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        Quantity<?> quantity1 = createQuantity(q1);
        Quantity<?> quantity2 = createQuantity(q2);

        Quantity result = ((Quantity) quantity1).add((Quantity) quantity2);

        repository.saveMeasurement(
                new QuantityMeasurementEntity(
                        "ADD",
                        quantity1.toString(),
                        quantity2.toString(),
                        result.toString(),
                        null
                )
        );

        return new QuantityDTO(result.getValue(), result.getUnit().toString());
    }

    // ================= SUBTRACT =================

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        Quantity<?> quantity1 = createQuantity(q1);
        Quantity<?> quantity2 = createQuantity(q2);

        Quantity result = ((Quantity) quantity1).subtract((Quantity) quantity2);

        repository.saveMeasurement(
                new QuantityMeasurementEntity(
                        "SUBTRACT",
                        quantity1.toString(),
                        quantity2.toString(),
                        result.toString(),
                        null
                )
        );

        return new QuantityDTO(result.getValue(), result.getUnit().toString());
    }

    // ================= DIVIDE =================

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity<?> quantity1 = createQuantity(q1);
        Quantity<?> quantity2 = createQuantity(q2);

        double result = ((Quantity) quantity1).divide((Quantity) quantity2);

        repository.saveMeasurement(
                new QuantityMeasurementEntity(
                        "DIVIDE",
                        quantity1.toString(),
                        quantity2.toString(),
                        String.valueOf(result),
                        null
                )
        );

        return result;
    }
}
