package com.quantity.unit;

import com.quantity.domain.IMeasurable;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        if(!Double.isFinite(baseValue))
            throw new IllegalArgumentException("Invalid numeric value");
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}