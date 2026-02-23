package com.quantity.service;

import com.quantity.domain.length.Length;
import com.quantity.domain.length.LengthUnit;

public class UnitConversionService {

    public Length convert(Length source, LengthUnit targetUnit) {

        if (source == null || targetUnit == null) {
            throw new IllegalArgumentException("Source and target unit cannot be null");
        }

        //Step 1: Convert source to base (inches)
        double baseValue = source.getUnit()
                                 .toBase(source.getValue());

        //Step 2: Convert base to target unit
        double convertedValue = targetUnit.fromBase(baseValue);

        return new Length(round(convertedValue), targetUnit);
    }

    private double round(double value) {
        return Math.round(value * 100.0)/100.0;
    }
}