package com.quantity.domain.length;

import java.util.Objects;

public final class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    double toBase() {
        return unit.toBase(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length other = (Length) o;
        return Double.compare(round(this.toBase()), round(other.toBase())) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round(toBase()));
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}