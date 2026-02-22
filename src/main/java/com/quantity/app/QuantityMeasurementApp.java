package com.quantity.app;

import com.quantity.domain.length.LengthUnit;
import com.quantity.domain.length.Quantity;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Quantity q1, Quantity q2) {
        return q1.equals(q2);
    }

    public static void demonstrate(String label, Quantity q1, Quantity q2) {
        System.out.println(label + " : " + demonstrateLengthEquality(q1, q2));
    }

    public static void main(String[] args) {

        demonstrate("Feet == Feet",
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(1.0, LengthUnit.FEET));

        demonstrate("Inches == Inches",
                new Quantity(1.0, LengthUnit.INCH),
                new Quantity(1.0, LengthUnit.INCH));

        demonstrate("Feet == Inches",
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCH));
    }
}