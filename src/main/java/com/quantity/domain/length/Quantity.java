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

    // ==========================
    // UC6 – Default Addition
    // Result in first operand unit
    // ==========================
    public Quantity add(Quantity other) {
        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        return addInternal(this, other, this.unit);
    }

    // ==========================
    // UC7 – Explicit Target Unit
    // ==========================
    public static Quantity add(Quantity q1,
                               Quantity q2,
                               LengthUnit targetUnit) {

        if (q1 == null || q2 == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return addInternal(q1, q2, targetUnit);
    }

    // ==========================
    // PRIVATE UTILITY METHOD
    // (Avoids duplication)
    // ==========================
    private static Quantity addInternal(Quantity q1,
                                        Quantity q2,
                                        LengthUnit targetUnit) {

        // Convert both to base unit (feet)
        double q1Feet = q1.unit.toFeet(q1.value);
        double q2Feet = q2.unit.toFeet(q2.value);

        double sumFeet = q1Feet + q2Feet;

        // Convert to target unit
        double resultValue = targetUnit.fromFeet(sumFeet);

        return new Quantity(resultValue, targetUnit);
    }

    // ==========================
    // Equality
    // ==========================
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
        return Objects.hash(unit.toFeet(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}