package com.quantity.domain.length;

import java.util.Objects;

public final class Quantity {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    public Quantity(double value, LengthUnit unit) {

        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if(unit == null)
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

    // Conversion
    public Quantity convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity(converted, targetUnit);
    }

    // ==========================
    // UC6 – Default Addition
    // ==========================
    public Quantity add(Quantity other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        return add(this, other, this.unit);
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

        double base1 = q1.unit.convertToBaseUnit(q1.value);
        double base2 = q2.unit.convertToBaseUnit(q2.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Quantity)) return false;

        Quantity other = (Quantity) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}