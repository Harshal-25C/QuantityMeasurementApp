package com.quantity.model;

import com.quantity.unit.IMeasurable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuantityModel<U extends IMeasurable> {

    private double value;
    private U unit;

}