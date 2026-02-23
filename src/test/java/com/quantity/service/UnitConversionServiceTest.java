package com.quantity.service;

import com.quantity.domain.length.Length;
import com.quantity.domain.length.LengthUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitConversionServiceTest {

    private final UnitConversionService service = new UnitConversionService();

    @Test
    void shouldConvertFeetToInches() {
        Length feet = new Length(1, LengthUnit.FEET);

        Length result = service.convert(feet, LengthUnit.INCHES);

        assertEquals(new Length(12, LengthUnit.INCHES), result);
    }

    @Test
    void shouldConvertInchesToFeet() {
        Length inches = new Length(24, LengthUnit.INCHES);

        Length result = service.convert(inches, LengthUnit.FEET);

        assertEquals(new Length(2, LengthUnit.FEET), result);
    }

    @Test
    void shouldConvertYardsToFeet() {
        Length yards = new Length(1, LengthUnit.YARDS);

        Length result = service.convert(yards, LengthUnit.FEET);

        assertEquals(new Length(3, LengthUnit.FEET), result);
    }
}