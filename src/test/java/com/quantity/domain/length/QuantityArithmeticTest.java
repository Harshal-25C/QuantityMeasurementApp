package com.quantity.domain.length;

import com.quantity.domain.Quantity;
import com.quantity.unit.LengthUnit;
import com.quantity.unit.VolumeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityArithmeticTest {

    @Test
    void testSubtraction_CrossUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = l1.subtract(l2);
        assertEquals(9.5, result.getValue(), 0.01);
    }

    @Test
    void testSubtraction_NegativeResult() {
        Quantity<LengthUnit> l1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = l1.subtract(l2);
        assertEquals(-5.0, result.getValue(), 0.01);
    }

    @Test
    void testDivision_SameUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, l1.divide(l2), 0.0001);
    }

    @Test
    void testDivision_CrossUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, l1.divide(l2), 0.0001);
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> l1.divide(l2));
    }

    @Test
    void testSubtraction_Volume() {
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.subtract(v2);
        assertEquals(4.5, result.getValue(), 0.01);
    }
}