package com.quantity.core;

public enum WeightUnit implements IMeasurable {

	MILLIGRAM(0.001), GRAM(1.0), KILOGRAM(1000.0), POUND(453.592), TONNE(1_000_000.0);

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
		return Math.round(value * conversionFactor * 100.0) / 100.0;
	}

	@Override
	public double convertFromBaseUnit(double base) {
		return Math.round(base / conversionFactor * 100.0) / 100.0;
	}

	@Override
	public String getUnitName() {
		return this.name();
	}
}