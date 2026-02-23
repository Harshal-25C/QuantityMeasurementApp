package com.quantity.domain.length;

public enum LengthUnit {

    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double factorToInches;

    LengthUnit(double factorToInches) {
        this.factorToInches = factorToInches;
    }

    public double toBase(double value) {
        return value * factorToInches;
    }

    public double fromBase(double baseValue) {
        return baseValue / factorToInches;
    }
}