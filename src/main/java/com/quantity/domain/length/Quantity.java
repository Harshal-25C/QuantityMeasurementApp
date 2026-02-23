package com.quantity.domain.length;

import java.util.Objects;

public final class Quantity {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    public Quantity(double value, LengthUnit unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // ========================
    // UC5: CONVERSION
    // ========================

    public Quantity convertTo(LengthUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double valueInFeet = unit.toFeet(value);
        double convertedValue = targetUnit.fromFeet(valueInFeet);

        return new Quantity(convertedValue, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        double valueInFeet = source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }

    // ========================
    // UC6: ADDITION
    // ========================

    public Quantity add(Quantity other) {
        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double thisInFeet = this.unit.toFeet(this.value);
        double otherInFeet = other.unit.toFeet(other.value);

        double sumInFeet = thisInFeet + otherInFeet;

        double resultInThisUnit = this.unit.fromFeet(sumInFeet);

        return new Quantity(resultInThisUnit, this.unit);
    }

    public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double q1Feet = q1.unit.toFeet(q1.value);
        double q2Feet = q2.unit.toFeet(q2.value);

        double sumFeet = q1Feet + q2Feet;

        double result = targetUnit.fromFeet(sumFeet);

        return new Quantity(result, targetUnit);
    }

    // ========================
    // EQUALITY
    // ========================

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity)) return false;

        Quantity other = (Quantity) obj;

        double thisFeet = this.unit.toFeet(this.value);
        double otherFeet = other.unit.toFeet(other.value);

        return Math.abs(thisFeet - otherFeet) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.toFeet(value);
        return Objects.hash(base);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}