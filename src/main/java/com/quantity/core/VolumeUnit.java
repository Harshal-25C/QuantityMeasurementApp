package com.quantity.core;

//volume units
public enum VolumeUnit implements IMeasurable {

 LITRE(1.0),
 MILLILITRE(0.001),
 GALLON(3.78541);

 private final double conversionFactor;

 VolumeUnit(double conversionFactor) {
     this.conversionFactor = conversionFactor;
 }

 @Override
 public double getConversionFactor() {
     return conversionFactor;
 }

 @Override
 public double convertToBaseUnit(double value) {
     double result = value * conversionFactor;
     return Math.round(result * 100.0) / 100.0;  //Convert to litre
 }

 @Override
 public double convertFromBaseUnit(double baseValue) {
     double result = baseValue / conversionFactor;
     return Math.round(result * 100.0) / 100.0; //From litre
 }

 @Override
 public String getUnitName() {
     return name();
 }
}