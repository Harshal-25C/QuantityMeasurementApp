package com.quantity.core;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

	CELSIUS, FAHRENHEIT, KELVIN;

	private static final SupportsArithmetic supportsArithmetic = () -> false;

	@Override
	public boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
	}

	@Override
	public void validateOperationSupport(String operation) {
		throw new UnsupportedOperationException(this.name() + " does not support " + operation + " operation.");
	}

	private static final Function<Double, Double> F_TO_C = f -> (f - 32) * 5.0 / 9.0;
	private static final Function<Double, Double> C_TO_F = c -> (c * 9.0 / 5.0) + 32;
	private static final Function<Double, Double> K_TO_C = k -> k - 273.15;
	private static final Function<Double, Double> C_TO_K = c -> c + 273.15;

	@Override
	public String getUnitName() {
		return this.name();
	}

	@Override
	public double getConversionFactor() {
		return 1.0;
	}

	@Override
	public double convertToBaseUnit(double value) {
		switch (this) {
		case CELSIUS:
			return value;
		case FAHRENHEIT:
			return F_TO_C.apply(value);
		case KELVIN:
			return K_TO_C.apply(value);
		default:
			throw new IllegalStateException("Unexpected unit");
		}
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		switch (this) {
		case CELSIUS:
			return baseValue;
		case FAHRENHEIT:
			return C_TO_F.apply(baseValue);
		case KELVIN:
			return C_TO_K.apply(baseValue);
		default:
			throw new IllegalStateException("Unexpected unit");
		}
	}

	public double convertTo(double value, TemperatureUnit target) {
		if (target == null)
			throw new IllegalArgumentException("Target temperature unit cannot be null");
		if (this == target)
			return value;

		double celsius;
		switch (this) {
		case CELSIUS:
			celsius = value;
			break;
		case FAHRENHEIT:
			celsius = (value - 32) * 5.0 / 9.0;
			break;
		case KELVIN:
			celsius = value - 273.15;
			break;
		default:
			throw new IllegalStateException("Unexpected unit");
		}

		switch (target) {
		case CELSIUS:
			return celsius;
		case FAHRENHEIT:
			return (celsius * 9.0 / 5.0) + 32;
		case KELVIN:
			return celsius + 273.15;
		default:
			throw new IllegalStateException("Unexpected unit");
		}
	}
}