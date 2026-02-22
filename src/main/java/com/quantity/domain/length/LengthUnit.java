package com.quantity.domain.length;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0);

    private final double conversionFactorToFeet;

    LengthUnit(double conversionFactorToFeet) {
        this.conversionFactorToFeet = conversionFactorToFeet;
    }

    public double toBaseUnit(double value) {
        return value * conversionFactorToFeet;
    }
}