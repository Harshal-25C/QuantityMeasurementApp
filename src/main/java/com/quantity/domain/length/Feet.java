package com.quantity.domain.length;

import java.util.Objects;

/**
 * Value Object representing a measurement in Feet.
 * Immutable and value-based equality.
 */
public final class Feet {

    private final double value;

    public Feet(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Feet feet = (Feet) obj;
        return Double.compare(feet.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + " ft";
    }
}