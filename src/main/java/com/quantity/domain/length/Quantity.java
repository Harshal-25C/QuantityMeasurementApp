package com.quantity.domain.length;

import java.util.Objects;

public class Quantity {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final LengthUnit unit;

    public Quantity(double value, LengthUnit unit) {
        if(unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.toBaseUnit(value);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Quantity other = (Quantity) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseUnit() / EPSILON));
    }
}