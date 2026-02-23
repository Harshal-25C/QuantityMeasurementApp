package com.quantity.app;

import com.quantity.domain.length.LengthUnit;
import com.quantity.domain.length.Quantity;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println(
                new Quantity(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES)
        );

        System.out.println(
                Quantity.add(
                        new Quantity(1.0, LengthUnit.FEET),
                        new Quantity(12.0, LengthUnit.INCHES),
                        LengthUnit.FEET)
        );

        System.out.println(
                new Quantity(36.0, LengthUnit.INCHES)
                        .equals(new Quantity(1.0, LengthUnit.YARDS))
        );

        System.out.println(
                Quantity.add(
                        new Quantity(1.0, LengthUnit.YARDS),
                        new Quantity(3.0, LengthUnit.FEET),
                        LengthUnit.YARDS)
        );

        System.out.println(
                new Quantity(2.54, LengthUnit.CENTIMETERS)
                        .convertTo(LengthUnit.INCHES)
        );
    }
}