package com.quantity.domain;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

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

    public U getUnit() {
        return unit;
    }

    //Convert to target unit
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        double rounded = Math.round(converted * 100.0) / 100.0;

        return new Quantity<>(rounded, targetUnit);
    }

    //Add - result in first operand's unit
    public Quantity<U> add(Quantity<U> other) {

        if(other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        return add(other, this.unit);
    }

    //Add - result in specified unit
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if(other == null || targetUnit == null)
            throw new IllegalArgumentException("Invalid argument");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;
        double converted = targetUnit.convertFromBaseUnit(sumBase);

        double rounded = Math.round(converted * 100.0) / 100.0;

        return new Quantity<>(rounded, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(!(obj instanceof Quantity<?> that)) return false;

        //Cross-category prevention
        if(!this.unit.getClass().equals(that.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = that.unit.convertToBaseUnit(that.value);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(base);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}