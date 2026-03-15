package com.quantity.domain.length;

import com.quantity.domain.Quantity;
import com.quantity.unit.TemperatureUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    @Test
    void testCelsiusToFahrenheitEquality() {
        assertEquals(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS),
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
        );
    }

    @Test
    void testConversion() {
        Quantity<TemperatureUnit> result =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(
                new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT),
                result
        );
    }

    @Test
    void testUnsupportedOperation() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS))
        );
    }
}