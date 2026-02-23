package com.quantity.unit;

import com.quantity.domain.IMeasurable;

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0, "Litre"),
    MILLILITRE(0.001, "Millilitre"),
    GALLON(3.78541, "Gallon");

    private final double conversionFactor;
    private final String unitName;

    VolumeUnit(double conversionFactor, String unitName) {
        this.conversionFactor = conversionFactor;
        this.unitName = unitName;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return unitName;
    }
}