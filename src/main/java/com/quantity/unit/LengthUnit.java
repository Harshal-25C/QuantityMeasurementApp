package com.quantity.unit;

public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
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

	@Override
	public double toBaseUnit(double value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double fromBaseUnit(double baseValue) {
		// TODO Auto-generated method stub
		return 0;
	}
}