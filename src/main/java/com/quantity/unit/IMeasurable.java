package com.quantity.unit;

public interface IMeasurable {

    double toBaseUnit(double value);
    double fromBaseUnit(double baseValue);

    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        // Default: all operations allowed
    }
	double convertFromBaseUnit(double baseValue);
	String getUnitName();
	double convertToBaseUnit(double value);
	double getConversionFactor();
}