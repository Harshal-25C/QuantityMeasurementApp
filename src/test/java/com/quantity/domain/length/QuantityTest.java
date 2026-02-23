package com.quantity.domain.length;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_SameUnit() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(2.0, LengthUnit.FEET);

        Quantity result = q1.add(q2);

        assertEquals(new Quantity(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);

        Quantity result = q1.add(q2);

        assertEquals(new Quantity(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_Commutative() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);

        assertEquals(q1.add(q2), q2.add(q1));
    }

    @Test
    void testAddition_WithZero() {
        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity zero = new Quantity(0.0, LengthUnit.INCHES);

        assertEquals(q1, q1.add(zero));
    }

    @Test
    void testAddition_Negative() {
        Quantity q1 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(-2.0, LengthUnit.FEET);

        assertEquals(new Quantity(3.0, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_NullOperand() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    @Test
    void testAddition_LargeValues() {
        Quantity q1 = new Quantity(1e6, LengthUnit.FEET);
        Quantity q2 = new Quantity(1e6, LengthUnit.FEET);

        assertEquals(new Quantity(2e6, LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_SmallValues() {
        Quantity q1 = new Quantity(0.001, LengthUnit.FEET);
        Quantity q2 = new Quantity(0.002, LengthUnit.FEET);

        Quantity result = q1.add(q2);

        assertEquals(0.003, result.getValue(), EPSILON);
    }
}