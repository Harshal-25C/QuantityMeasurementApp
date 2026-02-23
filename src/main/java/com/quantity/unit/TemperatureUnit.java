package com.quantity.unit;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(
            c -> c,
            c -> c
    ),

    FAHRENHEIT(
            f -> (f - 32) * 5 / 9,
            c -> (c * 9 / 5) + 32
    ),

    KELVIN(
            k -> k - 273.15,
            c -> c + 273.15
    );

    private final Function<Double, Double> toCelsius;
    private final Function<Double, Double> fromCelsius;

    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(Function<Double, Double> toCelsius,
                    Function<Double, Double> fromCelsius) {
        this.toCelsius = toCelsius;
        this.fromCelsius = fromCelsius;
    }

    @Override
    public double toBaseUnit(double value) {
        return toCelsius.apply(value);
    }

    @Override
    public double fromBaseUnit(double baseValue) {
        return fromCelsius.apply(baseValue);
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation + " operation."
        );
    }

	@Override
	public double convertFromBaseUnit(double baseValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUnitName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double convertToBaseUnit(double value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getConversionFactor() {
		// TODO Auto-generated method stub
		return 0;
	}
}
