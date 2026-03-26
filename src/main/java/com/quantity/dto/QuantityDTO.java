package com.quantity.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityDTO {

	@NotNull(message = "Value cannot be null")
	private Double value;

	@NotEmpty(message = "Unit name cannot be empty")
	private String unit;

	@NotEmpty(message = "Measurement type cannot be empty")
	@Pattern(regexp = "LengthUnit|WeightUnit|VolumeUnit|TemperatureUnit", message = "Measurement type must be LengthUnit, WeightUnit, VolumeUnit, or TemperatureUnit")
	private String measurementType;

	// ===== Valid unit names per measurement type =====
	private static final List<String> LENGTH_UNITS = Arrays.asList("FEET", "INCHES", "YARDS", "CENTIMETERS");

	private static final List<String> WEIGHT_UNITS = Arrays.asList("MILLIGRAM", "GRAM", "KILOGRAM", "POUND", "TONNE");

	private static final List<String> VOLUME_UNITS = Arrays.asList("LITRE", "MILLILITRE", "GALLON");

	private static final List<String> TEMPERATURE_UNITS = Arrays.asList("CELSIUS", "FAHRENHEIT", "KELVIN");

	@AssertTrue(message = "Unit must be valid for the specified measurement type")
	public boolean isUnitValidForMeasurementType() {

		// handled by @NotEmpty and @NotNull
		if (unit == null || measurementType == null)
			return true;

		switch (measurementType) {
		case "LengthUnit":
			return LENGTH_UNITS.contains(unit.toUpperCase());
		case "WeightUnit":
			return WEIGHT_UNITS.contains(unit.toUpperCase());
		case "VolumeUnit":
			return VOLUME_UNITS.contains(unit.toUpperCase());
		case "TemperatureUnit":
			return TEMPERATURE_UNITS.contains(unit.toUpperCase());
		default:
			return false;
		}
	}

}