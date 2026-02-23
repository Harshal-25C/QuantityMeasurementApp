package com.quantity.app;

import com.quantity.domain.Quantity;
import com.quantity.unit.TemperatureUnit;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<TemperatureUnit> temp1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> temp2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("Equality: " + temp1.equals(temp2));

        System.out.println("Convert 100C to F: " +
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT));

        try {
            temp1.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}