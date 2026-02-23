package com.quantity.app;

import com.quantity.domain.length.LengthUnit;
import com.quantity.domain.length.Quantity;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println(
            Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES),
                LengthUnit.FEET)
        );

        System.out.println(
            Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES)
        );

        System.out.println(
            Quantity.add(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS)
        );

        System.out.println(
            Quantity.add(
                new Quantity(1.0, LengthUnit.YARDS),
                new Quantity(3.0, LengthUnit.FEET),
                LengthUnit.YARDS)
        );

        System.out.println(
            Quantity.add(
                new Quantity(36.0, LengthUnit.INCHES),
                new Quantity(1.0, LengthUnit.YARDS),
                LengthUnit.FEET)
        );

        System.out.println(
            Quantity.add(
                new Quantity(2.54, LengthUnit.CENTIMETERS),
                new Quantity(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS)
        );

        System.out.println(
            Quantity.add(
                new Quantity(5.0, LengthUnit.FEET),
                new Quantity(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS)
        );

        System.out.println(
            Quantity.add(
                new Quantity(5.0, LengthUnit.FEET),
                new Quantity(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES)
        );
    }
}