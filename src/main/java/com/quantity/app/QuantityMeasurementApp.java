package com.quantity.app;

import com.quantity.domain.Quantity;
import com.quantity.unit.LengthUnit;
import com.quantity.unit.WeightUnit;
import com.quantity.unit.VolumeUnit;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("Subtraction (Length):");
        System.out.println(l1.subtract(l2));
        System.out.println(l1.subtract(l2, LengthUnit.INCHES));

        System.out.println("\nDivision (Length):");
        System.out.println(l1.divide(new Quantity<>(2.0, LengthUnit.FEET)));

        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("\nSubtraction (Weight):");
        System.out.println(w1.subtract(w2));

        System.out.println("\nDivision (Weight):");
        System.out.println(w1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("\nSubtraction (Volume):");
        System.out.println(v1.subtract(v2));

        System.out.println("\nDivision (Volume):");
        System.out.println(v1.divide(new Quantity<>(10.0, VolumeUnit.LITRE)));
    }
}