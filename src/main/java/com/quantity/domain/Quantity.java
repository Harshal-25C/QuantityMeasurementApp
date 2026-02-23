package com.quantity.domain;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    /* -------------------- ADDITION (Existing UC11) -------------------- */

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateOperation(other, targetUnit);

        double baseSum = toBase(this) + toBase(other);
        return createResult(baseSum, targetUnit);
    }

    /* -------------------- SUBTRACTION (UC12) -------------------- */

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateOperation(other, targetUnit);

        double baseResult = toBase(this) - toBase(other);
        return createResult(baseResult, targetUnit);
    }

    /* -------------------- DIVISION (UC12) -------------------- */

    public double divide(Quantity<U> other) {
        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot divide different measurement categories");

        double divisor = toBase(other);
        if (Math.abs(divisor) < EPSILON)
            throw new ArithmeticException("Division by zero quantity");

        return toBase(this) / divisor;
    }

    /* -------------------- COMMON UTILITIES -------------------- */

    private void validateOperation(Quantity<U> other, U targetUnit) {
        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories not allowed");
    }

    private double toBase(Quantity<U> q) {
        return q.unit.convertToBaseUnit(q.value);
    }

    private Quantity<U> createResult(double baseValue, U targetUnit) {
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        double rounded = round(converted);
        return new Quantity<>(rounded, targetUnit);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /* -------------------- EQUALITY -------------------- */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (!unit.getClass().equals(other.unit.getClass())) return false;

        return Math.abs(toBase(this) - other.unit.convertToBaseUnit(other.value)) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBase(this) / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}