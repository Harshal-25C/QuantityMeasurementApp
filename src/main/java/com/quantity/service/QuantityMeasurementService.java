package com.quantity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantity.core.LengthUnit;
import com.quantity.core.Quantity;
import com.quantity.core.TemperatureUnit;
import com.quantity.core.VolumeUnit;
import com.quantity.core.WeightUnit;

import com.quantity.dto.QuantityDTO;
import com.quantity.model.QuantityMeasurementEntity;
import com.quantity.repository.QuantityMeasurementRepository;

@Service
public class QuantityMeasurementService implements IQuantityMeasurementService {

    private final QuantityMeasurementRepository repository;

    @Autowired
    public QuantityMeasurementService(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // Convert DTO -> Quantity object
    private Quantity<?> createQuantity(QuantityDTO dto) {

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

        throw new IllegalArgumentException("Unsupported Unit: " + dto.getUnit());
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

        Quantity<?> result = ((Quantity) quantity1).add((Quantity) quantity2);

        repository.save(
                new QuantityMeasurementEntity(
                        null,
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

        Quantity<?> result = ((Quantity) quantity1).subtract((Quantity) quantity2);

        repository.save(
                new QuantityMeasurementEntity(
                        null,
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

        repository.save(
                new QuantityMeasurementEntity(
                        null,
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