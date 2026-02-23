package com.quantity.app;

import com.quantity.domain.length.Length;
import com.quantity.domain.length.LengthUnit;
import com.quantity.service.UnitConversionService;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        UnitConversionService service = new UnitConversionService();

        Length feet = new Length(2, LengthUnit.FEET);

        Length result = service.convert(feet, LengthUnit.INCHES);

        System.out.println("Converted Value: " + result);
    }
}