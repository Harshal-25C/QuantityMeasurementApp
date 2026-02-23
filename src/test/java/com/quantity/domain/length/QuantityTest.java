package com.quantity.domain.length;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        Quantity result = Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES),
                LengthUnit.FEET);

        assertEquals(new Quantity(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        Quantity result = Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES);

        assertEquals(new Quantity(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        Quantity result = Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        Quantity r1 = Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        Quantity r2 = Quantity.add(
                new Quantity(12.0, LengthUnit.INCHES),
                new Quantity(1.0, LengthUnit.FEET),
                LengthUnit.YARDS);

        assertEquals(r1, r2);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTarget() {

        assertThrows(IllegalArgumentException.class,
                () -> Quantity.add(
                        new Quantity(1.0, LengthUnit.FEET),
                        new Quantity(12.0, LengthUnit.INCHES),
                        null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        Quantity result = Quantity.add(
                new Quantity(5.0, LengthUnit.FEET),
                new Quantity(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES);

        assertEquals(new Quantity(36.0, LengthUnit.INCHES), result);
    }
    
    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        double result = LengthUnit.INCHES.convertToBaseUnit(12.0);
        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        double result = LengthUnit.YARDS.convertFromBaseUnit(3.0);
        assertEquals(1.0, result, 1e-6);
    }
    @Test
    void testQuantity_ConvertTo() {
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        Quantity result = q.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity(12.0, LengthUnit.INCHES), result);
    }
    @Test
    void testQuantity_Add_WithTargetUnit() {

        Quantity result = Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), 1e-6);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }
}