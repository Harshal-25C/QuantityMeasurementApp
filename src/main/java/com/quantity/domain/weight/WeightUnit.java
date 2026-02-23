package com.quantity.domain.weight;

public enum WeightUnit {

    KILOGRAM(1.0),        // Base Unit
    GRAM(0.001),          // 1 g = 0.001 kg
    POUND(0.453592);      // 1 lb ≈ 0.453592 kg

    private final double conversionFactorToKg;

    WeightUnit(double conversionFactorToKg) {
        this.conversionFactorToKg = conversionFactorToKg;
    }

    public double getConversionFactor() {
        return conversionFactorToKg;
    }

    //Convert THIS unit → base unit (kg)
    public double convertToBaseUnit(double value) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        return value * conversionFactorToKg;
    }

    //Convert base unit (kg) → THIS unit
    public double convertFromBaseUnit(double baseValue) {

        if(!Double.isFinite(baseValue))
            throw new IllegalArgumentException("Invalid numeric value");

        return baseValue / conversionFactorToKg;
    }
}