package com.quantity.domain.length;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void testEquality_YardToYard_SameValue() {
        assertEquals(
                new Quantity(1.0, LengthUnit.YARD),
                new Quantity(1.0, LengthUnit.YARD)
        );
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(
                new Quantity(1.0, LengthUnit.YARD),
                new Quantity(3.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_YardToInch_EquivalentValue() {
        assertEquals(
                new Quantity(1.0, LengthUnit.YARD),
                new Quantity(36.0, LengthUnit.INCH)
        );
    }

    @Test
    void testEquality_CmToInch_EquivalentValue() {
        assertEquals(
                new Quantity(1.0, LengthUnit.CENTIMETER),
                new Quantity(0.393701, LengthUnit.INCH)
        );
    }

    @Test
    void testEquality_CmToFeet_NotEqual() {
        assertNotEquals(
                new Quantity(1.0, LengthUnit.CENTIMETER),
                new Quantity(1.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        Quantity yard = new Quantity(1.0, LengthUnit.YARD);
        Quantity feet = new Quantity(3.0, LengthUnit.FEET);
        Quantity inch = new Quantity(36.0, LengthUnit.INCH);

        assertEquals(yard, feet);
        assertEquals(feet, inch);
        assertEquals(yard, inch);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity(1.0, null));
    }
}