package com.quantity.domain.length;

import com.quantity.domain.*;
import com.quantity.domain.length.LengthUnit;
import com.quantity.domain.weight.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
    @Test
    void testIMeasurableImplementation_LengthUnit() {
        IMeasurable unit = LengthUnit.FEET;
        assertEquals("FEET", unit.getUnitName());
        assertEquals(1.0, unit.getConversionFactor());
    }

    @Test
    void testIMeasurableImplementation_WeightUnit() {
        IMeasurable unit = WeightUnit.KILOGRAM;
        assertEquals("KILOGRAM", unit.getUnitName());
        assertEquals(1.0, unit.getConversionFactor());
    }
    
    @Test
    void testEquality_LengthUnits() {
        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }
    
    @Test
    void testEquality_WeightUnits() {
        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(q1, q2);
    }
    
    @Test
    void testCrossCategoryEquality() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }
    
    @Test
    void testConversion_Length() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue());
    }
    
    @Test
    void testConversion_Weight() {

        Quantity<WeightUnit> q =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue());
    }
    
    @Test
    void testAddition_Length() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue());
    }
    
    @Test
    void testAddition_Weight() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                q1.add(q2, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue());
    }
    
    @Test
    void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testConstructor_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }
    
    @Test
    void testHashCodeConsistency() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1.hashCode(), q2.hashCode());
    }
}