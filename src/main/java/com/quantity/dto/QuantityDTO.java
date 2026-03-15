package com.quantity.dto;

public class QuantityDTO {

    private double value;
    private String unit;

    public QuantityDTO() {}

    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    @Override
    public String toString() {
        return "QuantityDTO [value=" + value + ", unit=" + unit + "]";
    }
}