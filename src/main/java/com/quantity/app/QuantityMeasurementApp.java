package com.quantity.app;

import com.quantity.domain.length.Feet;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        System.out.println("Are equal? " + first.equals(second));
    }
}