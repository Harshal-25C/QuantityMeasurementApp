package com.quantity.app;

import com.quantity.domain.length.LengthUnit;
import com.quantity.domain.length.Quantity;

public class QuantityMeasurementApp {

    public static void demonstrateAddition(Quantity q1, Quantity q2) {
        Quantity result = q1.add(q2);
        System.out.println("Result: " + result);
    }

    public static void demonstrateConversion(double value, LengthUnit from,  LengthUnit to) {
        double result = Quantity.convert(value, from, to);
        System.out.println("Converted Value: " + result + " " + to);
    }

    public static void main(String[] args) {

        //UC6 Examples
        demonstrateAddition(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCHES)
        );

        demonstrateAddition(
                new Quantity(1.0, LengthUnit.YARDS),
                new Quantity(3.0, LengthUnit.FEET)
        );

        demonstrateAddition(
                new Quantity(2.54, LengthUnit.CENTIMETERS),
                new Quantity(1.0, LengthUnit.INCHES)
        );
    }
}