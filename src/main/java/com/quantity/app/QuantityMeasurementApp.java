package com.quantity.app;

import com.quantity.domain.length.LengthUnit;
import com.quantity.domain.length.Quantity;

public class QuantityMeasurementApp {

    private static void demonstrate(String label, Quantity q1, Quantity q2) {
        System.out.println(label + " : " + q1.equals(q2));
    }

    public static void main(String[] args) {

        demonstrate("Yard == Feet",
                new Quantity(1.0, LengthUnit.YARD),
                new Quantity(3.0, LengthUnit.FEET));

        demonstrate("Yard == Inches",
                new Quantity(1.0, LengthUnit.YARD),
                new Quantity(36.0, LengthUnit.INCH));

        demonstrate("CM == Inches",
                new Quantity(1.0, LengthUnit.CENTIMETER),
                new Quantity(0.393701, LengthUnit.INCH));

        demonstrate("2 Yard == 6 Feet",
                new Quantity(2.0, LengthUnit.YARD),
                new Quantity(6.0, LengthUnit.FEET));
    }
}