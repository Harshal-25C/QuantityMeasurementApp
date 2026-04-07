package com.quantity.core;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	public Quantity(double value, U unit) {
		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");
		if (Double.isNaN(value))
			throw new IllegalArgumentException("Value cannot be NaN");
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	private double roundTwoDecimals(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	private enum ArithmeticOperation {

		ADD((a, b) -> a + b), SUBTRACT((a, b) -> a - b), MULTIPLY((a, b) -> a * b), DIVIDE((a, b) -> {
			if (b == 0.0)
				throw new ArithmeticException("Cannot divide by zero");
			return a / b;
		});

		private final DoubleBinaryOperator operation;

		ArithmeticOperation(DoubleBinaryOperator operation) {
			this.operation = operation;
		}

		public double compute(double a, double b) {
			return operation.applyAsDouble(a, b);
		}
	}

	private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
		if (other == null)
			throw new IllegalArgumentException("Other quantity cannot be null");
		if (!this.unit.getClass().equals(other.unit.getClass()))
			throw new IllegalArgumentException("Incompatible measurement categories");
		if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
			throw new IllegalArgumentException("Values must be finite numbers");
		if (targetUnitRequired && targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");
	}

	private double performBaseArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation) {
		this.unit.validateOperationSupport(operation.name());
		other.unit.validateOperationSupport(operation.name());

		double thisBase = this.unit.convertToBaseUnit(this.value);
		double otherBase = other.unit.convertToBaseUnit(other.value);

		return operation.compute(thisBase, otherBase);
	}

	private double toBaseUnit() {
		return unit.convertToBaseUnit(value);
	}

	// ===== CONVERT =====
	public Quantity<U> convertTo(U targetUnit) {
		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		if (unit instanceof TemperatureUnit && targetUnit instanceof TemperatureUnit) {
			double convertedValue = ((TemperatureUnit) unit).convertTo(this.value, (TemperatureUnit) targetUnit);
			return new Quantity<>(convertedValue, targetUnit);
		}

		double baseValue = toBaseUnit();
		double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
		return new Quantity<>(convertedValue, targetUnit);
	}

	// ===== ADD =====
	public Quantity<U> add(Quantity<U> other) {
		validateArithmeticOperands(other, null, false);
		double baseResult = performBaseArithmetic(other, this.unit, ArithmeticOperation.ADD);
		return new Quantity<>(roundTwoDecimals(this.unit.convertFromBaseUnit(baseResult)), this.unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {
		validateArithmeticOperands(other, targetUnit, true);
		double baseResult = performBaseArithmetic(other, targetUnit, ArithmeticOperation.ADD);
		return new Quantity<>(roundTwoDecimals(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
	}

	// ===== SUBTRACT =====
	public Quantity<U> subtract(Quantity<U> other) {
		validateArithmeticOperands(other, null, false);
		double baseResult = performBaseArithmetic(other, this.unit, ArithmeticOperation.SUBTRACT);
		return new Quantity<>(roundTwoDecimals(this.unit.convertFromBaseUnit(baseResult)), this.unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
		validateArithmeticOperands(other, targetUnit, true);
		double baseResult = performBaseArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);
		return new Quantity<>(roundTwoDecimals(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
	}

	// ===== DIVIDE =====
	public double divide(Quantity<U> other) {
		validateArithmeticOperands(other, null, false);
		return performBaseArithmetic(other, this.unit, ArithmeticOperation.DIVIDE);
	}

	// ===== MULTIPLY =====
	public double multiply(Quantity<U> other) {
		validateArithmeticOperands(other, null, false);
		return performBaseArithmetic(other, this.unit, ArithmeticOperation.MULTIPLY);
	}

	// ===== EQUALS =====
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Quantity<?> other = (Quantity<?>) obj;
		return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < 0.01;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Math.round(toBaseUnit() * 100.0) / 100.0);
	}

	@Override
	public String toString() {
		return value + " " + unit.getUnitName();
	}
}