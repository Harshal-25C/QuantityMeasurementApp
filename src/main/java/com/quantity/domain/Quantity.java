package com.quantity.domain;

import com.quantity.unit.ArithmeticOperation;
import com.quantity.unit.IMeasurable;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 0.01;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Cross-category conversion not allowed");

        double base = unit.toBaseUnit(value);
        double converted = targetUnit.fromBaseUnit(base);
        return new Quantity<>(round(converted), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return performArithmetic(other, ArithmeticOperation.ADD);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return performArithmetic(other, ArithmeticOperation.SUBTRACT);
    }

    public Quantity<U> divide(Quantity<U> other) {
        return performArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    private Quantity<U> performArithmetic(Quantity<U> other,
                                          ArithmeticOperation operation) {

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different unit categories");

        unit.validateOperationSupport(operation.name());

        double base1 = unit.toBaseUnit(value);
        double base2 = other.unit.toBaseUnit(other.value);

        double result = operation.apply(base1, base2);

        return new Quantity<>(round(unit.fromBaseUnit(result)), unit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (!unit.getClass().equals(other.unit.getClass())) return false;

        double base1 = unit.toBaseUnit(value);
        double base2 = other.unit.toBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), round(unit.toBaseUnit(value)));
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Quantity{" + value + " " + unit + '}';
    }
}